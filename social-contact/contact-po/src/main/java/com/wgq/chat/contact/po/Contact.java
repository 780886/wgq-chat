package com.wgq.chat.contact.po;


import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.*;

@Table(name = "contact")
public class Contact implements POJO {

    private Long id;
    private Long roomId;
    private Long userId;
    private Long friendId;
    private Long lastMessageId;
    private Long lastSendTime;
    private Long applyTime;
    private Long auditTime;

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
            name = "user_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '用户ID'",
            nullable = false,
            updatable = false
    )
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @MethodOrder(order =4)
    @Column(
            name = "friend_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '好友ID'",
            nullable = false,
            updatable = false
    )
    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
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
            name = "apply_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '申请时间'",
            nullable = false,
            updatable = false
    )
    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    @MethodOrder(order = 8)
    @Column(
            name = "audit_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '审核时间'",
            nullable = false,
            updatable = false
    )
    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

}
