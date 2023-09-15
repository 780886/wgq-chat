package com.wgq.chat.contact.assembler;

import com.sheep.protocol.VO;

/**
 * @ClassName: FriendUnreadVO
 * @Author : wgq
 * @Date :2023/9/15  15:15
 * @Description:
 * @Version :1.0
 */
public class FriendUnreadVO implements VO {

    private Integer unReadCount;

    public FriendUnreadVO(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }
}
