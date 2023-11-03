package com.wgq.chat.contact.bo;


import com.wgq.passport.protocol.dto.UserProfileDTO;

import java.util.List;
import java.util.Map;

public class AuditWrapBO {



    /**
     * 好友的申请记录
     */
    private List<AuditBO> auditList;
    /**
     * 好友的基本信息
     * <p>
     * key:好友的id
     * <p>
     * value：用户的基本信息
     */
    private Map<Long, UserProfileDTO> userMap;

    public AuditWrapBO(List<AuditBO> auditList, Map<Long, UserProfileDTO> userProfiles) {
        this.auditList = auditList;
        this.userMap = userProfiles;
    }


    public List<AuditBO> getAuditList() {
        return auditList;
    }

    public void setAuditList(List<AuditBO> auditList) {
        this.auditList = auditList;
    }

    public Map<Long, UserProfileDTO> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Long, UserProfileDTO> userMap) {
        this.userMap = userMap;
    }
}
