package com.wgq.chat.contact.protocol.contact.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName QunMemberDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 16:43
 * @Version 1.0
 **/
public class QunMemberDTO implements DTO {

    private Long qunId;
    private Long memberId;

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
