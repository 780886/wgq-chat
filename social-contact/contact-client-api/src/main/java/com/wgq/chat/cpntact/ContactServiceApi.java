package com.wgq.chat.cpntact;

import com.wgq.chat.contact.protocol.contact.dto.ContactDTO;

import java.util.Date;
import java.util.List;

public interface ContactServiceApi {

    void refreshOrCreateActiveTime(Long roomId, List<Long> memberUserList, Long messageId, Date activeTime);

    ContactDTO getContact(Long userId, Long roomId);
}
