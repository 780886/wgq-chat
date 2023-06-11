package com.wgq.chat.contact.infrastructure.service;


import com.sparrow.passport.api.UserProfileAppService;
import com.sparrow.passport.protocol.dto.UserProfileDTO;
import com.sparrow.protocol.LoginUser;
import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.common.utils.StringUtils;
import com.wgq.chat.contact.bo.AuditBo;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.Asserts;
import com.wgq.chat.contact.protocol.BusinessException;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.repository.AuditRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class ContactService {


    @Resource
    private SecretService secretService;

    @Resource
    private UserProfileAppService userProfileAppService;

    @Resource
    private AuditRepository auditRepository;

    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), BusinessCodeEnum.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }

    public Boolean applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = null;
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo();
        //提交申请
        return this.auditRepository.applyFriend(friendApplyBo);
    }

    public FriendAuditWrapBo friendApplyList() {
        // TODO 获取当前登录用户
        LoginUser loginUser = null;
        Long currentUserId = loginUser.getUserId();
        //获取审核记录
        List<AuditBo> auditBos = this.auditRepository.getFriendAuditList(currentUserId);
        Set<Long> applyFriendSet = this.fetchUserId(auditBoList);
        List<UserProfileDTO> userProfiles = this.userProfileAppService.getUserList(applyFriendSet);
        return new FriendAuditWrapBo(auditBos,userProfiles);
    }
}
