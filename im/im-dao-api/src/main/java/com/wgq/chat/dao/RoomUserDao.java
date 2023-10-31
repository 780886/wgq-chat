package com.wgq.chat.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomUserDao {
    void refreshOrCreateLastTime(@Param("roomId") Long roomId, @Param("userIdList") List<Long> userIdList, @Param("messageId") Long messageId, @Param("lastSendTime") Long lastSendTime);
}
