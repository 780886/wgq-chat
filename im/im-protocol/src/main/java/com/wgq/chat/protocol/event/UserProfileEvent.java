package com.wgq.chat.protocol.event;

import com.sheep.mq.MQEvent;

/**
 * @ClassName: UserProfileEvent
 * @Author : wgq
 * @Date :2023/9/27  10:47
 * @Description:
 * @Version :1.0
 */
public class UserProfileEvent implements MQEvent {

    private Long userId;
    private Long lastLoginTime;
    private Long ip;
    private Long gmtModified;

    public UserProfileEvent() {
    }

    public UserProfileEvent(Long userId, Long lastLoginTime, Long ip, Long gmtModified) {
        this.userId = userId;
        this.lastLoginTime = lastLoginTime;
        this.ip = ip;
        this.gmtModified = gmtModified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
