package com.wgq.chat.domain.event;

import org.springframework.context.ApplicationEvent;



public class MessageSendEvent extends ApplicationEvent {

    private Long messageId;

    public MessageSendEvent(Object source, Long messageId) {
        super(source);
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }
}
