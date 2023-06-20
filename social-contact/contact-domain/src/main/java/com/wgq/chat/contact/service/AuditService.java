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
import com.wgq.chat.contact.protocol.enums.AuditBusiness;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.repository.AuditRepository;
import com.wgq.chat.contact.repository.ContactRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
public class AuditService {

    @Inject
    private SecretService secretService;

    @Inject
    private AuditRepository auditRepository;

    @Inject
    private ContactRepository contactRepository;

    private UserProfileAppService userProfileAppService;


    public Boolean applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = ThreadContext.getLoginToken();
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo();
        friendApplyBo.setFriendId(friendId);
        friendApplyBo.setCurrentUserId(loginUser.getUserId());
        friendApplyBo.setReason(friendApplyParam.getReason());
        //提交申请
        return this.auditRepository.applyFriend(friendApplyBo);

    }

    public FriendAuditWrapBo friendApplyList() {
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

    public void auditFriendApply(FriendAuditParam friendAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(friendAuditParam.getAuditId());
        Asserts.isTrue(auditBO.getAuditBusiness() != AuditBusiness.FRIEND, ContactError.AUDIT_BUSINESS_TYPE);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(auditBO.getBusinessId().equals(loginUser.getUserId()),ContactError.AUDIT_USER_IS_NOT_MATCH);
        //审核用户申请
        //TODO 分布式事务 MQ消息事务
        this.auditRepository.auditFriend(auditBO,friendAuditParam);
        if (friendAuditParam.getAgree()){
            this.contactRepository.addContact(auditBO,friendAuditParam);
            //TODO 同步发消息
        }
    }
}
