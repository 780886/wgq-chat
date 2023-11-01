package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.RoomTypeEnum;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.event.MessageSendEvent;
import com.wgq.chat.repository.MessageRepository;
import com.wgq.chat.repository.RoomFriendRepository;
import com.wgq.chat.repository.RoomRepository;
import com.wgq.chat.repository.RoomUserRepository;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: MessageSendConsumer
 * @Author : wgq
 * @Date :2023/8/23  14:53
 * @Description:
 * @Version :1.0
 */
@RocketMQMessageListener(consumerGroup = MQConstant.SEND_MSG_GROUP, topic = MQConstant.SEND_MSG_TOPIC)
@Named
public class MessageSendEventConsumer implements RocketMQListener<MessageSendEvent> {

    private final static Logger logger = LoggerFactory.getLogger(MessageSendEventConsumer.class);

    @Inject
    private ImMQPublisher imMQPublisher;

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private RoomRepository repository;

    @Inject
    private RoomFriendRepository roomFriendRepository;

    @Inject
    private RoomUserRepository roomUserRepository;

    @Override
    public void onMessage(MessageSendEvent messageSendEvent) {
        MessageBO messageBO = null;
        RoomBO roomBO = null;
        try {
            messageBO = this.messageRepository.getMessage(messageSendEvent.getMessageId());
            roomBO = this.repository.getRoom(messageBO.getRoomId());
        }catch (Exception e){
            logger.error("未找到消息记录,房间id:{}...",messageBO.getRoomId());
            return;
        }
        if (roomBO.isHotRoom()){
            //TODO 推送所有人
        }else {
            List<Long> memberUserList = new ArrayList<>();
            if (Objects.equals(roomBO.getType(), RoomTypeEnum.GROUP.getType())){//普通群聊推送所有群成员
                //TODO 推送群所有成员
            }else if (Objects.equals(roomBO.getType(),RoomTypeEnum.FRIEND.getType())){//单聊对象
                //推送给单人
                RoomFriendBO roomFriend = this.roomFriendRepository.getRoomFriend(roomBO.getId());
                memberUserList = Arrays.asList(roomFriend.getSmallerUserId(),roomFriend.getLargerUserId());
            }
            // 更新或创建用户会话列表信息
            this.roomUserRepository.refreshOrCreateLastTime(roomBO.getId(), memberUserList, messageBO.getId(), messageBO.getSendTime());
            //推送给用户
            this.imMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.MESSAGE.getType(),messageBO),memberUserList);
        }
    }
}
