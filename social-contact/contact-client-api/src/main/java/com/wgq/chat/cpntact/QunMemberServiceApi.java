package com.wgq.chat.cpntact;

import com.wgq.chat.contact.protocol.contact.dto.QunMemberDTO;

import java.util.List;

/**
 * @ClassName QunMemberServiceApi
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 16:41
 * @Version 1.0
 **/
public interface QunMemberServiceApi {
    List<QunMemberDTO> getQunMembersByQunId(Long qunId);
}
