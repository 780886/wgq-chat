package com.wgq.chat.contact.service;


import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.redisson.annotation.RedissonLock;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class ContactService {


    @Inject
    private SecretService secretService;

    @Inject
    private UserProfileAppService userProfileAppService;

//    @RedissonLock(key = "#userIdentify",waitTime = 500)
    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), BusinessCodeEnum.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }





}
