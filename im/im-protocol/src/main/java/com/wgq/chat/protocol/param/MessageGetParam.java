package com.wgq.chat.protocol.param;

import com.sheep.protocol.Param;

public class MessageGetParam implements Param {
    /**
     * 房间id
     */
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
