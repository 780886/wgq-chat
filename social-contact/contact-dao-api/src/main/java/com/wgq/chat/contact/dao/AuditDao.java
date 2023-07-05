package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Audit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AuditDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:13
 * @Version 1.0
 **/
public interface AuditDao {

    List<Audit> getAudits(@Param("auditUserId") Long auditUserId);

    Audit getEntity(@Param("id") Long id);

    Long update(@Param("audit") Audit audit);

    Long insert(@Param("audit") Audit audit);

    Audit exist(Audit audit);
}
