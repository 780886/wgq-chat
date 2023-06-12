package com.wgq.chat.contact.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FriendAuditWrapVo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/12 0:04
 * @Version 1.0
 **/
public class FriendAuditWrapVo {




    public FriendAuditWrapVo(Map<String, String> auditStatusDict, List<FriendAuditVO> friendAudits) {
        this.auditStatusDict = auditStatusDict;
        this.friendAudits = friendAudits;
    }


    /**
     * 审核状态字典
     */
    private Map<String, String> auditStatusDict = new HashMap<>();

    private List<FriendAuditVO> friendAudits;

    public Map<String, String> getAuditStatusDict() {
        return auditStatusDict;
    }

    public void setAuditStatusDict(HashMap<String, String> auditStatusDict) {
        this.auditStatusDict = auditStatusDict;
    }

    public List<FriendAuditVO> getFriendAudits() {
        return friendAudits;
    }

    public void setFriendAudits(List<FriendAuditVO> friendAudits) {
        this.friendAudits = friendAudits;
    }
}
