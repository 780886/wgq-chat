package com.wgq.chat.contact.protocol.audit;

import com.wgq.chat.contact.protocol.Param;

/**
 * 群申请
 */
public class QunApplyParam implements Param {
    /**
     * 群ID
     */
    private Long qunId;
    /**
     * 申请的理由F
     */
    private String reason;

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
