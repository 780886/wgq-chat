package com.wgq.chat.contact.bo;

import com.sheep.protocol.BO;

/**
 * @ClassName: FriendBO
 * @Author : wgq
 * @Date :2023/9/14  14:47
 * @Description:
 * @Version :1.0
 */
public class FriendBO implements BO {

    private Long contactId;
    private Long userId;
    private Long friendId;

    public FriendBO() {
    }

    public FriendBO(Long contactId, Long userId, Long friendId) {
        this.contactId = contactId;
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
