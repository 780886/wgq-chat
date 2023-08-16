package com.wgq.chat.contact.service;



import com.sheep.protocol.BusinessException;
import com.wgq.passport.protocol.dto.UserProfileDTO;

public interface SecretService {

    String encryptUserIdentify(UserProfileDTO userDto) throws BusinessException;

    Long parseUserSecretIdentify(String secretIdentify) throws BusinessException;
}
