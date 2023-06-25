package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.ExistQunBO;
import com.wgq.chat.contact.po.QunMember;

import javax.inject.Named;

@Named
public class QunMemberConverter {

    public QunMember convert2po(ExistQunBO existQunBO) {
        QunMember qunMember = new QunMember();
        qunMember.setQunId(existQunBO.getQunId());
        qunMember.setMemberId(existQunBO.getMemberId());
        return qunMember;
    }
}
