package com.wgq.chat.infrastructure.persistence.data.mapper;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.po.Message;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        message.setId(messageBO.getId());
        message.setSenderUserId(messageBO.getSenderUserId());
        message.setRoomId(messageBO.getRoomId());
        message.setType(messageBO.getType());
        message.setStatus(messageBO.getStatus());
        message.setContent(String.valueOf(messageBO.getBody()));
        return message;
    }


    public MessageReturnBO convert2MessageReturnBO(Message message) {
        MessageReturnBO messageReturnBO = new MessageReturnBO();
        messageReturnBO.setSenderUserId(message.getSenderUserId());
        messageReturnBO.setMessageId(message.getId());
        messageReturnBO.setRoomId(message.getRoomId());
        messageReturnBO.setSendTime(message.getCreateTime());
        messageReturnBO.setMessageType(message.getType());
        messageReturnBO.setBody(message.getContent());
        return messageReturnBO;
    }

    public MessageBO convert2BO(Message message) {
        MessageBO messageBO = new MessageBO();
        messageBO.setSenderUserId(message.getSenderUserId());
        messageBO.setRoomId(message.getRoomId());
        messageBO.setType(message.getType());
        messageBO.setStatus(message.getStatus());
        messageBO.setBody(message.getContent());
        return messageBO;
    }

    public List<MessageReturnBO> convert2MessageReturnBOList(List<Message> messageList) {
        if (CollectionsUtils.isNullOrEmpty(messageList)){
            return Collections.emptyList();
        }
        return messageList.stream().map(message->{
            MessageReturnBO messageReturnBO = new MessageReturnBO();
            messageReturnBO.setSenderUserId(message.getSenderUserId());
            messageReturnBO.setMessageId(message.getId());
            messageReturnBO.setRoomId(message.getRoomId());
            messageReturnBO.setSendTime(message.getCreateTime());
            messageReturnBO.setMessageType(message.getType());
            messageReturnBO.setBody(message.getContent());
            return messageReturnBO;
        }).collect(Collectors.toList());
    }
}
