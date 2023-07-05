package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Boolean applyFriend(@RequestBody FriendApplyParam friendApplyParam) throws BusinessException {
        if (true){
            throw new BusinessException(BusinessCodeEnum.CAPTCHA_CODE_ERROR);
        }
        return this.auditService.applyFriend(friendApplyParam);
    }


    @ApiOperation("对好友申请进行审核")
    @PostMapping("audit-friend-apply")
    public void auditFriendApply(@RequestBody FriendAuditParam friendAuditParam) throws BusinessException {
        this.auditService.auditFriendApply(friendAuditParam);
    }

    @ApiOperation("加群")
    @PostMapping("join-qun")
    public void joinQun(@RequestBody Long qunId){

    }

    @ApiOperation("对加群进行审核")
    @PostMapping("audit-qun-apply")
    public void auditQunApply(@RequestBody QunAuditParam qunAuditParam) throws BusinessException {

    }
}
