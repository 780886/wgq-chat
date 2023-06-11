package com.wgq.chat.contact.assembler;


import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.vo.FriendAuditVO;
import com.wgq.chat.contact.vo.FriendAuditWrapVo;
import com.wgq.chat.contact.vo.UserFriendApplyVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
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
            friendAuditVO.setAuditId(audit.getAuditId());
            friendAuditVO.setAuditStatus(audit.getAuditStatus());
            UserProfileDTO applyUser = userDictionaries.get(audit.getApplyUserId());
            friendAuditVO.setAvatar(applyUser.getAvatar());
            friendAuditVO.setNickName(applyUser.getNickName());
            userFriendApplyList.add(friendAuditVO);
        }
        HashMap<Integer, String> auditStatusDict = new HashMap<>();
        auditStatusDict.put(StatusRecord.ENABLE.ordinal(),"通过");
        auditStatusDict.put(StatusRecord.DISABLE.ordinal(),"拒绝");
        return new FriendAuditWrapVo(auditStatusDict,userFriendApplyList);
    }
}
