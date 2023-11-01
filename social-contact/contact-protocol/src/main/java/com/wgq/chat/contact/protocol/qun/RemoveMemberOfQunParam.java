package com.wgq.chat.contact.protocol.qun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移除群成员")
public class RemoveMemberOfQunParam {


    @ApiModelProperty("房间id")
    private Long roomId;
    @ApiModelProperty("成员ID")
    private Long memberId;

    public RemoveMemberOfQunParam() {
    }

    public RemoveMemberOfQunParam(Long roomId, Long memberId) {
        this.roomId = roomId;
        this.memberId = memberId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
