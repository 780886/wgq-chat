package com.wgq.chat.contact.infrastructure.service;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.service.SecretService;

import javax.annotation.Resource;
import javax.inject.Named;

@Named
public class AuditServiceImpl {

    @Resource
    private SecretService secretService;


}
