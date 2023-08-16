package com.wgq.chat.contact.infrastructure.service;

import com.sheep.core.spi.JsonFactory;
import com.sheep.cryptogram.ThreeDES;
import com.sheep.exception.Asserts;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.service.SecretService;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.passport.protocol.dto.UserProfileDTO;
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
        Asserts.isTrue(userDto == null, ContactError.USER_IDENTIFY_INFO_EMPTY);
        Asserts.isTrue(StringUtils.isNullOrEmpty(userDto.getUserId()),ContactError.USER_IDENTIFY_INFO_ID_IS_EMPTY);
        return ThreeDES.getInstance().encryptHex(this.getSecretKey(),userDto.getUserId().toString());
    }

    @Override
    public Long parseUserSecretIdentify(String secretIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(secretIdentify),ContactError.USER_SECRET_IDENTIFY_IS_EMPTY);
        String userId = ThreeDES.getInstance().decryptHex(this.getSecretKey(), secretIdentify);
        return Long.parseLong(userId);
    }

    public String getSecretKey() {
        //如果当前是生产环境，我在生产环境的服务器上通过环境变量来获取，保证安全性
        if (this.profile.equalsIgnoreCase(SPRING_PROD_PROFILE)){
            return System.getenv(USER_IDENTIFY_SECRET_KEY);
        }
        return secretKey;
    }
}
