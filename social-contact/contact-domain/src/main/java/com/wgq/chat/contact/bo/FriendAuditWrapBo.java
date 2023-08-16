package com.wgq.chat.contact.bo;

import com.wgq.passport.protocol.dto.UserProfileDTO;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FrendApplyBo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:19
 * @Version 1.0
 **/
public class FriendAuditWrapBo {


    public FriendAuditWrapBo(List<AuditBO> auditBOS, List<UserProfileDTO> userProfiles){
        this.auditBOS = auditBOS;
        this.friendMap = new HashMap<>(userProfiles.size());
        for (UserProfileDTO userProfile : userProfiles) {
            this.friendMap.put(userProfile.getUserId(),userProfile);
        }
    }


    /**
     * 好友的申请记录
     */
    private List<AuditBO> auditBOS;

    /**
     * 好友的基本信息
     * key:好友的id
     * value:好友的基本信息
     */
    private Map<Long,UserProfileDTO> friendMap;

    public List<AuditBO> getAuditBos() {
        return auditBOS;
    }

    public void setAuditBos(List<AuditBO> auditBOS) {
        this.auditBOS = auditBOS;
    }

    public Map<Long, UserProfileDTO> getFriendMap() {
        return friendMap;
    }

    public void setFriendMap(Map<Long, UserProfileDTO> friendMap) {
        this.friendMap = friendMap;
    }
}
