package com.wgq.chat.infrastructure.converter;

import com.wgq.chat.domain.netty.Protocol;
import com.wgq.chat.protocol.dto.MessageDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageDTO assembleMessage(Protocol protocol) {
        MessageDTO message = new MessageDTO();
        message.setMessageType(protocol.getMessageType());
        message.setChatType(protocol.getCharType());

        message.setContent(protocol.getContent());
        message.setSession(protocol.getChatSession().getSessionKey());
        message.setSender(protocol.getSender());
        message.setReceiver(protocol.getReceiver());
        message.setServerTime(protocol.getServerTime());
        message.setClientSendTime(protocol.getClientSendTime());
        return message;
    }
}
