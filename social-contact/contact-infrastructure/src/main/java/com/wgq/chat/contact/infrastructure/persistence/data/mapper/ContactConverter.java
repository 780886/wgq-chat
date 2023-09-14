package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendBO;
import com.wgq.chat.contact.po.Contact;

import javax.inject.Named;

@Named
public class ContactConverter {


    public Contact convert2po(AuditBO auditBO) {
        Contact contact = new Contact();
        contact.setUserId(auditBO.getApplyUserId());
        contact.setFriendId(auditBO.getBusinessId());
        contact.setApplyTime(auditBO.getApplyTime());
        contact.setAuditTime(System.currentTimeMillis());
        return contact;
    }

    public FriendBO convert2FriendBO(Contact contact) {
        FriendBO friendBO = new FriendBO();
        friendBO.setUserId(contact.getUserId());
        friendBO.setFriendId(contact.getFriendId());
        return friendBO;
    }
}
