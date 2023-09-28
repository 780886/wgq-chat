package com.wgq.chat.protocol.event;

import com.sheep.mq.MQEvent;

/**
 * @ClassName UserOnlineEvent
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/27 11:21
 * @Version 1.0
 **/
public class UserOnlineEvent implements MQEvent {
    /**
     * 推送的用户id
     */
    private Long userId;

    /**
     * 推送类型
     */
    private Integer pushType;

    /**
     *
     */
    private Integer websocketResponseType;

    /**
     * 用户事件
     */
    private UserProfileEvent userProfileEvent;

    public UserOnlineEvent() {
    }

    public UserOnlineEvent(Long userId, Integer pushType, Integer websocketResponseType, UserProfileEvent userProfileEvent) {
        this.userId = userId;
        this.pushType = pushType;
        this.websocketResponseType = websocketResponseType;
        this.userProfileEvent = userProfileEvent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public Integer getWebsocketResponseType() {
        return websocketResponseType;
    }

    public void setWebsocketResponseType(Integer websocketResponseType) {
        this.websocketResponseType = websocketResponseType;
    }

    public UserProfileEvent getUserProfileEvent() {
        return userProfileEvent;
    }

    public void setUserProfileEvent(UserProfileEvent userProfileEvent) {
        this.userProfileEvent = userProfileEvent;
    }

}
