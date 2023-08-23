package com.wgq.chat.protocol.param;

/**
 * @ClassName: MessageSendParam
 * @Author : wgq
 * @Date :2023/8/23  9:56
 * @Description:
 * @Version :1.0
 */
public class MessageSendParam {


    private Long roomId;


    private Integer msgType;


    private Object body;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
