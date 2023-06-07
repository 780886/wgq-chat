package com.wgq.chat.contact.service.impl;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.service.SecretService;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AuditServiceImpl implements AuditService {

    @Inject
    private SecretService secretService;

    @Override
    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        Long userId = secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());

    }
}
