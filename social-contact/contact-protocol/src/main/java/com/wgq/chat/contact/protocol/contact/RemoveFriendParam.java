package com.wgq.chat.contact.protocol.contact;

import com.sheep.protocol.Param;

/**
 * @ClassName: RemoveFriendParam
 * @Author : wgq
 * @Date :2023/9/15  14:40
 * @Description:
 * @Version :1.0
 */
public class RemoveFriendParam implements Param {

    /**
     * 好友id
     */
    private Long friendId;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
