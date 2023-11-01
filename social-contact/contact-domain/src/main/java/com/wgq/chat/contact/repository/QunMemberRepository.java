package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.AuditBO;

public interface QunMemberRepository {

    void addQunMember(Long qunId);

    void addQunMember(AuditBO auditBO);
}
