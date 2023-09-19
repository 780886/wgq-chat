package com.wgq.chat.dao;

import com.wgq.chat.po.RoomFriend;
import org.apache.ibatis.annotations.Param;

public interface RoomFriendDao {

    RoomFriend getByRoomKey(@Param("roomKey") String roomKey);

    Long updateById(@Param("roomFriend") RoomFriend roomFriend);

    Long insert(@Param("roomFriend") RoomFriend roomFriend);

    void disableRoom(@Param("roomKey") String roomKey, @Param("status") Integer status);

    RoomFriend getByRoomId(@Param("roomId") Long roomId);


}
