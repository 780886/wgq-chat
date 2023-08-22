package com.wgq.chat.protocol.dto;

/**
 * Description: 记录和前端连接的一些映射信息
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-21
 */

public class ChannelExtraDTO {
    /**
     * 前端如果登录了，记录userId
     */
    private Long userId;

    public ChannelExtraDTO() {
    }

    public ChannelExtraDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
