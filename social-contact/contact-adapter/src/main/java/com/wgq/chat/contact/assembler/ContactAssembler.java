package com.wgq.chat.contact.assembler;


import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import org.springframework.stereotype.Component;

@Component
public class ContactAssembler {

    public UserFriendApplyVO toUserFriendApplyVO(ContactBO contactBO) {
        UserFriendApplyVO userFriendApply = new UserFriendApplyVO();
        userFriendApply.setUserSecretIdentify(contactBO.getSecretIdentify());
        userFriendApply.setNickName(contactBO.getUserDto().getNickName());
        userFriendApply.setAvatar(contactBO.getUserDto().getAvatar());
        return userFriendApply;
    }

    public FriendAuditWrapBo toUserFriendApplyVoList(FriendAuditWrapBo friendAuditBo) {
        return null;
    }
}
