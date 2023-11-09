package com.wgq.chat.contact.bo;

import com.sheep.protocol.BO;

/**
 * @ClassName: QunMemberBO
 * @Author : wgq
 * @Date :2023/11/1  16:26
 * @Description:
 * @Version :1.0
 */
public class QunMemberBO implements BO {

    private Long id;
    private Long qunId;
    private Long memberId;
    /**
     * 审核时间
     */
    private Long auditTime;
    /**
     * 申请时间
     */
    private Long applyTime;

    public QunMemberBO() {
    }

    public QunMemberBO(Long id, Long qunId, Long memberId, Long auditTime, Long applyTime) {
        this.id = id;
        this.qunId = qunId;
        this.memberId = memberId;
        this.auditTime = auditTime;
        this.applyTime = applyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }
}
