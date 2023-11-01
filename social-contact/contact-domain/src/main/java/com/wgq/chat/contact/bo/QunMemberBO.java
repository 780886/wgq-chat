package com.wgq.chat.contact.bo;

import com.sheep.protocol.BO;

/**
 * @ClassName: QunMemberBO
 * @Author : wgq
 * @Date :2023/11/1  16:26
 * @Description:
 * @Version :1.0
 */
public class QunMemberBO implements BO {

    private Long id;
    private Long qunId;
    private Long memberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
