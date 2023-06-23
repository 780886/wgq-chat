package com.wgq.chat.contact.protocol.qun;

import com.sheep.protocol.Param;

/**
 * @ClassName RemoveMemberParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:52
 * @Version 1.0
 **/
public class RemoveMemberParam implements Param {

    private long qunId;
    private long memberId;

    public long getQunId() {
        return qunId;
    }

    public void setQunId(long qunId) {
        this.qunId = qunId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
