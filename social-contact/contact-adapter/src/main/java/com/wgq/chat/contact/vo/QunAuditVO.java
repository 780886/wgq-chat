package com.wgq.chat.contact.vo;

import com.sheep.protocol.VO;
import com.sheep.protocol.enums.StatusRecord;

/**
 * @ClassName: QunAuditVO
 * @Author : wgq
 * @Date :2023/11/2  10:02
 * @Description:
 * @Version :1.0
 */
public class QunAuditVO implements VO {
    /**
     * 审核记录ID
     */
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
