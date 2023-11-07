package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

import java.util.List;

/**
 * @ClassName: TextMessageDTO
 * @Author : wgq
 * @Date :2023/9/14  17:02
 * @Description:
 * @Version :1.0
 */
public class TextMessageDTO implements DTO {

    /**
     * 消息内容
     */
    private String content;

    /**
     * 回复的消息id,如果没有别传就好
     */
    private Long replyMessageId;

    /**
     * @的用户id集合 一次别@那么多人
     */
    private List<Long> atUserIdList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    public List<Long> getAtUserIdList() {
        return atUserIdList;
    }

    public void setAtUserIdList(List<Long> atUserIdList) {
        this.atUserIdList = atUserIdList;
    }
}
