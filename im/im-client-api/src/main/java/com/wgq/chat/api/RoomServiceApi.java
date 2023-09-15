package com.wgq.chat.api;

import com.sheep.protocol.BusinessException;

import java.util.List;

/**
 * @ClassName: RoomServiceApi
 * @Author : wgq
 * @Date :2023/9/15  9:58
 * @Description:
 * @Version :1.0
 */
public interface RoomServiceApi {

    /**
     * 创建一个单聊房间
     */
    Long createFriendRoom(List<Long> userList) throws BusinessException;

    /**
     * 禁用一个单聊房间
     * @param userList
     */
    void disableFriendRoom(List<Long> userList) throws BusinessException;
}
