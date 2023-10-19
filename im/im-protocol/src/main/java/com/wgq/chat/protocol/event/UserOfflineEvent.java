package com.wgq.chat.protocol.event;

import com.sheep.mq.MQEvent;

/**
 * @ClassName UserOfflineEvent
 * @Description TODO
 * @Author wgq
 * @Date 2023/10/17 22:35
 * @Version 1.0
 **/
public class UserOfflineEvent implements MQEvent {

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

    public UserOfflineEvent() {
    }

    public UserOfflineEvent(Integer pushType, Integer websocketResponseType, UserProfileEvent userProfileEvent) {
        this.pushType = pushType;
        this.websocketResponseType = websocketResponseType;
        this.userProfileEvent = userProfileEvent;
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
