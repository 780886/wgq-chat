package com.wgq.chat.domain.service;

import com.sheep.constant.DateTime;
import com.sheep.enums.DateTimeUnit;
import com.sheep.enums.NormalOrNoEnum;
import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.DateUtils;
import com.wgq.chat.assemble.MessageAssemble;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.domain.service.strategy.AbstractMessageHandler;
import com.wgq.chat.domain.service.strategy.MessageHandlerFactory;
import com.wgq.chat.domain.service.strategy.RecallMessageHandler;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.event.MessageSendEvent;
import com.wgq.chat.protocol.param.MessageReadParam;
import com.wgq.chat.protocol.param.MessageRecallParam;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.repository.MessageRepository;
import com.wgq.chat.repository.RoomFriendRepository;
import com.wgq.chat.repository.RoomRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: ChatService
 * @Author : wgq
 * @Date :2023/8/23  10:09
 * @Description:
 * @Version :1.0
 */
@Named
public class ChatService {

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private ImMQPublisher imMQPublisher;

    @Inject
    private RoomRepository roomRepository;

    @Inject
    private RoomFriendRepository roomFriendRepository;

    @Inject
    private MessageAssemble messageAssemble;


    @Inject
    private RecallMessageHandler recallMessageHandler;

    /**
     * 发送消息
     * @param messageSendParam 消息发送入参
     * @return
     * @throws BusinessException
     */
    public Long sendMessage(MessageSendParam messageSendParam) throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        return sendMessage(messageSendParam, loginUser.getUserId());
    }

    /**
     * 根据id获取返回消息
     * @param messageId 消息id
     * @return
     */
    public MessageReturnBO getMessage(Long messageId) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        //TODO 批量回复的消息
        return this.messageRepository.getMessage(messageId, loginUser.getUserId());
    }

    /**
     * 发送消息
     * @param messageSendParam 消息发送入参
     * @param userId 用户id
     * @return
     * @throws BusinessException
     */
    public Long sendMessage(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        this.check(messageSendParam,userId);
        AbstractMessageHandler messageHandler = MessageHandlerFactory.getStrategyNoNull(messageSendParam.getMessageType());
        //校验消息
        messageHandler.checkMessage(messageSendParam,userId);
        //构造消息业务数据
        MessageBO messageBO = this.messageAssemble.assembleMessageBO(messageSendParam,userId);
        Long messageId = this.messageRepository.save(messageBO);
        messageHandler.saveMessage(messageId,messageSendParam);
        //推送消息
//        this.mqPublisher.publish(MQConstant.SEND_MSG_TOPIC,new MessageSendDTO(messageId),messageId);
        this.imMQPublisher.publish(MQConstant.SEND_MSG_TOPIC,new MessageSendEvent(messageId));
        return messageId;
    }

    private void check(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        RoomBO roomBO = this.roomRepository.getRoom(messageSendParam.getRoomId());
        //TODO 后期开放群聊功能
        if (roomBO.isHotRoom()){//官方群聊，跳过校验
            return;
        }
        //单聊
        if (roomBO.isRoomFriend()){
            RoomFriendBO roomFriendBO = this.roomFriendRepository.getRoomFriend(messageSendParam.getRoomId());
            // TODO "您已被对方拉黑"
            Asserts.isTrue(Objects.equals(NormalOrNoEnum.NOT_NORMAL.getStatus(),roomFriendBO.getStatus()), BusinessCodeEnum.USER_BLACK);
            // TODO "您已被对方拉黑"!userId.equals(roomFriendBO.getLargerUserId())
//            Asserts.isTrue(!Objects.equals(userId,roomFriendBO.getSmallerUserId()) || !Objects.equals(userId,roomFriendBO.getLargerUserId()),BusinessCodeEnum.USER_BLACK);
        }
//        //群聊
//        if (roomBO.isRoomGroup()){
//
//        }
    }

    public List<MessageReturnBO> getMessageList(MessageReadParam messageReadParam) throws BusinessException {
        // TODO"房间不能为空!"
        Asserts.isTrue(Objects.isNull(messageReadParam.getRoomId()),null);
        LoginUser loginToken = ThreadContext.getLoginToken();
        //获取当前房间最后一条消息id,来限制被踢出的人能看见的最大一条消息
        RoomBO room = this.roomRepository.getRoom(messageReadParam.getRoomId());
        // 房间有误
        Asserts.isTrue(Objects.isNull(room),null);
        if (room.isHotRoom()){
            return null;
        }
        return this.messageRepository.getMessageList(messageReadParam.getRoomId(),room.getLastMsgId());
    }

    public void recallMessage(MessageRecallParam messageRecallParam) throws BusinessException {
        //撤回消息为空
        Asserts.isTrue(Objects.isNull(messageRecallParam.getMessageId()),null);
        LoginUser loginUser = ThreadContext.getLoginToken();
        MessageBO message = this.messageRepository.getMessage(messageRecallParam.getMessageId());
        //校验能不能执行撤回
        checkRecall(loginUser.getUserId(),message);
        //执行消息撤回
        this.recallMessageHandler.recall(loginUser.getUserId(), message);
    }

    private void checkRecall(Long userId, MessageBO message) throws BusinessException {
        //消息有误
        Asserts.isTrue(Objects.isNull(message),null);
        //消息无法撤回
        Asserts.isTrue(Objects.equals(message.getType(), MessageTypeEnum.RECALL.getType()),null);
        /**
         * 可以加角色 校验权限
         * 普通用户只能撤回自己两分钟之内的消息
         */
        //抱歉，您没有权限!
        Asserts.isTrue(Objects.equals(userId,message.getSenderUserId()),null);
        long between = DateUtils.between(message.getCreateTime(), new Date(), DateTime.MILLISECOND_UNIT.get(DateTimeUnit.MINUTE));
        //超过两分钟的消息不能撤回哦~~
        Asserts.isTrue(between <= 2,null);

    }
}
