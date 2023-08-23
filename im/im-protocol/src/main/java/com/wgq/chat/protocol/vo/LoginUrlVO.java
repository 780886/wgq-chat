package com.wgq.chat.protocol.vo;

/**
 * @ClassName LoginUrlVO
 * @Description TODO
 * @Author wgq
 * @Date 2023/8/22 21:16
 * @Version 1.0
 **/
public class LoginUrlVO {

    private String loginUrl;

    public LoginUrlVO() {
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public static class LoginUrlVOBuilder{
        LoginUrlVO loginUrlVO = new LoginUrlVO();
        
        public LoginUrlVO.LoginUrlVOBuilder loginUrl(String loginUrl){
            this.loginUrlVO.loginUrl = loginUrl;
            return this;
        }

        public LoginUrlVO build(){
            return this.loginUrlVO;
        }
    }

}
