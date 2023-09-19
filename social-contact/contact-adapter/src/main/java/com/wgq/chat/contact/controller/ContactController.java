package com.wgq.chat.contact.controller;


import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.ContactsWrapBO;
import com.wgq.chat.contact.protocol.FindUserSecretParam;
import com.wgq.chat.contact.protocol.contact.RemoveFriendParam;
import com.wgq.chat.contact.service.ContactService;
import com.wgq.chat.contact.vo.ContactVO;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Api(value = "contact", tags = "IM 联系人接口")
@RestController
@RequestMapping("/contact")
public class ContactController {


    @Inject
    private ContactService contactService;

    @Inject
    private ContactAssembler contactAssembler;



    /**
     * 通过用户标识查找用户密文标识 和 用户基本信息
     * @param findUserSecretParam
     * @return
     */
    @ApiOperation("通过用户标识查找用户密文标识和用户基本信息")
    @GetMapping("find-friend")
    public UserFriendApplyVO findFriend(FindUserSecretParam findUserSecretParam) throws BusinessException {
        ContactBO contactBO = this.contactService.findFriend(findUserSecretParam.getUserIdentify());
        return this.contactAssembler.toUserFriendApplyVO(contactBO);
    }

    @PostMapping("/contacts")
    @ApiOperation("联系人接口")
    public ContactVO getContacts() throws BusinessException {
        ContactsWrapBO contactsWrapBO = this.contactService.getContacts();
        return this.contactAssembler.assembleVO(contactsWrapBO);
    }

    /**
     * 删除好友
     * @param removeFriendParam
     * @throws BusinessException
     */
    @ApiOperation("删除好友")
    @PostMapping("remove-friend")
    public void removeFriend(@RequestBody RemoveFriendParam removeFriendParam) throws BusinessException {
        this.contactService.removeFriend(removeFriendParam);
    }


}
