package com.wgq.chat.contact.service;


import com.wgq.chat.common.utils.StringUtils;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.protocol.Asserts;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.dto.UserProfileDTO;

import javax.annotation.Resource;

public class ContactService {

    private SecretService secretService;

    @Resource
    private UserProfileAppService userProfileAppService;

    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), SparrowError.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }
}
