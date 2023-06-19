package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.po.Contact;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;

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
}
