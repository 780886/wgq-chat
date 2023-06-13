package com.wgq.chat.contact.repository;


import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;

import java.util.List;

public interface AuditRepository {

    Boolean applyFriend(FriendApplyBo friendApplyBo);

    List<AuditBO> getFriendAuditList(Long currentUserId);

//    Long applyQun(Long currentUserId, QunApplyParam qunApplyParam);
//
//    Integer auditFriend(Audit audit, FriendAuditParam friendAuditParam);
//
    AuditBO getAudit(Long auditId);
}
