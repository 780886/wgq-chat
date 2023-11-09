package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

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

    public QunMember convert2po(QunMemberBO qunMemberBO) {
        QunMember qunMember = new QunMember();
        qunMember.setQunId(qunMemberBO.getQunId());
        qunMember.setMemberId(qunMemberBO.getMemberId());
        qunMember.setAuditTime(qunMemberBO.getAuditTime());
        qunMember.setApplyTime(qunMemberBO.getApplyTime());
        return qunMember;
    }

    public QunMember convert2po(Long qunId,Long memberId) {
        QunMember qunMember = new QunMember();
        qunMember.setQunId(qunId);
        qunMember.setMemberId(memberId);
        qunMember.setApplyTime(System.currentTimeMillis());
        qunMember.setAuditTime(qunMember.getApplyTime());
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
