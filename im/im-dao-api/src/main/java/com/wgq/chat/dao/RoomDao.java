package com.wgq.chat.dao;

import com.wgq.chat.po.Room;
import org.apache.ibatis.annotations.Param;

public interface RoomDao {
    Long insert(Room room);

    Room getById(@Param("id") Long id);

}
