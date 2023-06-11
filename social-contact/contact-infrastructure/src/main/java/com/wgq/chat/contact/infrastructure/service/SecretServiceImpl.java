package com.wgq.chat.contact.service.impl;

import com.sheep.core.spi.JsonFactory;
import com.sheep.cryptogram.ThreeDES;
import com.sheep.exception.Asserts;
import com.sheep.json.Json;
import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.protocol.constant.BusinessCodeEnum;
import com.wgq.chat.contact.service.SecretService;
import org.springframework.beans.factory.annotation.Value;


import javax.inject.Named;

@Named
public class SecretServiceImpl implements SecretService {

    private static final String USER_IDENTIFY_SECRET_KEY = "user.identify_secret_key";
    private static final String SPRING_PROD_PROFILE = "prod";

    @Value("${user.identify_secret_key}")
    private String secretKey;

    @Value("${spring.profiles.active}")
    private String profile;

    private Json json = JsonFactory.getProvider();

    @Override
    public String encryptUserIdentify(UserProfileDTO userDto) throws BusinessException {
        Asserts.isTrue(userDto == null, BusinessCodeEnum.PARAM_NOT_EMPTY);
        return null;
//        Asserts.isTrue(userDto.getId() == null,BusinessCodeEnum.PARAM_NOT_EMPTY);
//        return ThreeDES.getInstance().encryptHex(this.secretKey,userDto.getId().toString());
    }

    @Override
    public Long parseUserSecretIdentify(String secretIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(secretIdentify),BusinessCodeEnum.USER_SECRET_IS_EMPTY);
        String userId = ThreeDES.getInstance().decryptHex(this.getSecretKey(), secretIdentify);
        return Long.parseLong(userId);
    }

    public String getSecretKey() {
        if (this.profile.equalsIgnoreCase(SPRING_PROD_PROFILE)){
            return System.getenv(USER_IDENTIFY_SECRET_KEY);
        }
        return secretKey;
    }
}
