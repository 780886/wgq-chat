package com.wgq.chat.contact.repository;


import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.JoinQunParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;

import java.util.List;

public interface AuditRepository {

    Long applyFriend(FriendApplyBo friendApplyBo);

    List<AuditBO> getFriendAuditList(Long currentUserId);

//    Long applyQun(Long currentUserId, QunApplyParam qunApplyParam);
//
    Long auditFriend(AuditBO auditBO,FriendAuditParam friendAuditParam);
//
    AuditBO getAudit(Long id);

    Long auditQun(AuditBO auditBO, QunAuditParam qunAuditParam);

    Long joinQun(JoinQunParam joinQunParam);
}
