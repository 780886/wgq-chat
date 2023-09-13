package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.mq.*;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.AuditWrapBO;
import com.wgq.chat.contact.bo.FriendApplyBo;
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
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Named
public class AuditService {

    @Inject
    private SecretService secretService;

    @Inject
    private AuditRepository auditRepository;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private QunRepository qunRepository;

    @Inject
    private MQPublisher mqPublisher;



    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = ThreadContext.getLoginToken();
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo(loginUser.getUserId(),friendId,friendApplyParam.getReason());
        //提交申请
        this.auditRepository.applyFriend(friendApplyBo);
        // TODO
        this.mqPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.APPLY.getType(),new WebsocketFriendApplyDTO(friendApplyBo.getFriendId(),1)),friendApplyBo.getFriendId());

    }

    public AuditWrapBO friendApplyList() throws BusinessException{
//        LoginUser loginUser = ThreadContext.getLoginToken();
//        Long currentUserId = loginUser.getUserId();
        Long currentUserId = 1L;
        //获取审核记录
        List<AuditBO> auditBOS = this.auditRepository.getFriendAuditList(currentUserId);
        Set<Long> fetchUserIds = this.fetchUserId(auditBOS);
        Map<Long, UserProfileDTO> userProfiles = this.userProfileAppService.getUserMap(fetchUserIds);
//        ArrayList<UserProfileDTO> userProfiles = new ArrayList<>();
//        UserProfileDTO userProfileDTO = new UserProfileDTO();
//        userProfileDTO.setUserId(1L);
//        userProfileDTO.setNickName("汪国庆");
//        userProfiles.add(userProfileDTO);
        return new AuditWrapBO(auditBOS,userProfiles);
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
