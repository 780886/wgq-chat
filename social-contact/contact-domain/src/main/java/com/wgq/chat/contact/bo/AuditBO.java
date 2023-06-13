package com.wgq.chat.contact.bo;

/**
 * @ClassName AuditBo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/11 23:10
 * @Version 1.0
 **/
public class AuditBO {


    /**
     * 申请用户id
     */
    private Long applyUserId;

    /**
     * 审核标识
     */
    private Long auditId;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
