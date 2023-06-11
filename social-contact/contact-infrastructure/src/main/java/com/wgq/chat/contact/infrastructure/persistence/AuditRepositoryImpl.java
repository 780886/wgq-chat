package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.AuditBo;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.dao.AuditDao;
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

    @Override
    public Boolean applyFriend(FriendApplyBo friendApplyBo) {
        return null;
    }

    @Override
    public List<AuditBo> getFriendAuditList(Long userId) {
        this.auditDao.getAudits(userId)
        return null;
    }
}
