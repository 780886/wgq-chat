package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.dao.AuditDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.AuditConverter;
import com.wgq.chat.contact.po.Audit;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.repository.AuditRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName AuditRepositoryImpl
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/11 23:15
 * @Version 1.0
 **/
@Named
public class AuditRepositoryImpl  implements AuditRepository {

    @Inject
    private AuditDao auditDao;

    @Inject
    private AuditConverter auditConverter;

    @Override
    public Boolean applyFriend(FriendApplyBo friendApplyBo) {
        Audit audit = this.auditConverter.convert2po(friendApplyBo);
        return this.auditDao.insert(audit);
    }

    @Override
    public List<AuditBO> getFriendAuditList(Long auditUserId) {
        List<Audit> audits = this.auditDao.getAudits(auditUserId);
        return this.auditConverter.auditList2AuditBOList(audits);
    }

    @Override
    public Integer auditFriend(AuditBO auditBO,FriendAuditParam friendAuditParam) {
        Audit audit = this.auditConverter.convert2po(auditBO, friendAuditParam);
        return this.auditDao.update(audit);
    }

    @Override
    public AuditBO getAudit(Long id) {
        Audit audit = this.auditDao.getEntity(id);
        return this.auditConverter.audit2AuditBo(audit);
    }
}
