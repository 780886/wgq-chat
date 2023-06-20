package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Audit;

import java.util.List;

/**
 * @ClassName AuditDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:13
 * @Version 1.0
 **/
public interface AuditDao {

    List<Audit> getAudits(Long userId);

    Audit getEntity(Long auditId);

    Integer update(Audit audit);

    Boolean insert(Audit audit);
}
