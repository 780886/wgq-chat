package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName: MessageSendDTO
 * @Author : wgq
 * @Date :2023/8/23  14:50
 * @Description:
 * @Version :1.0
 */

public class MessageSendDTO implements DTO {

    private Long messageId;

    public MessageSendDTO() {
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
