package com.wgq.chat.contact.infrastructure.service;


import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.wgq.chat.contact.protocol.BusinessException;

public interface SecretService {

    String encryptUserIdentify(UserProfileDTO userDto) throws BusinessException;

    Long parseUserSecretIdentify(String secretIdentify) throws BusinessException;
}
