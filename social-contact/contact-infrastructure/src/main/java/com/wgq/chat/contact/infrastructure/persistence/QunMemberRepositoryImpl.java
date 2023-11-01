package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.dao.QunMemberDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunMemberConverter;
import com.wgq.chat.contact.po.QunMember;
import com.wgq.chat.contact.repository.QunMemberRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class QunMemberRepositoryImpl implements QunMemberRepository {

    @Inject
    private QunMemberConverter qunMemberConverter;

    @Inject
    private QunMemberDao qunMemberDao;

    @Override
    public void addQunMember(Long qunId) {
        QunMember qunMember = this.qunMemberConverter.convert2po(qunId);
        this.qunMemberDao.insert(qunMember);
    }

    @Override
    public void addQunMember(AuditBO auditBO) {
        QunMember qunMember = this.qunMemberConverter.convert2po(auditBO);
        this.qunMemberDao.insert(qunMember);
    }
}
