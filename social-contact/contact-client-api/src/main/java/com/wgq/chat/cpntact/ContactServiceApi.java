package com.wgq.chat.cpntact;

import com.wgq.chat.contact.protocol.contact.dto.ContactDTO;

public interface ContactServiceApi {

    ContactDTO getContact(Long userId, Long roomId);
}
