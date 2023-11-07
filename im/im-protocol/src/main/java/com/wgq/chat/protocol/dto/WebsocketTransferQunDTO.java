package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName: WebsocketTransferQunDTO
 * @Author : wgq
 * @Date :2023/11/7  10:23
 * @Description:
 * @Version :1.0
 */
public class WebsocketTransferQunDTO implements DTO {

    /**
     * 新群主
     */
    private Long newOwnerId;

    public WebsocketTransferQunDTO(Long newOwnerId) {
        this.newOwnerId = newOwnerId;
    }

    public Long getNewOwnerId() {
        return newOwnerId;
    }

    public void setNewOwnerId(Long newOwnerId) {
        this.newOwnerId = newOwnerId;
    }
}
