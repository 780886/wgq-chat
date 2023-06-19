package com.wgq.chat.contact.service;


import com.sheep.exception.Asserts;
import com.sheep.passport.api.UserProfileAppService;
import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;

import javax.inject.Inject;
import javax.inject.Named;


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
