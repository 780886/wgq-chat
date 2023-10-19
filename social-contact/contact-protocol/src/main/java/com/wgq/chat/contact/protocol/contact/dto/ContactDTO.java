package com.wgq.chat.contact.protocol.contact.dto;

import com.sheep.protocol.DTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;

/**
 * @ClassName ContactDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/10/18 22:40
 * @Version 1.0
 **/
public class ContactDTO implements DTO {

    public ContactDTO() {
    }

    public ContactDTO(UserProfileDTO userDto, String secretIdentify) {
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
