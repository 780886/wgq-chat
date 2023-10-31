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


    public Contact convert2MyPO(Long roomId,AuditBO auditBO) {
        Contact contact = new Contact();
        contact.setRoomId(roomId);
        contact.setUserId(auditBO.getApplyUserId());
        contact.setFriendId(auditBO.getAuditUserId());
        contact.setApplyTime(auditBO.getApplyTime());
        contact.setAuditTime(auditBO.getAuditTime());
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

    public Contact convert2FriendPO(Long roomId, AuditBO auditBO) {
        Contact contact = new Contact();
        contact.setRoomId(roomId);
        contact.setUserId(auditBO.getAuditUserId());
        contact.setFriendId(auditBO.getApplyUserId());
        contact.setApplyTime(auditBO.getApplyTime());
        contact.setAuditTime(auditBO.getAuditTime());
        return contact;
    }
}
