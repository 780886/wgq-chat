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
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.JoinQunParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.repository.AuditRepository;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

    @Inject
    private QunRepository qunRepository;


    public Long applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
//        LoginUser loginUser = ThreadContext.getLoginToken();
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(2L);
        loginUser.setNickName("杨洋");
        loginUser.setDeviceId("1");
        loginUser.setDays(2);
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo(loginUser.getUserId(),friendId,friendApplyParam.getReason());
        //提交申请
        return this.auditRepository.applyFriend(friendApplyBo);

    }

    public FriendAuditWrapBo friendApplyList() throws BusinessException{
//        LoginUser loginUser = ThreadContext.getLoginToken();
//        Long currentUserId = loginUser.getUserId();
        Long currentUserId = 1L;
        //获取审核记录
        List<AuditBO> auditBOS = this.auditRepository.getFriendAuditList(currentUserId);
        Set<Long> fetchUserIds = this.fetchUserId(auditBOS);
//        List<UserProfileDTO> userProfiles = this.userProfileAppService.getUserList(fetchUserIds);
        ArrayList<UserProfileDTO> userProfiles = new ArrayList<>();
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserId(1L);
        userProfileDTO.setNickName("汪国庆");
        userProfiles.add(userProfileDTO);
        return new FriendAuditWrapBo(auditBOS,userProfiles);
    }

    private Set<Long> fetchUserId(List<AuditBO> auditBOS) {
        HashSet<Long> userIds = new HashSet<>();
        for (AuditBO auditBO : auditBOS) {
            //TODO
            userIds.add(auditBO.getApplyUserId());
        }
        return userIds;
    }

    public void auditFriendApply(FriendAuditParam friendAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(friendAuditParam.getId());
        Asserts.isTrue(AuditBusiness.FRIEND != auditBO.getAuditBusiness(), ContactError.AUDIT_BUSINESS_TYPE_NOT_MATCH);
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

    public void auditQunApply(QunAuditParam qunAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(qunAuditParam.getAuditId());
        Asserts.isTrue(AuditBusiness.GROUP != auditBO.getAuditBusiness(), ContactError.AUDIT_BUSINESS_TYPE_NOT_MATCH);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!auditBO.getAuditUserId().equals(loginUser.getUserId()), ContactError.AUDIT_USER_IS_NOT_MATCH);
        this.auditRepository.auditQun(auditBO, qunAuditParam);
        if (qunAuditParam.getAgree()) {
            this.qunRepository.joinQun(auditBO);
        }
    }

    public void joinQun(JoinQunParam joinQunParam) throws BusinessException {
        Asserts.isTrue(null == joinQunParam.getQunId(), ContactError.QUN_ID_IS_EMPTY);
        QunBO qunBO = this.qunRepository.qunDetail(joinQunParam.getQunId());
        Asserts.isTrue(qunBO == null, ContactError.QUN_NOT_FOUND);
        this.auditRepository.joinQun(joinQunParam);
    }
}
