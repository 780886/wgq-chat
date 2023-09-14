package com.wgq.chat.protocol.dto;

import com.wgq.chat.protocol.param.MessageSendParam;

/**
 * @ClassName: MessageSendParam
 * @Author : wgq
 * @Date :2023/8/23  9:56
 * @Description:
 * @Version :1.0
 */
public class MessageSendDTO extends MessageSendParam {

    private Long messageId;

    public MessageSendDTO() {
    }

    public MessageSendDTO(Long messageId,Long roomId, Integer messageType, Object body) {
        super(roomId, messageType, body);
        this.messageId = messageId;
    }

    public MessageSendDTO(Long roomId, Integer messageType, Object body) {
        super(roomId, messageType, body);
    }

    public MessageSendDTO(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
