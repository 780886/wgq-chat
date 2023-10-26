package com.wgq.chat.po;

import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>
 * 消息表
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
    private String extra;

    /**
     * 消息发送时间
     */
    private Long sendTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED AUTO_INCREMENT")
    @MethodOrder(order = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @MethodOrder(order = 2)
    @Column(
            name = "room_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '会话表id'",
            nullable = false,
            updatable = false
    )
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @MethodOrder(order = 3)
    @Column(
            name = "sender_user_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '消息发送者用户id'",
            nullable = false,
            updatable = false
    )
    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    @MethodOrder(order = 4)
    @Column(
            name = "content",
            columnDefinition = "varchar(1024) DEFAULT '' COMMENT '消息内容'",
            nullable = false,
            updatable = false
    )
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @MethodOrder(order = 5)
    @Column(
            name = "reply_message_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '回复的消息id'",
            nullable = false,
            updatable = false
    )
    public Long getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    @MethodOrder(order = 6)
    @Column(
            name = "status",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '消息状态 0正常 1删除'",
            nullable = false,
            updatable = false
    )
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @MethodOrder(order = 7)
    @Column(
            name = "gap_count",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '与回复的消息间隔多少条'",
            nullable = false,
            updatable = false
    )
    public Integer getGapCount() {
        return gapCount;
    }

    public void setGapCount(Integer gapCount) {
        this.gapCount = gapCount;
    }

    @MethodOrder(order = 8)
    @Column(
            name = "type",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '消息类型 1正常文本 2.撤回消息'",
            nullable = false,
            updatable = false
    )
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @MethodOrder(order = 9)
    @Column(
            name = "extra",
            columnDefinition = "json COMMENT '扩展信息'",
            nullable = false,
            updatable = false
    )
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @MethodOrder(order = 10)
    @Column(
            name = "send_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '消息发送时间'",
            nullable = false,
            updatable = false
    )
    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

}
