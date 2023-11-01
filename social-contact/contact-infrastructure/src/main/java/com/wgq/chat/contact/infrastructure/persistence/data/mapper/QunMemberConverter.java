package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.po.QunMember;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class QunMemberConverter {

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

    public List<QunMemberBO> convert2BOList(List<QunMember> qunMemberList) {
        if (CollectionsUtils.isNullOrEmpty(qunMemberList)){
            return Collections.emptyList();
        }
        return qunMemberList.stream().map(this::convert2bo).collect(Collectors.toList());
    }

    public QunMemberBO convert2bo(QunMember qunMember) {
        QunMemberBO qunMemberBO = new QunMemberBO();
        qunMemberBO.setId(qunMember.getId());
        qunMemberBO.setQunId(qunMember.getQunId());
        qunMemberBO.setMemberId(qunMember.getMemberId());
        return qunMemberBO;
    }
}
