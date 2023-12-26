package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.bo.JoinQunBO;
import com.wgq.chat.contact.po.Audit;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<AuditBO> auditBOS = new ArrayList<>();
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
        if (Objects.isNull(audit)){
            return null;
        }
        AuditBO auditBO = new AuditBO();
        auditBO.setId(audit.getId());
        auditBO.setAuditUserId(audit.getAuditUserId());
        auditBO.setAuditBusiness(AuditBusiness.of(audit.getBusinessType()));
        auditBO.setBusinessId(audit.getBusinessId());
        auditBO.setApplyUserId(audit.getApplyUserId());
        auditBO.setAuditStatus(audit.getStatus());
        auditBO.setApplyTime(audit.getApplyTime());
        auditBO.setAuditTime(audit.getAuditTime());
        return auditBO;
    }

    public Audit convert2po(AuditBO auditBO, FriendAuditParam friendAuditParam){
        auditBO.setAuditTime(System.currentTimeMillis());
        LoginUser loginUser = ThreadContext.getLoginToken();
        Audit audit = new Audit();
        audit.setId(auditBO.getId());
        audit.setBusinessType(AuditBusiness.FRIEND.getBusiness());
        audit.setApplyUserId(auditBO.getApplyUserId());
        audit.setBusinessId(auditBO.getBusinessId());
        audit.setApplyTime(auditBO.getApplyTime());
        audit.setAuditTime(auditBO.getAuditTime());
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
        audit.setStatus(StatusRecord.DISABLE);
        audit.setBusinessType(AuditBusiness.FRIEND.getBusiness());
        audit.setApplyTime(System.currentTimeMillis());
        return audit;
    }

    public Audit convert2po(AuditBO auditBO, QunAuditParam qunAuditParam){
        LoginUser loginUser = ThreadContext.getLoginToken();
        Audit audit = new Audit();
        audit.setId(auditBO.getId());
        audit.setApplyUserId(auditBO.getApplyUserId());
        audit.setBusinessId(auditBO.getBusinessId());
        audit.setAuditUserId(loginUser.getUserId());
        audit.setApplyReason(auditBO.getApplyReason());
        audit.setAuditReason(qunAuditParam.getReason());
        audit.setStatus(qunAuditParam.getAgree() ? StatusRecord.ENABLE : StatusRecord.DISABLE);
        auditBO.setAuditTime(System.currentTimeMillis());
        audit.setAuditTime(auditBO.getAuditTime());
        audit.setBusinessType(AuditBusiness.GROUP.getBusiness());
        audit.setApplyTime(auditBO.getApplyTime());
        return audit;
    }

    public Audit joinQun2AuditPo(JoinQunBO joinQunBO) {
        Audit audit = new Audit();
        //可能是邀请id
        audit.setApplyUserId(joinQunBO.getApplyUserId());
        audit.setBusinessType(AuditBusiness.GROUP.getBusiness());
        audit.setBusinessId(joinQunBO.getRoomId());
        audit.setApplyReason(joinQunBO.getReason());
        audit.setAuditReason(Symbol.EMPTY);
        audit.setStatus(StatusRecord.DISABLE);
        audit.setAuditUserId(joinQunBO.getAuditUserId());
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

    public Audit convert2po(Long roomId, Long applyUserId, Long auditUserId) {
        Audit audit = new Audit();
        audit.setBusinessId(roomId);
        audit.setApplyUserId(applyUserId);
        audit.setAuditUserId(auditUserId);
        audit.setStatus(StatusRecord.DISABLE);
        //朋友关系
        audit.setBusinessType(AuditBusiness.GROUP.getBusiness());
        return audit;
    }
}
