package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.QunMemberBO;

import java.util.List;

public interface QunMemberRepository {

    void addQunMember(Long qunId);

    void addQunMember(AuditBO auditBO);

    void dissolve(Long qunId);

    List<QunMemberBO> getQunMembers(Long memberId);
}
