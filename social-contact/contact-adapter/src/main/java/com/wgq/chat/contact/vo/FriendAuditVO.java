package com.wgq.chat.contact.vo;

import com.sheep.protocol.enums.StatusRecord;

public class FriendAuditVO {
    /**
     * 审核记录ID
     */
    private Long auditId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 审核状态
     */
    private StatusRecord auditStatus;

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public StatusRecord getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(StatusRecord auditStatus) {
        this.auditStatus = auditStatus;
    }
}
