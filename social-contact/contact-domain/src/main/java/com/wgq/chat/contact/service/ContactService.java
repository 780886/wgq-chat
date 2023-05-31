package com.wgq.chat.contact.service;


import com.sparrow.passport.api.UserProfileAppService;
import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.common.utils.StringUtils;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.protocol.Asserts;
import com.wgq.chat.contact.protocol.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContactService {


//    @Autowired
    private SecretService secretService;

//    @Autowired
    private UserProfileAppService userProfileAppService;

    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), BusinessCodeEnum.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }
}
