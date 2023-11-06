package com.wgq.chat.domain.service.strategy;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.event.MessageRecallEvent;
import com.wgq.chat.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: RecallMessageHandler
 * @Author : wgq
 * @Date :2023/10/19  11:32
 * @Description: 撤回文本消息
 * @Version :1.0
 */
@Named
public class RecallMessageHandler extends AbstractMessageHandler<Object>{


    @Inject
    private MessageRepository messageRepository;


    @Inject
    private ImMQPublisher imMQPublisher;

    @Override
    MessageTypeEnum getMessageTypeEnum() {
        return MessageTypeEnum.RECALL;
    }

    @Override
    public void saveMessage(MessageBO messageBO, Object obj) throws BusinessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object showMessage(MessageBO message) {//前端来做
        return null;
    }

    @Override
    public Object showReplyMessage(MessageBO message) {
        return "原消息已被撤回";
    }

    @Override
    public String showContactMessage(MessageBO message) {
        return "撤回了一条消息";
    }

    public void recall(Long recallUserId, MessageBO message) {
        MessageBO messageBO = new MessageBO();
        messageBO.setId(message.getId());
        messageBO.setType(MessageTypeEnum.RECALL.getType());
        // TODO 扩展消息
        this.messageRepository.updateById(messageBO);
        //发送消息
        MessageRecallEvent messageRecallEvent = new MessageRecallEvent(WebsocketResponseTypeEnum.RECALL.getType(),message.getId(),message.getRoomId(),recallUserId);
        this.imMQPublisher.publish(MQConstant.PUSH_TOPIC,messageRecallEvent);
    }
}
