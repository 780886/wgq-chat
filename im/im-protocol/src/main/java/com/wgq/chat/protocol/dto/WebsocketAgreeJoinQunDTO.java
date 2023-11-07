package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName WebsocketAgreeJoinQunDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/6 15:01
 * @Version 1.0
 **/
public class WebsocketAgreeJoinQunDTO implements DTO {
    /**
     * 群主id
     */
    private Long ownerId;

    public WebsocketAgreeJoinQunDTO(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
