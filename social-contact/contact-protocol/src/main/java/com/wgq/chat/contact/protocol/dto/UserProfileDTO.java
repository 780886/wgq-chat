package com.wgq.chat.contact.protocol.dto;

/**
 * @ClassName UserProfileDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/5/30 21:42
 * @Version 1.0
 **/
public class UserProfileDTO {

    private String avatar;
    private String nickName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
