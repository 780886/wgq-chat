package com.wgq.chat.contact.protocol.qun;

import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;

/**
 * @ClassName InviteFriendParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:57
 * @Version 1.0
 **/
@ApiModel("邀请好友")
public class InviteFriendParam implements Param {

    private Long qunId;
    private Long  friendId;

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
