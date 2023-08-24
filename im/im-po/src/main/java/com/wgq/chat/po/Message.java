package com.wgq.chat.po;

import com.sheep.protocol.POJO;

import java.util.Date;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author <a href="https://github.com/zongzibinbin">abin</a>
 * @since 2023-03-25
 */
public class Message implements POJO {



    /**
     * id
     */
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
     * 消息内容
     */
    private String content;

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
     * 与回复消息的间隔条数
     */
    private Integer gapCount;

    /**
     * 消息类型 1正常文本 2.撤回消息
     *
     * @see com.wgq.chat.protocol.enums.MessageTypeEnum
     */
    private Integer type;

    /**
     * 消息扩展字段
     */
//    private MessageExtra extra;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGapCount() {
        return gapCount;
    }

    public void setGapCount(Integer gapCount) {
        this.gapCount = gapCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
