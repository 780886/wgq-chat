package com.wgq.chat.contact.protocol.audit;

import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 加群
 */
@ApiModel("加群参数")
public class JoinQunParam implements Param {
    /**
     * 用户的密秘标识
     */
    @ApiModelProperty("房间id")
    private Long roomId;
    /**
     * 申请的理由
     */
    @ApiModelProperty("加群的理由")
    private String reason;

    public JoinQunParam() {
    }

    public JoinQunParam(Long roomId, String reason) {
        this.roomId = roomId;
        this.reason = reason;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
