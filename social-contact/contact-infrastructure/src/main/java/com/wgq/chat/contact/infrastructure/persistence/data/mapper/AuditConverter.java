package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.po.Audit;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AuditConverter
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:30
 * @Version 1.0
 **/
@Named
public class AuditConverter {

    public Audit friendApply2AuditPo(FriendApplyBo friendApply){
        Audit audit = new Audit();
        audit.setUserId(friendApply.getCurrentUserId());
        audit.setApplyReason(friendApply.getReason());
        audit.setAuditTime(System.currentTimeMillis());
        return audit;
    }

    public List<AuditBO> auditList2AuditBOList(List<Audit> audits) {
        ArrayList<AuditBO> auditBOS = new ArrayList<>();
        for (Audit audit : audits) {
            AuditBO bo = new AuditBO();
            bo.setApplyUserId(audit.getUserId());
            bo.setAuditId(audit.getAuditUserId());
            bo.setAuditStatus(audit.getStatus());
            auditBOS.add(bo);
        }
        return auditBOS;
    }
}
