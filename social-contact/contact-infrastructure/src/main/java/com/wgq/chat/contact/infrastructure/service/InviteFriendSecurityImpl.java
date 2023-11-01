package com.wgq.chat.contact.infrastructure.service;

import com.sheep.core.spi.JsonFactory;
import com.sheep.cryptogram.ThreeDES;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.contact.protocol.qun.InviteFriendParam;
import com.wgq.chat.contact.service.InviteFriendSecurity;

import javax.inject.Named;

@Named
public class InviteFriendSecurityImpl implements InviteFriendSecurity {

    private Json json = JsonFactory.getProvider();

    @Override
    public String encryptInviteFriend(InviteFriendParam inviteFriendParam) throws BusinessException {
        String json = this.json.toString(inviteFriendParam);
        return ThreeDES.getInstance().encryptHex(String.valueOf(inviteFriendParam.getFriendId()), json);
    }

    @Override
    public InviteFriendParam parseUserSecretIdentify(String inviteFriendToken) throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        String inviteFriendInfo = ThreeDES.getInstance().decryptHex(loginUser.getUserId() + "", inviteFriendToken);
        return (InviteFriendParam) this.json.parse(inviteFriendInfo, InviteFriendSecurity.class);
    }
}
