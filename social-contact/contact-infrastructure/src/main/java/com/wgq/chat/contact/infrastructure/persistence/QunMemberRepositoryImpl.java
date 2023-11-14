package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.dao.QunMemberDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunMemberConverter;
import com.wgq.chat.contact.po.QunMember;
import com.wgq.chat.contact.repository.QunMemberRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class QunMemberRepositoryImpl implements QunMemberRepository {

    @Inject
    private QunMemberConverter qunMemberConverter;

    @Inject
    private QunMemberDao qunMemberDao;

    @Override
    public void addQunMember(QunMemberBO qunMemberBO) {
        QunMember qunMember = this.qunMemberConverter.convert2po(qunMemberBO);
        this.qunMemberDao.insert(qunMember);
    }

    @Override
    public void dissolve(Long qunId) {
        this.qunMemberDao.delete(qunId);
    }

    @Override
    public List<QunMemberBO> getQunMembers(Long qunId) {
        List<QunMember> qunMemberList = this.qunMemberDao.getQunMembers(qunId);
        return this.qunMemberConverter.convert2BOList(qunMemberList);
    }
}
