package com.wgq.chat.repository;



import com.wgq.chat.protocol.ChatSession;

import java.util.List;

public interface SessionRepository {

    void saveSession(ChatSession session, Integer currentUserId);

    List<ChatSession> getSessions(Integer userId);
}
