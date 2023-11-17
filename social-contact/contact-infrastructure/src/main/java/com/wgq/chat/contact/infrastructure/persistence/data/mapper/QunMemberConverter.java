package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.utils.CollectionsUtils;
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
        qunMember.setRoleType(qunMemberBO.getRoleType());
        qunMember.setAuditTime(qunMemberBO.getAuditTime());
        qunMember.setApplyTime(qunMemberBO.getApplyTime());
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

    public List<QunMember> convert2POList(List<QunMemberBO> memberBOList) {
       return memberBOList.stream().map(qunMemberBO->{
            QunMember qunMember = new QunMember();
            qunMember.setQunId(qunMemberBO.getQunId());
            qunMember.setMemberId(qunMemberBO.getMemberId());
            qunMember.setRoleType(qunMemberBO.getRoleType());
            return qunMember;
        }).collect(Collectors.toList());
    }
}
