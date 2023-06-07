package com.wgq.chat.protocol.dto;

import com.sheep.protocol.VO;
import com.wgq.chat.protocol.ChatSession;

import java.util.List;

public class SessionDTO implements VO {
    private ChatSession chatSession;
    private List<MessageDTO> messages;
    private Long lastReadTime=0L;


    public SessionDTO(ChatSession chatSession, List<MessageDTO> messages) {
        this.chatSession = chatSession;
        this.messages = messages;
        this.lastReadTime = 0L;
    }

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public Long getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }
}
