package com.wgq.chat.contact.infrastructure.persistence.data.mapper;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.po.Audit;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.JoinQunParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;
import org.springframework.beans.BeanUtils;

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
        audit.setApplyUserId(friendApply.getCurrentUserId());
        audit.setApplyReason(friendApply.getReason());
        audit.setAuditTime(System.currentTimeMillis());
        return audit;
    }

    public List<AuditBO> auditList2AuditBOList(List<Audit> audits) {
        ArrayList<AuditBO> auditBOS = new ArrayList<>();
        for (Audit audit : audits) {
            AuditBO bo = new AuditBO();
            bo.setApplyUserId(audit.getApplyUserId());
            bo.setId(audit.getId());
            bo.setAuditUserId(audit.getAuditUserId());
            bo.setAuditStatus(audit.getStatus());
            auditBOS.add(bo);
        }
        return auditBOS;
    }

    public AuditBO audit2AuditBo(Audit audit) {
        AuditBO auditBO = new AuditBO();
        auditBO.setAuditId(audit.getId());
        auditBO.setAuditUserId(audit.getAuditUserId());
        auditBO.setBusinessId(audit.getBusinessId());
        auditBO.setApplyUserId(audit.getApplyUserId());
        auditBO.setAuditStatus(audit.getStatus());
        return auditBO;
    }

    public Audit convert2po(AuditBO auditBO, FriendAuditParam friendAuditParam){
        LoginUser loginUser = ThreadContext.getLoginToken();
        Audit audit = new Audit();
        audit.setId(auditBO.getId());
        audit.setBusinessType(AuditBusiness.FRIEND.getBusiness());
        audit.setApplyUserId(auditBO.getApplyUserId());
        audit.setBusinessId(auditBO.getBusinessId());
        audit.setApplyTime(auditBO.getApplyTime());
        audit.setAuditTime(System.currentTimeMillis());
        audit.setAuditUserId(loginUser.getUserId());
        audit.setApplyReason(auditBO.getApplyReason());
        audit.setAuditReason(auditBO.getAuditReason());
        audit.setStatus(friendAuditParam.getAgree()?StatusRecord.ENABLE:StatusRecord.DISABLE);
        return audit;
    }

    public Audit convert2po(FriendApplyBo friendApplyBo) {
        Audit audit = new Audit();
        audit.setApplyUserId(friendApplyBo.getCurrentUserId());
        audit.setBusinessId(friendApplyBo.getFriendId());
        audit.setAuditUserId(friendApplyBo.getFriendId());
        audit.setApplyReason(friendApplyBo.getReason());
        audit.setStatus(StatusRecord.ENABLE);
        audit.setBusinessType(AuditBusiness.FRIEND.getBusiness());
        audit.setApplyTime(System.currentTimeMillis());
        return audit;
    }

    public Audit convert2po(AuditBO auditBO, QunAuditParam qunAuditParam){
        LoginUser loginUser = ThreadContext.getLoginToken();
        Audit audit = new Audit();
        audit.setId(auditBO.getAuditId());
        audit.setApplyUserId(auditBO.getApplyUserId());
        audit.setBusinessId(auditBO.getBusinessId());
        audit.setAuditUserId(loginUser.getUserId());
        audit.setApplyReason(auditBO.getApplyReason());
        audit.setAuditReason(qunAuditParam.getReason());
        audit.setStatus(qunAuditParam.getAgree() ? StatusRecord.ENABLE : StatusRecord.DISABLE);
        audit.setAuditTime(System.currentTimeMillis());
        audit.setBusinessType(AuditBusiness.GROUP.getBusiness());
        audit.setApplyTime(auditBO.getApplyTime());
        return audit;
    }

    public Audit joinQun2AuditPo(JoinQunParam joinQunParam) {
        Audit audit = new Audit();
        BeanUtils.copyProperties(joinQunParam, audit);
        LoginUser loginUser = ThreadContext.getLoginToken();
        audit.setApplyUserId(loginUser.getUserId());
        audit.setBusinessType(AuditBusiness.GROUP.getBusiness());
        audit.setBusinessId(joinQunParam.getQunId());
        audit.setApplyReason(joinQunParam.getReason());
        audit.setAuditReason(Symbol.EMPTY);
        audit.setStatus(StatusRecord.INIT);
        audit.setAuditUserId(0L);
        audit.setAuditTime(0L);
        audit.setApplyTime(System.currentTimeMillis());
        return audit;
    }

    public Audit convert2po(Long applyUserId, Long auditUserId) {
        Audit audit = new Audit();
        audit.setApplyUserId(applyUserId);
        audit.setAuditUserId(auditUserId);
        audit.setStatus(StatusRecord.DISABLE);
        //朋友关系
        audit.setBusinessType(AuditBusiness.FRIEND.getBusiness());
        return audit;
    }
}
