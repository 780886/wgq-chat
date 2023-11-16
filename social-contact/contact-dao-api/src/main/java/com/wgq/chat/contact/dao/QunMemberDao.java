package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.QunMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName QunMemberDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:12
 * @Version 1.0
 **/
public interface QunMemberDao {

    void remove(@Param("qunMember") QunMember qunMember);

    Long insert(@Param("qunMember") QunMember qunMember);

    QunMember getQunMemberByMemberId(@Param("qunId") Long qunId, @Param("memberId") Long memberId);

    void removeMember(@Param("qunId") Long qunId, @Param("memberId") Long memberId);

    void delete(@Param("qunId") Long qunId);

    Map<Long, Long> getQunsByMemberId(@Param("memberId") Long memberId);

    List<QunMember> getQunMembers(@Param("qunId") Long qunId);
}
