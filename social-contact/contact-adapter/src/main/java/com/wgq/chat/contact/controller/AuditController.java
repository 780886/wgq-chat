package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.assembler.FriendUnreadVO;
import com.wgq.chat.contact.bo.AuditWrapBO;
import com.wgq.chat.contact.bo.FriendUnreadBO;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.JoinQunParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@Api(value = "contact", tags = "IM 联系人审核")
@RestController
@RequestMapping("/audit")
public class AuditController {

    @Inject
    private AuditService auditService;

    @Inject
    private ContactAssembler contactAssembler;


    @ApiOperation("获取好友申请列表")
    @GetMapping("friend-apply-list")
    public FriendAuditWrapVo friendApplyList() throws BusinessException{
        AuditWrapBO auditWrapBO = this.auditService.friendApplyList();
        return this.contactAssembler.toUserFriendApplyVoList(auditWrapBO);
    }

    @ApiOperation("申请好友")
    @PostMapping("friend-apply")
    public void applyFriend(@RequestBody FriendApplyParam friendApplyParam) throws BusinessException {
        this.auditService.applyFriend(friendApplyParam);
    }

    @ApiOperation("对好友申请进行审核")
    @PostMapping("audit-friend-apply")
    public void auditFriendApply(@RequestBody FriendAuditParam friendAuditParam) throws BusinessException {
        this.auditService.auditFriendApply(friendAuditParam);
    }

    @GetMapping("apply-unread")
    @ApiOperation("申请未读数")
    public FriendUnreadVO applyUnread() {
        FriendUnreadBO friendUnreadBO = this.auditService.applyUnread();
        return new FriendUnreadVO(friendUnreadBO.getUnReadCount());
    }

    /**
     * 1. 从controller 获取loginUser 并放入joinQunParam
     * 2. 在业务里直接使用loginUser 参数不透传
     * 3. 从service 逐层传递
     *
     * @param joinQunParam
     * @throws BusinessException
     */
    @ApiOperation("加群")
    @PostMapping("join-qun")
    public void joinQun(@RequestBody JoinQunParam joinQunParam) throws BusinessException {
        this.auditService.joinQun(joinQunParam);
    }

    @ApiOperation("对加群进行审核")
    @PostMapping("audit-qun-apply")
    public void auditQunApply(@RequestBody QunAuditParam qunAuditParam) throws BusinessException {
        this.auditService.auditQunApply(qunAuditParam);
    }
}
