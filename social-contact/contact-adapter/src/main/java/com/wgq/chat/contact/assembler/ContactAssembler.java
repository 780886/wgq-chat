package com.wgq.chat.contact.assembler;


import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.enums.StatusRecord;
import com.sheep.utils.EnumUtils;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class ContactAssembler {

    public UserFriendApplyVO toUserFriendApplyVO(ContactBO contactBO) {
        UserFriendApplyVO userFriendApply = new UserFriendApplyVO();
        userFriendApply.setUserSecretIdentify(contactBO.getSecretIdentify());
        userFriendApply.setNickName(contactBO.getUserDto().getNickName());
        userFriendApply.setAvatar(contactBO.getUserDto().getAvatar());
        return userFriendApply;
    }

    public FriendAuditWrapVo toUserFriendApplyVoList(FriendAuditWrapBo friendAuditWrap) {
        List<AuditBO> auditBOS = friendAuditWrap.getAuditBos();
        List<FriendAuditVO> userFriendApplyList = new ArrayList<>();
        Map<Long, UserProfileDTO> userDictionaries = friendAuditWrap.getFriendMap();
        for (AuditBO audit : auditBOS) {
            FriendAuditVO friendAuditVO = new FriendAuditVO();
            friendAuditVO.setId(audit.getId());
            friendAuditVO.setAuditStatus(audit.getAuditStatus());
            UserProfileDTO applyUser = userDictionaries.get(audit.getApplyUserId());
            if (applyUser != null) {
                friendAuditVO.setAvatar(applyUser.getAvatar());
                friendAuditVO.setNickName(applyUser.getNickName());
            }
            userFriendApplyList.add(friendAuditVO);
        }
        /**
         * 1.枚举变的时候，这部分的逻辑不需要改
         * 2.枚举国际化自动支持
         * 3.支持任务枚举的map字典
         */
        Map<String, String> auditStatusDict = EnumUtils.getOrdinalValueMap(StatusRecord.class);
        return new FriendAuditWrapVo(auditStatusDict,userFriendApplyList);
    }
}
