package com.wgq.chat.contact.bo;

import com.sparrow.passport.protocol.dto.UserProfileDTO;

import java.util.List;

/**
 * @ClassName FrendApplyBo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:19
 * @Version 1.0
 **/
public class FriendAuditWrapBo {

   private List<AuditBo> auditBos;
   private List<UserProfileDTO> userProfiles;


    public FriendAuditWrapBo(List<AuditBo> auditBos, List<UserProfileDTO> userProfiles) {
        this.auditBos = auditBos;
        this.userProfiles = userProfiles;
    }

    public List<AuditBo> getAuditBos() {
        return auditBos;
    }

    public void setAuditBos(List<AuditBo> auditBos) {
        this.auditBos = auditBos;
    }

    public List<UserProfileDTO> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfileDTO> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
