package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendBO;
import com.wgq.chat.contact.po.Contact;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class ContactConverter {


    public Contact convert2po(AuditBO auditBO) {
        Contact contact = new Contact();
        contact.setUserId(auditBO.getApplyUserId());
        contact.setFriendId(auditBO.getAuditUserId());
        contact.setApplyTime(auditBO.getApplyTime());
        contact.setAuditTime(System.currentTimeMillis());
        return contact;
    }

    public FriendBO convert2FriendBO(Contact contact) {
        if (Objects.isNull(contact)){
            return null;
        }
        FriendBO friendBO = new FriendBO();
        friendBO.setContactId(contact.getId());
        friendBO.setUserId(contact.getUserId());
        friendBO.setFriendId(contact.getFriendId());
        return friendBO;
    }

    public List<FriendBO> convert2FriendBOList(List<Contact> contactList) {
        return contactList.stream().map(contact->{
            FriendBO friendBO = new FriendBO();
            friendBO.setContactId(contact.getId());
            friendBO.setUserId(contact.getUserId());
            friendBO.setFriendId(contact.getFriendId());
            return friendBO;
        }).collect(Collectors.toList());
    }
}
