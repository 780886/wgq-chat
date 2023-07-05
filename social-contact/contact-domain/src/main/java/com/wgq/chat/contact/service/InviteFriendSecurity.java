package com.wgq.chat.contact.service;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.protocol.qun.InviteFriendParam;

public interface InviteFriendSecurity {

    String encryptInviteFriend(InviteFriendParam inviteFriendParam) throws BusinessException;
    
    InviteFriendParam parseUserSecretIdentify(String inviteFriendToken) throws BusinessException;
}
