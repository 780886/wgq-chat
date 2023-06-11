package com.wgq.chat.contact.infrastructure.service;

import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;

public interface AuditService {
    void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException;

}
