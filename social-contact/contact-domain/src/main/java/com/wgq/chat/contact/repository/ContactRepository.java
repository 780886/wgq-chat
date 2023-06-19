package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;

public interface ContactRepository {

    Long addContact(AuditBO auditBO,FriendAuditParam friendAuditParam);
}
