package com.wgq.chat.contact.assembler;


import com.sheep.protocol.enums.StatusRecord;
import com.sheep.utils.BeanUtils;
import com.sheep.utils.CollectionsUtils;
import com.sheep.utils.EnumUtils;
import com.wgq.chat.contact.bo.*;
import com.wgq.chat.contact.vo.*;
import com.wgq.passport.protocol.dto.UserProfileDTO;

import javax.inject.Named;
import java.util.ArrayList;
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

    public FriendAuditWrapVo toUserFriendApplyVoList(AuditWrapBO friendAuditWrap) {
        List<AuditBO> auditBOS = friendAuditWrap.getAuditList();
        List<FriendAuditVO> userFriendApplyList = new ArrayList<>();
        Map<Long, UserProfileDTO> userDictionaries = friendAuditWrap.getUserMap();
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
         * TODO StatusRecord可能需要拆分
         */
        Map<String, String> auditStatusDict = EnumUtils.getOrdinalValueMap(StatusRecord.class);
        return new FriendAuditWrapVo(auditStatusDict,userFriendApplyList);
    }

    public ContactVO assembleVO(ContactsWrapBO contactsWrap) {
        List<QunVO> qunVOS = this.assembleMyQun(contactsWrap);
        List<UserVO> userVOS = this.assembleMyContact(contactsWrap);
        return new ContactVO(qunVOS, userVOS);
    }

    private List<QunVO> assembleMyQun(ContactsWrapBO contactsWrap) {
        if (CollectionsUtils.isNullOrEmpty(contactsWrap.getQuns())) {
            return null;
        }
        List<QunVO> qunVOS = new ArrayList<>(contactsWrap.getQuns().size());
        for (QunBO qunBO : contactsWrap.getQuns()) {
            QunVO qunVO = new QunVO();
            BeanUtils.copyProperties(qunBO, qunVO);
            qunVO.setQunId(qunBO.getId());
            qunVO.setQunName(qunBO.getName());
            qunVOS.add(qunVO);
        }
        return qunVOS;
    }

    private List<UserVO> assembleMyContact(ContactsWrapBO contactsWrap) {
        if (CollectionsUtils.isNullOrEmpty(contactsWrap.getUsers())) {
            return null;
        }
        List<UserVO> userVOS = new ArrayList<>(contactsWrap.getUsers().size());
        for (UserProfileDTO userProfileDTO : contactsWrap.getUsers()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userProfileDTO, userVO);
            userVOS.add(userVO);
        }
        return userVOS;
    }
}
