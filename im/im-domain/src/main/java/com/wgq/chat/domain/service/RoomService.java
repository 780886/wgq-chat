package com.wgq.chat.domain.service;

import com.sheep.enums.NormalOrNoEnum;
import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.utils.CollectionsUtils;
import com.sheep.utils.StringUtils;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.protocol.enums.RoomTypeEnum;
import com.wgq.chat.repository.RoomFriendRepository;
import com.wgq.chat.repository.RoomRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RoomService
 * @Author : wgq
 * @Date :2023/9/15  10:08
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomService {


    @Inject
    private RoomFriendRepository roomFriendRepository;

    @Inject
    private RoomRepository roomRepository;

    public Long createFriendRoom(List<Long> userList) throws BusinessException {
        //"房间创建失败，好友数量不对"
        Asserts.isTrue(CollectionsUtils.isNullOrEmpty(userList), BusinessCodeEnum.FRIEND_NUMBER_NOT_MATCH);
        //"房间创建失败，好友数量不对"
        Asserts.isTrue(userList.size() != 2, BusinessCodeEnum.FRIEND_NUMBER_NOT_MATCH);
        //生产房间key 房间key由两个uid拼接，先做排序userId1_userId2
        String roomKey = StringUtils.join(userList, Symbol.COMMA);

        RoomFriendBO roomFriendBO = this.roomFriendRepository.getByRoomKey(roomKey);
        if (Objects.nonNull(roomFriendBO)){//如果存在房间就恢复，适用于恢复好友场景
            this.restoreRoomIfNeed(roomFriendBO);
            return roomFriendBO.getRoomId();
        }else {//新建房间
            Long id = this.roomRepository.createRoom(RoomTypeEnum.FRIEND);
            this.roomFriendRepository.createRoomFriend(id,  userList);
            return id;
        }
    }

    public void restoreRoomIfNeed(RoomFriendBO roomFriendBO) {
        if (Objects.equals(roomFriendBO.getStatus(), NormalOrNoEnum.NOT_NORMAL.getStatus())){
            this.roomFriendRepository.restoreRoom(roomFriendBO.getId());
        }
    }

    public void disableFriendRoom(List<Long> userList) throws BusinessException {
        //"房间创建失败，好友数量不对"
        Asserts.isTrue(CollectionsUtils.isNullOrEmpty(userList), BusinessCodeEnum.FRIEND_NUMBER_NOT_MATCH);
        //"房间创建失败，好友数量不对"
        Asserts.isTrue(!Objects.equals(userList.size(),2) , BusinessCodeEnum.FRIEND_NUMBER_NOT_MATCH);
        String roomKey = StringUtils.join(userList, Symbol.COMMA);
        this.roomFriendRepository.disableRoom(roomKey);
    }

    public Long createQunRoom(Long userId) {
        return this.roomRepository.createRoom(RoomTypeEnum.GROUP);
    }

    public RoomBO getRoom(Long roomId) throws BusinessException {
        Asserts.isTrue(Objects.isNull(roomId),BusinessCodeEnum.ROOM_ID_IS_EMPTY);
        RoomBO room = this.roomRepository.getRoom(roomId);
        return room;
    }

    public void dissolve(Long roomId) throws BusinessException {
        Asserts.isTrue(Objects.isNull(roomId),BusinessCodeEnum.ROOM_ID_IS_EMPTY);
        this.roomRepository.dissolve(roomId);
    }


}
