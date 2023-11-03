package com.wgq.chat.rpc;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.assemble.RoomAssemble;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.domain.service.RoomService;
import com.wgq.chat.protocol.dto.RoomDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: RoomRpcServiceApi
 * @Author : wgq
 * @Date :2023/9/15  10:02
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomRpcServiceApi implements RoomServiceApi {

    @Inject
    private RoomService roomService;

    @Inject
    private RoomAssemble roomAssemble;

    @Override
    public Long createFriendRoom(List<Long> userList) throws BusinessException {
        return this.roomService.createFriendRoom(userList);
    }

    @Override
    public void disableFriendRoom(List<Long> userList) throws BusinessException {
        this.roomService.disableFriendRoom(userList);
    }

    @Override
    public Long createQunRoom(Long userId) {
        return this.roomService.createQunRoom(userId);
    }

    @Override
    public RoomDTO getRoom(Long roomId) throws BusinessException {
        RoomBO roomBO = this.roomService.getRoom(roomId);
        return this.roomAssemble.assembleRoomDTO(roomBO);
    }

    @Override
    public void dissolve(Long roomId) throws BusinessException {
        this.roomService.dissolve(roomId);
    }

}
