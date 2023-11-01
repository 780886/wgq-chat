package com.wgq.chat.contact.protocol.audit;

import com.sheep.protocol.Param;

/**
 * @ClassName AgreeJoinQunParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/1 14:58
 * @Version 1.0
 **/
public class AgreeJoinQunParam implements Param {

    /**
     * 邀请token
     */
    private String inviteToken;

    public String getInviteToken() {
        return inviteToken;
    }

    public void setInviteToken(String inviteToken) {
        this.inviteToken = inviteToken;
    }
}
