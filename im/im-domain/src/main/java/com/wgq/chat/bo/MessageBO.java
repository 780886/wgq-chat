package com.wgq.chat.bo;

/**
 * @ClassName: MessageBO
 * @Author : wgq
 * @Date :2023/8/23  14:38
 * @Description:
 * @Version :1.0
 */
public class MessageBO {

    private Long id;
    /**
     * 会话表id
     */
    private Long roomId;

    /**
     * 消息发送者uid
     */
    private Long senderUserId;
    /**
     * 回复的消息内容
     */
    private Long replyMessageId;


    /**
     * 消息状态 0正常 1删除
     *
     * @see com.wgq.chat.protocol.enums.MessageStatusEnum
     */
    private Integer status;


    /**
     * 消息类型 1正常文本 2.撤回消息
     *
     * @see com.wgq.chat.protocol.enums.MessageTypeEnum
     */
    private Integer type;

    private Integer gapCount;

    /**
     * 消息内容不同的消息类型
     */
    private String content;

    /**
     * 创建时间
     */
    private Long sendTime;

    public MessageBO() {
    }

    public MessageBO(Long id,Long roomId, Long senderUserId, Long replyMessageId,Integer status, Integer type,String content,Integer gapCount,Long sendTime) {
        this.id = id;
        this.roomId = roomId;
        this.senderUserId = senderUserId;
        this.replyMessageId = replyMessageId;
        this.status = status;
        this.type = type;
        this.content = content;
        this.gapCount = gapCount;
        this.sendTime = sendTime;
    }

    public Long getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGapCount() {
        return gapCount;
    }

    public void setGapCount(Integer gapCount) {
        this.gapCount = gapCount;
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

    public void setType(Integer type) {
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

}
