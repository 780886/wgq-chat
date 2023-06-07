package com.wgq.chat.contact.service;


import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.BusinessException;

public interface SecretService {

    String encryptUserIdentify(UserProfileDTO userDto) throws BusinessException;

    Long parseUserSecretIdentify(String secretIdentify) throws BusinessException;
}
