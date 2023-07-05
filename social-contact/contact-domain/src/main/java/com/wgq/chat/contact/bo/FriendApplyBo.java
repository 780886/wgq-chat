package com.wgq.chat.contact.bo;

/**
 * @ClassName FrendApplyBo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:19
 * @Version 1.0
 **/
public class FriendApplyBo {

    private Long currentUserId;
    private Long friendId;
    private String reason;


    public FriendApplyBo(Long currentUserId, Long friendId, String reason) {
        this.currentUserId = currentUserId;
        this.friendId = friendId;
        this.reason = reason;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
