package com.wgq.chat.contact.bo;

import com.wgq.passport.protocol.dto.UserProfileDTO;


public class ContactBO {

    public ContactBO() {
    }

    public ContactBO(UserProfileDTO userDto, String secretIdentify) {
        this.userDto = userDto;
        this.secretIdentify = secretIdentify;
    }

    private UserProfileDTO userDto;
    private String secretIdentify;

    public UserProfileDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserProfileDTO userDto) {
        this.userDto = userDto;
    }

    public String getSecretIdentify() {
        return secretIdentify;
    }

    public void setSecretIdentify(String secretIdentify) {
        this.secretIdentify = secretIdentify;
    }
}
