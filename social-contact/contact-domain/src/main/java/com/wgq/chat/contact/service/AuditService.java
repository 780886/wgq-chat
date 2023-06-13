package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.passport.api.UserProfileAppService;
import com.sheep.passport.protocol.dto.UserProfileDTO;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
import com.wgq.chat.contact.bo.FriendAuditWrapBo;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.constant.BusinessCodeEnum;
import com.wgq.chat.contact.repository.AuditRepository;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuditService {

    @Inject
    private SecretService secretService;

    @Inject
    private AuditRepository auditRepository;

    private UserProfileAppService userProfileAppService;


    public Boolean applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = ThreadContext.getLoginToken();
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo();
        //提交申请
        return this.auditRepository.applyFriend(friendApplyBo);

    }

    public FriendAuditWrapBo friendApplyList() {
        // TODO 获取当前登录用户
        LoginUser loginUser = ThreadContext.getLoginToken();
        Long currentUserId = loginUser.getUserId();
        //获取审核记录
        List<AuditBO> auditBOS = this.auditRepository.getFriendAuditList(currentUserId);
        Set<Long> fetchUserIds = this.fetchUserId(auditBOS);
        List<UserProfileDTO> userProfiles = this.userProfileAppService.getUserList(fetchUserIds);
        return new FriendAuditWrapBo(auditBOS,userProfiles);
    }

    private Set<Long> fetchUserId(List<AuditBO> auditBOS) {
        HashSet<Long> userIds = new HashSet<>();
        for (AuditBO auditBO : auditBOS) {
            userIds.add(auditBO.getAuditId());
        }
        return userIds;
    }

    public Boolean auditFriendApply(FriendAuditParam friendAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(friendAuditParam.getAuditId());
        // TODO
        Asserts.isTrue(auditBO.getBusinessType() == null, BusinessCodeEnum.EMAIL_EXIST_ERROR);
        LoginUser loginUser = ThreadContext.getLoginToken();
        return null;
    }
}
