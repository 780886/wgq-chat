package com.wgq.chat.contact.controller;


import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.FindUserSecretParam;
import com.wgq.chat.contact.infrastructure.service.ContactService;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation("获取好友申请列表")
    @GetMapping("friend-apply-list")
    public FriendAuditWrapBo friendApplyList(){
        FriendAuditWrapBo friendAuditBo = this.contactService.friendApplyList();
        return this.contactAssembler.toUserFriendApplyVoList(friendAuditBo);
    }


    /**
     * 通过用户标识查找用户密文标识 和 用户基本信息
     *
     * @param findUserSecretParam
     * @return
     */
    public UserFriendApplyVO findFriend(FindUserSecretParam findUserSecretParam) throws BusinessException {
        ContactBO contactBO = this.contactService.findFriend(findUserSecretParam.getUserIdentify());
        return this.contactAssembler.toUserFriendApplyVO(contactBO);
    }

    /**
     * 添加好友
     *
     * @return
     */
    public Boolean addFriend() {
        return null;
    }
}
