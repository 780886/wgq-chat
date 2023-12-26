package com.wgq.chat.contact.repository;


import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.bo.JoinQunBO;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;

import java.util.List;
import java.util.Set;

public interface AuditRepository {

    Long applyFriend(FriendApplyBo friendApplyBo);

    List<AuditBO> getFriendAuditList(Long auditUserId);

    Long auditFriend(AuditBO auditBO,FriendAuditParam friendAuditParam);

    AuditBO getAudit(Long id);

    Long auditQun(AuditBO auditBO, QunAuditParam qunAuditParam);

    Long joinQun(JoinQunBO joinQunBO);

    AuditBO getAudit(Long applyUserId, Long auditUserId);

    void readAudits(Long userId, Set<Long> auditIds);

    Integer applyUnread(Long userId);

    Integer getUnReadCount(Long friendId);

    List<AuditBO> getMyQunApplyList(Long roomId);

    void readAudits(Set<Long> fetchAuditIds);

    AuditBO getAudit(Long roomId, Long applyUserId, Long auditUserId);
}
