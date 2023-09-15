package com.wgq.chat.contact.bo;

import com.sheep.protocol.BO;

/**
 * @ClassName: FriendUnreadBO
 * @Author : wgq
 * @Date :2023/9/15  15:14
 * @Description:
 * @Version :1.0
 */
public class FriendUnreadBO implements BO {

    private Integer unReadCount;

    public FriendUnreadBO(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }
}
