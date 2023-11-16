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

    private Long qunId;
    private Long memberId;

    public RemoveMemberParam() {
    }

    public RemoveMemberParam(Long qunId, Long memberId) {
        this.qunId = qunId;
        this.memberId = memberId;
    }

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
