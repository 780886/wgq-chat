package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendBO;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;

import java.util.List;

public interface ContactRepository {

    Long addContact(AuditBO auditBO,FriendAuditParam friendAuditParam);

    FriendBO findContact(Long userId, Long friendId);

    Long removeById(Long contactId);

    List<Long> getContacts();

}
