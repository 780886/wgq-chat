package com.wgq.chat.protocol.dto;

/**
 * @ClassName: AuthorizeBO
 * @Author : wgq
 * @Date :2023/8/22  15:03
 * @Description:
 * @Version :1.0
 */
public class AuthorizeDTO {

    private String token;

    public AuthorizeDTO() {
    }

    public AuthorizeDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
