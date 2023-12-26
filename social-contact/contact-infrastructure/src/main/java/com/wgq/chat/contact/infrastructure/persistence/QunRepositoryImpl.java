package com.wgq.chat.contact.infrastructure.persistence;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.dao.QunDao;
import com.wgq.chat.contact.dao.QunMemberDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunConverter;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunMemberConverter;
import com.wgq.chat.contact.po.Qun;
import com.wgq.chat.contact.po.QunMember;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;
import com.wgq.chat.contact.repository.QunRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Long createQun(QunBO qunCreateBO) {
        Qun qun = this.qunConverter.convert2po(qunCreateBO);
        this.qunDao.insert(qun);
        return qun.getId();
    }

    @Override
    public void modifyQun(QunModifyParam qunModifyParam) throws BusinessException {
        Qun oldQun = this.qunDao.getEntity(qunModifyParam.getQunId());
        Asserts.isTrue(Objects.isNull(oldQun), ContactError.QUN_NOT_FOUND);
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
    public QunBO qunDetail(Long qunId) {
        Qun qun = this.qunDao.getEntity(qunId);
        return this.qunConverter.po2Bo(qun);
    }

    @Override
    public Boolean isMember(Long qunId, Long memberId) {
        QunMember qunMember = this.qunMemberDao.getQunMemberByMemberId(qunId, memberId);
        return Objects.nonNull(qunMember);
    }

    @Override
    public void dissolve(Long roomId) {
        this.qunDao.delete(roomId);
    }

    @Override
    public void transfer(QunBO qunBO, Long newOwnerId) {
        Qun qun = this.qunConverter.convert2po(qunBO,newOwnerId);
        this.qunDao.updateById(qun);
    }

    @Override
    public List<QunBO> queryQunPlaza() {
        List<Qun> quns = this.qunDao.getQunsByStatus(StatusRecord.ENABLE);
        return this.qunConverter.poList2BoList(quns);
    }

    @Override
    public List<QunBO> getMyQunList() {
        LoginUser loginUser = ThreadContext.getLoginToken();
        Map<Long, Long> myQunIds = this.qunMemberDao.getQunsByMemberId(loginUser.getUserId());
        if (myQunIds == null || myQunIds.isEmpty()) {
            return null;
        }
        List<Qun> myQuns = this.qunDao.getQunsByIds(myQunIds.values());
        return this.qunConverter.poList2BoList(myQuns);
    }

    @Override
    public QunBO getOwnerQun(Long ownerId) {
        Qun qun = this.qunDao.getOwnerQun(ownerId);
        return this.qunConverter.qun2QunBO(qun);
    }

    @Override
    public QunBO qunDetailByRoomId(Long roomId) {
        Qun qun = this.qunDao.qunDetailByRoomId(roomId);
        return this.qunConverter.qun2QunBO(qun);
    }
}
