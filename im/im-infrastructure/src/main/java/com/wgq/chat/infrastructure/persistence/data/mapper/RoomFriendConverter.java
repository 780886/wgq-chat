package com.wgq.chat.infrastructure.persistence.data.mapper;

import com.sheep.enums.NormalOrNoEnum;
import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.utils.StringUtils;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.po.RoomFriend;
import org.springframework.beans.BeanUtils;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: RoomFriendConverter
 * @Author : wgq
 * @Date :2023/9/15  11:00
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomFriendConverter {

    public RoomFriendBO convert2RoomFriendBO(RoomFriend roomFriend) {
        if (Objects.isNull(roomFriend)){
            return null;
        }
        RoomFriendBO roomFriendBO = new RoomFriendBO();
        BeanUtils.copyProperties(roomFriend, roomFriendBO);
        return roomFriendBO;
    }

    public RoomFriend convert2po(Long id) {
        RoomFriend roomFriend = new RoomFriend();
        roomFriend.setId(id);
        roomFriend.setStatus(NormalOrNoEnum.NORMAL.getStatus());
        return roomFriend;
    }

    public RoomFriend convert2po(Long roomId, List<Long> userList) {
        List<Long> userIds = userList.stream().sorted().collect(Collectors.toList());
        RoomFriend roomFriend = new RoomFriend();
        roomFriend.setRoomId(roomId);
        roomFriend.setSmallerUserId(userIds.get(0));
        roomFriend.setLargerUserId(userIds.get(1));
        roomFriend.setRoomKey(StringUtils.join(userIds, Symbol.COMMA));
        roomFriend.setStatus(NormalOrNoEnum.NORMAL.getStatus());
        return roomFriend;
    }
}
