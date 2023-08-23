package com.wgq.chat.infrastructure.persistence.data.mapper;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.po.Message;

import javax.inject.Named;

/**
 * @ClassName: MessageConverter
 * @Author : wgq
 * @Date :2023/8/23  14:23
 * @Description:
 * @Version :1.0
 */
@Named
public class MessageConverter {
    public Message convert2po(MessageBO messageBO) {
        Message message = new Message();
        message.setSenderUserId(messageBO.getSenderUserId());
        message.setRoomId(messageBO.getRoomId());
        message.setType(messageBO.getType());
        message.setStatus(messageBO.getStatus());
        return message;
    }


    public MessageReturnBO convert2MessageReturnBO(Message message) {
        MessageReturnBO messageReturnBO = new MessageReturnBO();
        return messageReturnBO;
    }

    public MessageBO convert2bo(Message message) {
        MessageBO messageBO = new MessageBO();
        messageBO.setSenderUserId(message.getSenderUserId());
        messageBO.setRoomId(message.getRoomId());
        messageBO.setType(message.getType());
        messageBO.setStatus(message.getStatus());
        return messageBO;
    }
}
