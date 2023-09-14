package com.wgq.chat.domain.service;

import com.sheep.mq.MQConstant;
import com.sheep.mq.MQPublisher;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.domain.service.strategy.AbstractMessageHandler;
import com.wgq.chat.domain.service.strategy.MessageHandlerFactory;
import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.chat.protocol.enums.MessageStatusEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.repository.MessageRepository;
import org.springframework.context.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Named;

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
    private ApplicationEventPublisher applicationEventPublisher;

    @Inject
    private MQPublisher mqPublisher;


    public Long sendMessage(MessageSendParam messageSendParam) throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        return sendMessage(messageSendParam, loginUser.getUserId());
    }

    public MessageReturnBO getMessage(Long messageId) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        //TODO 批量回复的消息
        return this.messageRepository.getMessage(messageId, loginUser.getUserId());
    }

    public Long sendMessage(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        AbstractMessageHandler messageHandler = MessageHandlerFactory.getStrategyNoNull(messageSendParam.getMessageType());
        //校验消息
        messageHandler.checkMsg(messageSendParam,userId);
        //构造消息业务数据
        MessageBO messageBO = new MessageBO(messageSendParam.getRoomId(),
                userId, MessageStatusEnum.NORMAL.getStatus(),
                messageSendParam.getMessageType()
                ,messageSendParam.getBody());
        Long messageId = this.messageRepository.save(messageBO);
        //推送消息
        this.mqPublisher.publish(MQConstant.SEND_MSG_TOPIC,new MessageSendDTO(messageId),messageId);
        return messageId;
    }
}
