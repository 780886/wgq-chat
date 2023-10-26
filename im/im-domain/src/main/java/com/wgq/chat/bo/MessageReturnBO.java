package com.wgq.chat.bo;

/**
 * @ClassName: MessageVO
 * @Author : wgq
 * @Date :2023/8/23  9:54
 * @Description:
 * @Version :1.0
 */
public class MessageReturnBO {

    /**
     * 消息发送者userId
     */
    private Long senderUserId;
    /**
     * 消息id
     */
    private Long messageId;
    /**
     * 房间id
     */
    private Long roomId;
    /**
     * 发送时间
     */
    private Long sendTime;
    /**
     * 消息类型
     */
    private Integer messageType;
    /**
     * 消息内容不同的消息类型
     */
    private Object body;

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
