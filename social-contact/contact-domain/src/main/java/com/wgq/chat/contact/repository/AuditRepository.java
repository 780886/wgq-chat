package com.wgq.chat.contact.repository;


import com.wgq.chat.contact.bo.AuditBo;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;

import java.util.List;

public interface AuditRepository {

    Boolean applyFriend(FriendApplyBo friendApplyBo);

    List<AuditBo> getFriendAuditList(Long currentUserId);

//    Long applyQun(Long currentUserId, QunApplyParam qunApplyParam);
//
//    Integer auditFriend(Audit audit, FriendAuditParam friendAuditParam);
//
//    Audit getAudit(Long auditId);
}
