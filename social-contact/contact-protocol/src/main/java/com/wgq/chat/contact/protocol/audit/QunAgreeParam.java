package com.wgq.chat.contact.protocol.audit;

import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AgreeJoinQunParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/1 14:58
 * @Version 1.0
 **/
public class QunAgreeParam implements Param {

    /**
     * 审核id
     */
    private Long auditId;


    @ApiModelProperty("是否同意")
    private Boolean isAgree;



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
}
