package com.wgq.chat.domain.service;

import com.wgq.chat.cpntact.ContactServiceApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContactService
 * @Author : wgq
 * @Date :2023/9/19  16:35
 * @Description:
 * @Version :1.0
 */
@Named
public class ContactService {

    @Inject
    private ContactServiceApi contactServiceApi;

    public void refreshOrCreateActiveTime(Long roomId, List<Long> memberUserList, Long messageId, Date activeTime) {
        this.contactServiceApi.refreshOrCreateActiveTime(roomId,memberUserList, messageId, activeTime);
    }
}
