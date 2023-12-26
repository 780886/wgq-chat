package com.wgq.chat.contact.protocol.qun;

import com.sheep.protocol.Param;

/**
 * @ClassName ExistQunParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/15 9:12
 * @Version 1.0
 **/
public class ExistQunParam implements Param {

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
