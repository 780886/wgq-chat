package com.wgq.chat.bo;

/**
 * @ClassName: MessageBO
 * @Author : wgq
 * @Date :2023/8/23  14:38
 * @Description:
 * @Version :1.0
 */
public class MessageBO {


    /**
     * 会话表id
     */
    private Long roomId;

    /**
     * 消息发送者uid
     */
    private Long senderUserId;


    /**
     * 消息状态 0正常 1删除
     *
     * @see com.abin.mallchat.common.chat.domain.enums.MessageStatusEnum
     */
    private Integer status;


    /**
     * 消息类型 1正常文本 2.撤回消息
     *
     * @see com.abin.mallchat.common.chat.domain.enums.MessageTypeEnum
     */
    private Integer messageType;

    /**
     * 消息内容不同的消息类型
     */
    private Object body;

    public MessageBO() {
    }

    public MessageBO(Long roomId, Long senderUserId, Integer status, Integer messageType,Object body) {
        this.roomId = roomId;
        this.senderUserId = senderUserId;
        this.status = status;
        this.messageType = messageType;
        this.body = body;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
