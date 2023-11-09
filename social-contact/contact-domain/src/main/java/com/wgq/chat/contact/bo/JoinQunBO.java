package com.wgq.chat.contact.bo;

import com.sheep.protocol.BO;

/**
 * @ClassName JoinQunBO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/9 15:54
 * @Version 1.0
 **/
public class JoinQunBO implements BO {

    /**
     * 用户的密秘标识
     */
    private Long roomId;
    /**
     * 申请用户id
     */
    private Long applyUserId;
    /**
     * 申请的理由
     */
    private String reason;

    public JoinQunBO(Long roomId, Long applyUserId, String reason) {
        this.roomId = roomId;
        this.applyUserId = applyUserId;
        this.reason = reason;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
