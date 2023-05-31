package com.wgq.chat.contact.service.impl;

import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.contact.service.SecretService;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {

    @Override
    public String encryptUserIdentify(UserProfileDTO userDto) {
        return null;
    }

    @Override
    public UserProfileDTO parseUserSecretIdentify(String secretIdentify) {
        return null;
    }
}
