package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * Description:
 * Author: wgq
 * Date: 2023-03-19
 */

public class WebsocketFriendApplyDTO implements DTO {

    private Long userId;

    private Integer unreadCount;

    public WebsocketFriendApplyDTO() {
    }

    public WebsocketFriendApplyDTO(Long userId, Integer unreadCount) {
        this.userId = userId;
        this.unreadCount = unreadCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }
}
