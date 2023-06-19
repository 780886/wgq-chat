package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/audit")
public class AuditController {

    @Inject
    private AuditService auditService;

    @Inject
    private ContactAssembler contactAssembler;


    @ApiOperation("获取好友申请列表")
    @GetMapping("friend-apply-list")
    public FriendAuditWrapVo friendApplyList(){
        FriendAuditWrapBo friendAuditBo = this.auditService.friendApplyList();
        return this.contactAssembler.toUserFriendApplyVoList(friendAuditBo);
    }


    @ApiOperation("申请好友")
    @PostMapping("friend-apply")
    public Boolean applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        return this.auditService.applyFriend(friendApplyParam);
    }


    @ApiOperation("对好友申请进行审核")
    @PostMapping("audit-friend-apply")
    public void auditFriendApply(FriendAuditParam friendAuditParam) throws BusinessException {
        this.auditService.auditFriendApply(friendAuditParam);
    }
}
