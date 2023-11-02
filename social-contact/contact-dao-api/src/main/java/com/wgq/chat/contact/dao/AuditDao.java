package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Audit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @ClassName AuditDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:13
 * @Version 1.0
 **/
public interface AuditDao {

    List<Audit> getAudits(@Param("auditUserId") Long auditUserId);

    Audit getAuditById(@Param("id") Long id);

    Long update(@Param("audit") Audit audit);

    Long insert(@Param("audit") Audit audit);

    Audit getAudit(@Param("audit") Audit audit);

    void updateReadStatus(@Param("ids") Set<Long> ids, @Param("auditUserId") Long auditUserId, @Param("readCode") Integer readCode, @Param("unreadCode") Integer unreadCode);

    Integer applyUnread(@Param("auditUserId") Long auditUserId, @Param("readStatus") Integer readStatus);

    List<Audit> getAuditByBusinessId(Long roomId);
}
