package com.wgq.chat.contact.rpc;

import com.wgq.chat.contact.protocol.contact.dto.ContactDTO;
import com.wgq.chat.contact.service.ContactService;
import com.wgq.chat.cpntact.ContactServiceApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: ContactRpcServiceApi
 * @Author : wgq
 * @Date :2023/9/19  16:40
 * @Description:
 * @Version :1.0
 */
@Named
public class ContactRpcServiceApi implements ContactServiceApi {

    @Inject
    private ContactService contactService;

    @Override
    public void refreshOrCreateLastTime(Long roomId, List<Long> memberUserList, Long messageId, Long lastSendTime) {
        this.contactService.refreshOrCreateLastTime(roomId, memberUserList, messageId, lastSendTime);
    }

    @Override
    public ContactDTO getContact(Long userId, Long roomId) {
        return null;
    }

}
