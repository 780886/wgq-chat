package com.wgq.chat.contact.controller;


import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.FindUserSecretParam;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.service.ContactService;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {


    @Resource
    private ContactService contactService;

    @Resource
    private ContactAssembler contactAssembler;



    /**
     * 通过用户标识查找用户密文标识 和 用户基本信息
     *
     * @param findUserSecretParam
     * @return
     */
    @ApiOperation("通过用户标识查找用户密文标识和用户基本信息")
    @GetMapping("find-friend")
    public UserFriendApplyVO findFriend(FindUserSecretParam findUserSecretParam) throws BusinessException {
        ContactBO contactBO = this.contactService.findFriend(findUserSecretParam.getUserIdentify());
        return this.contactAssembler.toUserFriendApplyVO(contactBO);
    }

}
