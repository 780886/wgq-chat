package com.wgq.chat.vo;

/**
 * @ClassName: MessageVO
 * @Author : wgq
 * @Date :2023/8/23  9:54
 * @Description:
 * @Version :1.0
 */
public class MessageReturnVO {

    /**
     * 消息类型
     */
    private Integer messageType;
    private Integer chatType;
    /**
     * 发送者
     */
    private Integer sender;
    /**
     * 接收者
     */
    private Integer receiver;
    //文本类型
    private Object data;
    private Long serverTime;
    private Long clientSendTime;

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public Long getClientSendTime() {
        return clientSendTime;
    }

    public void setClientSendTime(Long clientSendTime) {
        this.clientSendTime = clientSendTime;
    }
}
