package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.service.AuditService;
import com.wgq.chat.contact.vo.FriendAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
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






    /**
     * 确认申请好友
     *
     * @param friendApplyParam
     * @return
     */
    public Boolean applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        this.auditService.applyFriend(friendApplyParam);
        return true;
    }

    /**
     * 审核好友申请
     *
     * @param friendAuditParam
     * @return
     */
    public Boolean auditFriendApply(FriendAuditParam friendAuditParam) {
        return true;
    }
}
