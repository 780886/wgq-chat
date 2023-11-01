package com.wgq.chat.contact.protocol.audit;

import com.sheep.protocol.POJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("加群审核")
public class QunAuditParam implements POJO {

    @ApiModelProperty("审核主键ID")
    private Long auditId;

    @ApiModelProperty("审核原因")
    private String reason;

    @ApiModelProperty("是否同意")
    private Boolean isAgree;

    public QunAuditParam() {
    }

    public QunAuditParam(Long auditId, String reason, Boolean isAgree) {
        this.auditId = auditId;
        this.reason = reason;
        this.isAgree = isAgree;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Boolean getAgree() {
        return isAgree;
    }

    public void setAgree(Boolean agree) {
        isAgree = agree;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
