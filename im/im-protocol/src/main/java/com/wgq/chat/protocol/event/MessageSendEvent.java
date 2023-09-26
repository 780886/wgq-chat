package com.wgq.chat.protocol.event;

import com.sheep.mq.MQEvent;

/**
 * @ClassName MessageSendEvent
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/26 11:01
 * @Version 1.0
 **/
public class MessageSendEvent implements MQEvent {

    private Long messageId;

    public MessageSendEvent() {
    }

    public MessageSendEvent(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
