package com.wgq.chat.protocol.param;

/**
 * @ClassName: MessageSendParam
 * @Author : wgq
 * @Date :2023/8/23  9:56
 * @Description:
 * @Version :1.0
 */
public class MessageSendParam {


    /**
     * 房间id
     */
    private Long roomId;
    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 消息内容不同的消息类型
     */
    private Object body;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
