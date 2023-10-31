package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendBO;

import java.util.List;

public interface ContactRepository {

    void addContact(Long roomId,AuditBO auditBO);

    FriendBO findContact(Long userId, Long friendId);

    Long removeById(Long contactId);

    List<Long> getContacts();

    void refreshOrCreateLastTime(Long roomId, List<Long> memberUserList, Long messageId, Long lastSendTime);
}
