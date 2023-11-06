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
     * 房间id
     */
    private Long roomId;

    public WebsocketAgreeJoinQunDTO() {
    }

    public WebsocketAgreeJoinQunDTO(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
