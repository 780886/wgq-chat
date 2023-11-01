package com.wgq.chat.contact.protocol.qun;

import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName InviteFriendParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:57
 * @Version 1.0
 **/
@ApiModel("邀请好友")
public class InviteFriendParam implements Param {

    @ApiModelProperty("房间id")
    private Long roomId;

    @ApiModelProperty("好友ID")
    private Long friendId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
