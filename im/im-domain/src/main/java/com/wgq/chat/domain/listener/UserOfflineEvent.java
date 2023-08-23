package com.wgq.chat.domain.listener;


import com.sheep.protocol.LoginUser;

import org.springframework.context.ApplicationEvent;



public class UserOfflineEvent extends ApplicationEvent {

    private final LoginUser loginUser;

    public UserOfflineEvent(Object source,LoginUser loginUser) {
        super(source);
        this.loginUser = loginUser;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }
}
