package com.wgq.chat.contact.infrastructure.persistence;

import com.wgq.chat.contact.bo.ExistQunBO;
import com.wgq.chat.contact.dao.QunMemberDao;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunConverter;
import com.wgq.chat.contact.infrastructure.persistence.data.mapper.QunMemberConverter;
import com.wgq.chat.contact.po.QunMember;
import com.wgq.chat.contact.repository.QunMemberRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class QunMemberRepositoryImpl implements QunMemberRepository {

    @Inject
    private QunMemberConverter qunMemberConverter;

    @Inject
    private QunMemberDao qunMemberDao;

}
