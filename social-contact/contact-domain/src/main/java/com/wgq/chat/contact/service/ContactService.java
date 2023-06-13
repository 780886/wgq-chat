package com.wgq.chat.contact.service;


import com.sheep.exception.Asserts;
import com.sheep.passport.api.UserProfileAppService;
import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.constant.BusinessCodeEnum;
import com.wgq.chat.contact.repository.AuditRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Named
public class ContactService {


    @Inject
    private SecretService secretService;



//    @Inject
    private UserProfileAppService userProfileAppService;

    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), BusinessCodeEnum.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }





}
