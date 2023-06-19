package com.wgq.chat.contact.protocol.audit;


import com.sheep.protocol.POJO;
import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 好友申请
 */
@ApiModel("好友申请参数")
public class FriendApplyParam implements Param {

    @ApiModelProperty("用户的密秘标识")
    private String friendSecretIdentify;

    @ApiModelProperty("申请的理由")
    private String reason;

    public String getFriendSecretIdentify() {
        return friendSecretIdentify;
    }

    public void setFriendSecretIdentify(String friendSecretIdentify) {
        this.friendSecretIdentify = friendSecretIdentify;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
