package com.wgq.chat.contact.infrastructure.service;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.service.SecretService;

import javax.annotation.Resource;
import javax.inject.Named;

@Named
public class AuditServiceImpl implements AuditService {

    @Resource
    private SecretService secretService;

    @Override
    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        Long userId = secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());

    }
}
