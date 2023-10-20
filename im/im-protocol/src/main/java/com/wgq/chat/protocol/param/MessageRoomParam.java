package com.wgq.chat.protocol.param;

import com.sheep.protocol.Param;

/**
 * @ClassName: MessageRoomParam
 * @Author : wgq
 * @Date :2023/10/20  14:54
 * @Description:
 * @Version :1.0
 */
public class MessageRoomParam implements Param {

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
