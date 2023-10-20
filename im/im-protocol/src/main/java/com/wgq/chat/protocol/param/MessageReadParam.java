package com.wgq.chat.protocol.param;

import com.sheep.protocol.Param;

/**
 * @ClassName: MessageReadParam
 * @Author : wgq
 * @Date :2023/10/20  14:14
 * @Description:
 * @Version :1.0
 */
public class MessageReadParam implements Param {

    /**
     * 消息id
     */
    private Long messageId;
    /**
     * 查询消息类型(1已读2未读)
     */
    private Integer readStatus;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }
}
