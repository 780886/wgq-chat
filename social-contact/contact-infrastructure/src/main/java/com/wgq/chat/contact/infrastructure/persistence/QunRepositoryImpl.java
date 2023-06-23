package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.dao.ContactDao;
import com.wgq.chat.contact.dao.QunDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.ContactConverter;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunConverter;
import com.wgq.chat.contact.po.Contact;
import com.wgq.chat.contact.po.Qun;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class QunRepositoryImpl implements QunRepository {

    @Inject
    private QunDao qunDao;

    @Inject
    private QunConverter qunConverter;

    @Override
    public Long createQun(QunCreateParam qunCreateParam) {
        Qun qun = this.qunConverter.convert2po(qunCreateParam);
        return this.qunDao.insert(qun);
    }
}
