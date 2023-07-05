package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.contact.bo.AuditBO;
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

    public QunMember convert2po(AuditBO auditBo) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        QunMember qunMember = new QunMember();
        qunMember.setQunId(auditBo.getBusinessId());
        qunMember.setMemberId(loginUser.getUserId());
        qunMember.setAuditTime(System.currentTimeMillis());
        qunMember.setApplyTime(auditBo.getApplyTime());
        return qunMember;
    }
}
