package com.wgq.chat.contact.service.impl;

import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.service.SecretService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuditServiceImpl implements AuditService {

    @Resource
    private SecretService secretService;

    @Override
    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {

        Long userId = secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());

    }
}
