package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName WebsocketInviteJoinQunDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/9 14:43
 * @Version 1.0
 **/
public class WebsocketInviteJoinQunDTO implements DTO {

    /**
     * 审核id
     */
    private Long auditId;
    /**
     * 返回内容
     */
    private String content;

    public WebsocketInviteJoinQunDTO(Long auditId, String content) {
        this.auditId = auditId;
        this.content = content;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
