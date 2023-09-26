package com.wgq.chat.infrastructure.persistence;

import com.sheep.enums.NormalOrNoEnum;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.dao.RoomFriendDao;
import com.wgq.chat.infrastructure.persistence.data.mapper.RoomFriendConverter;
import com.wgq.chat.po.RoomFriend;
import com.wgq.chat.repository.RoomFriendRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: RoomFriendRepositoryImpl
 * @Author : wgq
 * @Date :2023/9/15  10:15
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomFriendRepositoryImpl implements RoomFriendRepository {

    @Inject
    private RoomFriendDao roomFriendDao;

    @Inject
    private RoomFriendConverter roomFriendConverter;

    @Override
    public RoomFriendBO getByRoomKey(String roomKey) {
        RoomFriend roomFriend = this.roomFriendDao.getByRoomKey(roomKey);
        return this.roomFriendConverter.convert2RoomFriendBO(roomFriend);
    }

    @Override
    public void restoreRoom(Long id) {
        RoomFriend roomFriend = this.roomFriendConverter.convert2po(id);
        this.roomFriendDao.updateById(roomFriend);
    }

    @Override
    public Long createRoomFriend(Long roomId, List<Long> userList) {
        RoomFriend roomFriend = this.roomFriendConverter.convert2po(roomId, userList);
        this.roomFriendDao.insert(roomFriend);
        return roomFriend.getId();
    }

    @Override
    public void disableRoom(String roomKey) {
        this.roomFriendDao.disableRoom(roomKey, NormalOrNoEnum.NOT_NORMAL.getStatus());
    }

    @Override
    public RoomFriendBO getRoomFriend(Long roomId) {
        RoomFriend roomFriend = this.roomFriendDao.getByRoomId(roomId);
        return this.roomFriendConverter.convert2RoomFriendBO(roomFriend);
    }
}
