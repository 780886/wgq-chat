package com.wgq.chat.contact.bo;

import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;

/**
 * @ClassName AuditBo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/11 23:10
 * @Version 1.0
 **/
public class AuditBO {

    private Long auditId;

    private AuditBusiness auditBusiness;

    /**
     * 申请用户id
     */
    private Long applyUserId;
    /**
     * 审核标识
     */
    private Long id;
    /**
     * 业务ID  与业务类型对应
     * 如果是群，则为群ID
     * 如果是好友，则为好友ID
     */
    private Long businessId;
    /**
     * 审核人ID
     */
    private Long auditUserId;

    /**
     * 审核时间
     */
    private Long auditTime;
    /**
     * 申请时间
     */
    private Long applyTime;

    /**
     * 审核的理由
     */
    private String auditReason;

    /**
     * 申请的理由
     */
    private String applyReason;

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 审核状态
     */
    private StatusRecord auditStatus;

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public AuditBusiness getAuditBusiness() {
        return auditBusiness;
    }

    public void setAuditBusiness(AuditBusiness auditBusiness) {
        this.auditBusiness = auditBusiness;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusRecord getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(StatusRecord auditStatus) {
        this.auditStatus = auditStatus;
    }
}
