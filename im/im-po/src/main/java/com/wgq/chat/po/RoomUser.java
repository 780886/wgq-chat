package com.wgq.chat.po;

import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName: RoomUser
 * @Author : wgq
 * @Date :2023/10/31  14:50
 * @Description:
 * @Version :1.0
 */
public class RoomUser implements POJO {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 房间id
     */
    private Long roomId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 阅读时间
     */
    private Long readTime;
    /**
     * 会话中的最新消息id
     */
    private  Long lastMessageId;
    /**
     * 最新消息发送时间(只有普通会话需要维护)
     */
    private Long lastSendTime;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 更新时间
     */
    private Long gmtModified;

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
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '房间id'",
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
            name = "user_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '用户id'",
            nullable = false,
            updatable = false
    )
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @MethodOrder(order = 4)
    @Column(
            name = "read_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '阅读时间'",
            nullable = false,
            updatable = false
    )
    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    @MethodOrder(order = 5)
    @Column(
            name = "last_message_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '会话中的最后一条消息id'",
            nullable = false,
            updatable = false
    )
    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }
    @MethodOrder(order = 6)
    @Column(
            name = "last_send_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '最后消息发送时间(只有普通会话需要维护，全员会话不需要维护)'",
            nullable = false,
            updatable = false
    )
    public Long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    @MethodOrder(order = 7)
    @Column(
            name = "gmt_create",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '创建时间'",
            nullable = false,
            updatable = false
    )
    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @MethodOrder(order = 8)
    @Column(
            name = "gmt_modified",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '更新时间'",
            nullable = false
    )
    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
