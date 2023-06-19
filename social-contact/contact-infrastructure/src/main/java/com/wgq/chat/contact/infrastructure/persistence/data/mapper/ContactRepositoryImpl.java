package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.dao.ContactDao;
import com.wgq.chat.contact.po.Contact;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.repository.ContactRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ContactRepositoryImpl implements ContactRepository {

    @Inject
    private ContactConverter contactConverter;

    @Inject
    private ContactDao contactDao;

    @Override
    public Long addContact(AuditBO auditBO,FriendAuditParam friendAuditParam) {
        Contact contact = this.contactConverter.convert2po(auditBO);
        this.contactDao.insert(contact);
        return contact.getId();
    }
}
