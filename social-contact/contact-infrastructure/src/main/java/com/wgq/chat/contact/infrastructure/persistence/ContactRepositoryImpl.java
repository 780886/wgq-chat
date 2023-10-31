package com.wgq.chat.contact.infrastructure.persistence;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendBO;
import com.wgq.chat.contact.dao.ContactDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.ContactConverter;
import com.wgq.chat.contact.po.Contact;
import com.wgq.chat.contact.repository.ContactRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ContactRepositoryImpl implements ContactRepository {

    @Inject
    private ContactConverter contactConverter;

    @Inject
    private ContactDao contactDao;

    @Override
    public void addContact(Long roomId,AuditBO auditBO) {
        Contact myContact = this.contactConverter.convert2MyPO(roomId,auditBO);
        this.contactDao.insert(myContact);
        Contact friendContact = this.contactConverter.convert2FriendPO(roomId,auditBO);
        this.contactDao.insert(friendContact);
    }

    @Override
    public FriendBO findContact(Long userId, Long friendId) {
        Contact contact = this.contactDao.getContact(userId,friendId);
        return this.contactConverter.convert2FriendBO(contact);
    }

    @Override
    public Long removeById(Long id) {
        return this.contactDao.removeById(id);
    }

    @Override
    public List<Long> getContacts() {
        LoginUser loginUser = ThreadContext.getLoginToken();
        List<Contact> contacts = this.contactDao.getMyContact(loginUser.getUserId());
        if (CollectionsUtils.isNullOrEmpty(contacts)) {
            return null;
        }
        List<Long> contacIdList = new ArrayList<>(contacts.size());
        for (Contact contact : contacts) {
            contacIdList.add(contact.getFriendId());
        }
        return contacIdList;
    }

    @Override
    public void refreshOrCreateLastTime(Long roomId, List<Long> memberUserList, Long messageId, Long lastSendTime) {
        this.contactDao.refreshOrCreateLastTime(roomId,memberUserList, messageId, lastSendTime);
    }

}
