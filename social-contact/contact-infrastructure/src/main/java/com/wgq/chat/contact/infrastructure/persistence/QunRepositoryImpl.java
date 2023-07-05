package com.wgq.chat.contact.infrastructure.persistence;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.dao.ContactDao;
import com.wgq.chat.contact.dao.QunDao;
import com.wgq.chat.contact.dao.QunMemberDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.ContactConverter;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunConverter;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunMemberConverter;
import com.wgq.chat.contact.po.Contact;
import com.wgq.chat.contact.po.Qun;
import com.wgq.chat.contact.po.QunMember;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;
import com.wgq.chat.contact.protocol.qun.RemoveMemberOfQunParam;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class QunRepositoryImpl implements QunRepository {

    @Inject
    private QunDao qunDao;

    @Inject
    private QunConverter qunConverter;

    @Inject
    private QunMemberConverter qunMemberConverter;

    @Inject
    private QunMemberDao qunMemberDao;

    @Override
    public Long createQun(QunCreateParam qunCreateParam) {
        Qun qun = this.qunConverter.convert2po(qunCreateParam);
        return this.qunDao.insert(qun);
    }

    @Override
    public void modifyQun(QunModifyParam qunModifyParam) throws BusinessException {
        Qun oldQun = this.qunDao.getEntity(qunModifyParam.getQunId());
        Asserts.isTrue(oldQun == null, ContactError.QUN_NOT_FOUND);
        Asserts.isTrue(!StatusRecord.ENABLE.equals(oldQun.getStatus()), ContactError.QUN_STATUS_INVALID);
        Qun qun = this.qunConverter.convert2po(qunModifyParam);
        this.qunDao.update(qun);
    }

    @Override
    public QunBO getQunDetail(Long id) {
        Qun qun = this.qunDao.getEntity(id);
        return this.qunConverter.qun2QunBO(qun);
    }

    @Override
    public List<QunBO> getQunPlaza(Long categoryId) {
        List<Qun> quns = this.qunDao.getQuns(categoryId);
        return this.qunConverter.qunList2qunBOList(quns);
    }

    @Override
    public Long joinQun(AuditBO qunAuditBo) {
        QunMember qunMember = this.qunMemberConverter.convert2po(qunAuditBo);
        this.qunMemberDao.insert(qunMember);
        return qunMember.getId();
    }

    @Override
    public QunBO qunDetail(Long qunId) {
        return null;
    }

    @Override
    public Boolean isMember(Long qunId, Long memberId) {
        return this.qunMemberDao.isMember(qunId, memberId) > 0;
    }

    @Override
    public void removeMember(RemoveMemberOfQunParam removeMemberOfQunParam) {
        this.qunMemberDao.removeMember(removeMemberOfQunParam.getQunId(), removeMemberOfQunParam.getMemberId());
    }

    @Override
    public void dissolve(Long qunId) {
        this.qunDao.delete(qunId);
        this.qunMemberDao.delete(qunId);
    }

    @Override
    public void transfer(QunBO qunBO, Long newOwnerId) {
        Qun qun = this.qunConverter.convert2po(qunBO,newOwnerId);
        this.qunDao.updateById(qun);
    }
}
