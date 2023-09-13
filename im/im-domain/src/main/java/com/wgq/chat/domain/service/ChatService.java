package com.wgq.chat.domain.service;

import com.sheep.mq.MQConstant;
import com.sheep.mq.MQPublisher;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
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

    public MessageReturnBO sendMessage(MessageSendParam messageSendParam) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        MessageBO messageBO = new MessageBO(messageSendParam.getRoomId(),
                loginUser.getUserId(), MessageStatusEnum.NORMAL.getStatus(),
                messageSendParam.getMessageType()
                ,messageSendParam.getBody());
        Long messageId = this.messageRepository.save(messageBO);
        this.mqPublisher.publish(MQConstant.SEND_MSG_TOPIC,new MessageSendDTO(messageId),messageId);
        return this.messageRepository.getMessage(messageId,loginUser.getUserId());
    }
}
