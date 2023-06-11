package com.wgq.chat.contact.infrastructure.service;

import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;

import javax.annotation.Resource;

public class AuditServiceImpl implements AuditService {

    @Resource
    private SecretService secretService;

    @Override
    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {

        Long userId = secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());

    }
}
