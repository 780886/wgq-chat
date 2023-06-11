package com.wgq.chat.contact.vo;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName FriendAuditWrapVo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/12 0:04
 * @Version 1.0
 **/
public class FriendAuditWrapVo {




    public FriendAuditWrapVo(HashMap<Integer, String> auditStatusDict, List<FriendAuditVO> friendAudits) {
        this.auditStatusDict = auditStatusDict;
        this.friendAudits = friendAudits;
    }


    /**
     * 审核状态字典
     */
    private HashMap<Integer, String> auditStatusDict = new HashMap<>();

    private List<FriendAuditVO> friendAudits;

    public HashMap<Integer, String> getAuditStatusDict() {
        return auditStatusDict;
    }

    public void setAuditStatusDict(HashMap<Integer, String> auditStatusDict) {
        this.auditStatusDict = auditStatusDict;
    }

    public List<FriendAuditVO> getFriendAudits() {
        return friendAudits;
    }

    public void setFriendAudits(List<FriendAuditVO> friendAudits) {
        this.friendAudits = friendAudits;
    }
}
