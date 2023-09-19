package com.wgq.chat.repository;

import com.wgq.chat.bo.RoomFriendBO;

import java.util.List;

public interface RoomFriendRepository {

    RoomFriendBO getByRoomKey(String key);

    void restoreRoom(Long id);

    Long createRoomFriend(Long roomId, List<Long> userList);

    void disableRoom(String roomKey);

    RoomFriendBO getRoomFriend(Long roomId);

}
