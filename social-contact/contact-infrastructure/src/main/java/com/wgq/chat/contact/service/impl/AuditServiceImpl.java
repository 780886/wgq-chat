package com.wgq.chat.contact.service.impl;

import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.service.SecretService;

import javax.annotation.Resource;

public class AuditServiceImpl implements AuditService {

    @Resource
    private SecretService secretService;

    @Override
    public void applyFriend(FriendApplyParam friendApplyParam) {

        UserProfileDTO userProfileDTO = secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());

    }
}
