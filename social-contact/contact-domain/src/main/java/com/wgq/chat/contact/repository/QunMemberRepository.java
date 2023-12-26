package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.protocol.qun.RemoveMemberParam;

import java.util.List;

public interface QunMemberRepository {

    void addQunMember(QunMemberBO qunMemberBO);

    void dissolve(Long qunId);

    List<QunMemberBO> getQunMembers(Long qunId);

    void removeMember(RemoveMemberParam removeMemberParam);

    void updateBatchQunMemberRoleTypeByQunIdAndMemberId(List<QunMemberBO> memberBOList);
}
