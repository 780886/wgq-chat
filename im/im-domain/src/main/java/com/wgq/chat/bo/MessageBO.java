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
    private Integer type;

    public MessageBO() {
    }

    public MessageBO(Long roomId, Long senderUserId, Integer status, Integer type) {
        this.roomId = roomId;
        this.senderUserId = senderUserId;
        this.status = status;
        this.type = type;
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

    public Integer getType() {
        return type;
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

    public void setType(Integer type) {
        this.type = type;
    }
}
