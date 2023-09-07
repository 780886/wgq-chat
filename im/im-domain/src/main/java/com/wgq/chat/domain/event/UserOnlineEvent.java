package com.wgq.chat.domain.event;

import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.springframework.context.ApplicationEvent;


public class UserOnlineEvent extends ApplicationEvent {
    private final UserProfileDTO userProfileDTO;

    public UserOnlineEvent(Object source, UserProfileDTO userProfileDTO) {
        super(source);
        this.userProfileDTO = userProfileDTO;
    }

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }
}
