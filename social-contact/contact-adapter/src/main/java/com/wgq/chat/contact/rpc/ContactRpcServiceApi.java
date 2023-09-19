package com.wgq.chat.contact.rpc;

import com.wgq.chat.contact.service.ContactService;
import com.wgq.chat.cpntact.ContactServiceApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
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
    public void refreshOrCreateActiveTime(Long roomId, List<Long> memberUserList, Long messageId, Date activeTime) {
        this.contactService.refreshOrCreateActiveTime(roomId, memberUserList, messageId, activeTime);
    }
}
