package com.wgq.chat.contact.service;

import com.wgq.chat.contact.protocol.audit.FriendApplyParam;

public interface AuditService {
    void applyFriend(FriendApplyParam friendApplyParam);

}
