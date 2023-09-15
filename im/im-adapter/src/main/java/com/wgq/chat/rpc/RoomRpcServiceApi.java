package com.wgq.chat.rpc;

import com.sheep.protocol.BusinessException;
import com.sheep.utils.StringUtils;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.domain.service.RoomService;

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

    @Override
    public Long createFriendRoom(List<Long> userList) throws BusinessException {
        return this.roomService.createFriendRoom(userList);
    }

    @Override
    public void disableFriendRoom(List<Long> userList) throws BusinessException {
        this.roomService.disableFriendRoom(userList);
    }
}
