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
        QunMember qunMember = new QunMember();
        qunMember.setQunId(auditBo.getBusinessId());
        qunMember.setMemberId(auditBo.getApplyUserId());
        qunMember.setAuditTime(System.currentTimeMillis());
        qunMember.setApplyTime(auditBo.getApplyTime());
        return qunMember;
    }

    public QunMember convert2po(Long qunId) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        QunMember qunMember = new QunMember();
        qunMember.setQunId(qunId);
        qunMember.setMemberId(loginUser.getUserId());
        qunMember.setApplyTime(qunMember.getApplyTime());
        qunMember.setAuditTime(System.currentTimeMillis());
        return qunMember;
    }
}
