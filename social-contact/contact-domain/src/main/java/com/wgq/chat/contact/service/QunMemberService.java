package com.wgq.chat.contact.service;

import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.repository.QunMemberRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName QunMemberService
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 16:56
 * @Version 1.0
 **/
@Named
public class QunMemberService {

    @Inject
    private QunMemberRepository qunMemberRepository;

    public List<QunMemberBO> getQunMembersByQunId(Long qunId) {
        List<QunMemberBO> qunMembers = this.qunMemberRepository.getQunMembers(qunId);
        return qunMembers;
    }
}
