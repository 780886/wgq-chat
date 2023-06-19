package com.wgq.chat.contact.po;


import com.sheep.protocol.POJO;
import com.sheep.protocol.enums.StatusRecord;


public class Audit implements POJO {

    /**
     * 主键 ID
     */
    private Long id;
    /**
     * 申请人ID
     */
    private Long applyUserId;
    /**
     * 业务类型  申请的群或者好友ID
     */
    private Integer businessType;
    /**
     * 业务ID  与业务类型对应
     * 如果是群，则为群ID
     * 如果是好友，则为好友ID
     */
    private Long businessId;
    /**
     * 申请时间
     */
    private Long applyTime;
    /**
     * 审核时间
     */
    private Long auditTime;
    /**
     * 审核人ID
     */
    private Long auditUserId;
    /**
     * 申请的理由
     */
    private String applyReason;
    /**
     * 审核的理由
     */
    private String auditReason;
    /**
     * 审核的状态 0 未审核 1 审核通过 2 审核不通过
     */
    private StatusRecord status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }



    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public StatusRecord getStatus() {
        return status;
    }

    public void setStatus(StatusRecord status) {
        this.status = status;
    }

}
