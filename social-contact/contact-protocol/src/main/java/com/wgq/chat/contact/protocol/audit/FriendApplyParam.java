package com.wgq.chat.contact.protocol.audit;


import com.sheep.protocol.POJO;

/**
 * 好友申请
 */
public class FriendApplyParam implements POJO {
    /**
     * 用户的密秘标识
     */
    private String friendSecretIdentify;
    /**
     * 申请的理由
     */
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
