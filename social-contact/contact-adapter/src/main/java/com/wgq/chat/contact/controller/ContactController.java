package com.wgq.chat.contact.controller;


import com.wgq.chat.contact.assembler.ContactAssembler;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.FindUserSecretParam;
import com.wgq.chat.contact.service.ContactService;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactAssembler contactAssembler;

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
}
