package com.wgq.chat.contact.protocol.audit;

import com.sheep.protocol.POJO;
import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 好友的审核
 */
@ApiModel("好友审核参数")
public class FriendAuditParam implements Param {

    @ApiModelProperty("审核主键ID")
    private Long id;

    @ApiModelProperty("审核原因")
    private String reason;

    @ApiModelProperty("是否同意")
    private Boolean isAgree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
