package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.QunMember;

/**
 * @ClassName QunMemberDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:12
 * @Version 1.0
 **/
public interface QunMemberDao {

    void remove(QunMember qunMember);

    Long insert(QunMember qunMember);

    Long isMember(Long qunId, Long memberId);

    void removeMember(Long qunId, Long memberId);

    void delete(Long qunId);
}
