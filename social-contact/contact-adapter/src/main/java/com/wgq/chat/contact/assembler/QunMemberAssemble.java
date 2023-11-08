package com.wgq.chat.contact.assembler;

import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.protocol.contact.dto.QunMemberDTO;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName QunMemberAssemble
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 16:57
 * @Version 1.0
 **/
@Named
public class QunMemberAssemble {
    public List<QunMemberDTO> assembleQunMemberDTOList(List<QunMemberBO> qunMemberBOList) {
        if (CollectionsUtils.isNullOrEmpty(qunMemberBOList)){
            return Collections.emptyList();
        }

        return qunMemberBOList.stream().map(bo->{
            QunMemberDTO qunMemberDTO = new QunMemberDTO();
            qunMemberDTO.setQunId(bo.getQunId());
            qunMemberDTO.setMemberId(bo.getMemberId());
            return qunMemberDTO;
        }).collect(Collectors.toList());
    }
}
