package com.wgq.chat.contact.po;


import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "qun_member")
public class QunMember implements POJO {

    private Long id;
    private Long qunId;
    private Long memberId;
    private Integer roleType;
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
        name = "qun_id",
        columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '群ID'",
        nullable = false,
        updatable = false
    )
    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    @MethodOrder(order = 3)
    @Column(
        name = "member_id",
        columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '群成员ID'",
        nullable = false,
        updatable = false
    )
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @MethodOrder(order = 3)
    @Column(
            name = "role_type",
            columnDefinition = "int(11)  DEFAULT 3 COMMENT '角色类型'",
            nullable = false,
            updatable = false
    )
    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
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
