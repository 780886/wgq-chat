package com.wgq.chat.contact.po;


import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.*;

@Table(name = "contact")
public class Contact implements POJO {

    private Long id;
    private Long userId;
    private Long friendId;
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

    @MethodOrder(order = 3)
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

    @MethodOrder(order = 4)
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

    @MethodOrder(order = 5)
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
