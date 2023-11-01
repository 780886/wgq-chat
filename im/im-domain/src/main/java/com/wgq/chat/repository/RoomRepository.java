package com.wgq.chat.repository;

import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.protocol.enums.RoomTypeEnum;

public interface RoomRepository {
    Long createRoom(RoomTypeEnum roomTypeEnum);

    RoomBO getRoom(Long roomId);

    void dissolve(Long roomId);
}
