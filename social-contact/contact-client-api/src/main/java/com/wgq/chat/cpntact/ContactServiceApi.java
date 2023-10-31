package com.wgq.chat.cpntact;

import com.wgq.chat.contact.protocol.contact.dto.ContactDTO;

import java.util.List;

public interface ContactServiceApi {

    void refreshOrCreateLastTime(Long roomId, List<Long> memberUserList, Long messageId, Long lastSendTime);

    ContactDTO getContact(Long userId, Long roomId);
}
