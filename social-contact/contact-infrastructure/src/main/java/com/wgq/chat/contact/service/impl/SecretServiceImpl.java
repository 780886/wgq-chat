package com.wgq.chat.contact.service.impl;

import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.common.cryptogram.Hmac;
import com.wgq.chat.common.cryptogram.ThreeDES;
import com.wgq.chat.common.json.Json;
import com.wgq.chat.common.json.JsonFactory;
import com.wgq.chat.contact.UserService;
import com.wgq.chat.contact.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {



    private static final String USER_IDENTIFY_SECRET_KEY = "user.identify_secret_key";
    private static final String SPRING_PROD_PROFILE = "prod";

    @Value("${user.identify_secret_key}")
    private String secretKey;

    @Value("${spring.profiles.active}")
    private String profile;

    private Json json = JsonFactory.getProvider();

    @Override
    public String encryptUserIdentify(UserProfileDTO userDto) {
        String userInfo = this.json.toString(userDto);
        return ThreeDES.getInstance().encryptHex(userInfo,this.secretKey);
    }

    @Override
    public UserProfileDTO parseUserSecretIdentify(String secretIdentify) {
        String userInfo = ThreeDES.getInstance().decryptHex(this.getSecretKey(), secretIdentify);
        return this.json.parse(userInfo,UserProfileDTO.class);
    }

    public String getSecretKey() {
        if (this.profile.equalsIgnoreCase(SPRING_PROD_PROFILE)){
            return System.getenv(USER_IDENTIFY_SECRET_KEY);
        }
        return secretKey;
    }
}
