package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.po.Audit;

/**
 * @ClassName AuditConverter
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:30
 * @Version 1.0
 **/
public class AuditConverter {

    public Audit friendApply2AuditPo(FriendApplyBo friendApply){
        Audit audit = new Audit();
        audit.setUserId(friendApply.getCurrentUserId());
        audit.setApplyReason(friendApply.getReason());
        audit.setAuditTime(System.currentTimeMillis());
        return audit;
    }
}
