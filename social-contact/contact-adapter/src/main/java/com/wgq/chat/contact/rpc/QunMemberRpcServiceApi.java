package com.wgq.chat.contact.rpc;

import com.wgq.chat.contact.assembler.QunMemberAssemble;
import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.protocol.contact.dto.QunMemberDTO;
import com.wgq.chat.contact.service.QunMemberService;
import com.wgq.chat.cpntact.QunMemberServiceApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName QunMemberRpcServiceApi
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 16:41
 * @Version 1.0
 **/
@Named
public class QunMemberRpcServiceApi implements QunMemberServiceApi {

    @Inject
    private QunMemberService qunMemberService;
    @Inject
    private QunMemberAssemble qunMemberAssemble;
    @Override
    public List<QunMemberDTO> getQunMembersByQunId(Long qunId) {
        List<QunMemberBO> qunMemberBOList = this.qunMemberService.getQunMembersByQunId(qunId);
        return this.qunMemberAssemble.assembleQunMemberDTOList(qunMemberBOList);
    }
}
