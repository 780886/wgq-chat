package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.constant.magic.Digit;
import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.api.ChatServiceApi;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.contact.assemble.AuditAssemble;
import com.wgq.chat.contact.bo.*;
import com.wgq.chat.contact.mq.ContactMQPublisher;
import com.wgq.chat.contact.protocol.audit.*;
import com.wgq.chat.contact.protocol.constant.QunConstant;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.enums.QunRoleEnum;
import com.wgq.chat.contact.repository.AuditRepository;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunMemberRepository;
import com.wgq.chat.contact.repository.QunRepository;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.dto.WebsocketAgreeJoinQunDTO;
import com.wgq.chat.protocol.dto.WebsocketFriendApplyDTO;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
public class AuditService {


    private static Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Inject
    private SecretService secretService;

    @Inject
    private AuditRepository auditRepository;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private ChatServiceApi chatServiceApi;

    @Inject
    private RoomServiceApi roomServiceApi;

    @Inject
    private QunRepository qunRepository;

    @Inject
    private QunMemberRepository qunMemberRepository;

    @Inject
    private ContactMQPublisher contactMQPublisher;

    @Inject
    private AuditAssemble auditAssemble;

    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = ThreadContext.getLoginToken();
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //查看是否是好友关系
        FriendBO friendBO = this.contactRepository.findContact(friendId,loginUser.getUserId());
        if (Objects.nonNull(friendBO)){
            logger.info("已有好友申请记录,userId:{}, friendId:{}", loginUser, friendId);
            return;
        }
        //是否有待审批的申请记录(自己的)
        AuditBO ownAuditBO = this.auditRepository.getAudit(loginUser.getUserId(),friendId);
        if (Objects.nonNull(ownAuditBO)){
            logger.info("已有好友申请记录,userId:{},friendId:{}",loginUser.getUserId(),friendId);
            return;
        }
        //是否有待审批的申请记录(别人请求自己的)
        AuditBO friendAuditBO = this.auditRepository.getAudit(friendId,loginUser.getUserId());
        if (Objects.nonNull(friendAuditBO)){
            /**
             * 直接同意
             * 在同一个类中，非事务方法A调用事务方法B，事务失效，可以采用AopContext.currentProxy().xx()来进行调用，事务才能生效。
             */
            ((AuditService)AopContext.currentProxy()).auditFriendApply(new FriendAuditParam(friendAuditBO.getId(),friendApplyParam.getReason(), Boolean.TRUE));
            return;
        }
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo(loginUser.getUserId(),friendId,friendApplyParam.getReason());
        //提交申请
        this.auditRepository.applyFriend(friendApplyBo);
        //与回复的消息间隔多少条
        Integer unReadCount = this.auditRepository.getUnReadCount(friendId);
        //发送用户申请消息
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.APPLY.getType(),new WebsocketFriendApplyDTO(friendApplyBo.getCurrentUserId(),unReadCount)),friendApplyBo.getFriendId());
    }

    public AuditWrapBO friendApplyList() throws BusinessException{
        LoginUser loginUser = ThreadContext.getLoginToken();
        Long currentUserId = loginUser.getUserId();
        //获取审核记录
        List<AuditBO> auditBOS = this.auditRepository.getFriendAuditList(currentUserId);
        Set<Long> fetchUserIds = this.fetchUserId(auditBOS);
        Map<Long, UserProfileDTO> userProfiles = this.userProfileAppService.getUserMap(fetchUserIds);
        //将这些审核记录设为已读
        Set<Long> auditIds = this.fetchAuditIds(auditBOS);
        this.auditRepository.readAudits(loginUser.getUserId(),auditIds);
        return new AuditWrapBO(auditBOS,userProfiles);
    }

    private Set<Long> fetchAuditIds(List<AuditBO> auditBOS) {
        HashSet<Long> auditIds = new HashSet<>();
        for (AuditBO auditBO : auditBOS) {
            auditIds.add(auditBO.getId());
        }
        return auditIds;
    }

    private Set<Long> fetchUserId(List<AuditBO> auditBOS) {
        HashSet<Long> userIds = new HashSet<>();
        for (AuditBO auditBO : auditBOS) {
            userIds.add(auditBO.getApplyUserId());
        }
        return userIds;
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditFriendApply(FriendAuditParam friendAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(friendAuditParam.getId());
        Asserts.isTrue(Objects.isNull(auditBO),ContactError.AUDIT_NOT_EXIST);//不存在审核记录
        Asserts.isTrue(AuditBusiness.FRIEND != auditBO.getAuditBusiness(), ContactError.AUDIT_BUSINESS_TYPE_NOT_MATCH);//不是好友申请
        Asserts.isTrue(auditBO.getAuditStatus().equals(StatusRecord.ENABLE), ContactError.AGREED_FRIEND_APPLY);//已同意好友请求
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!Objects.equals(auditBO.getBusinessId(),loginUser.getUserId()),ContactError.AUDIT_USER_IS_NOT_MATCH);//审核用户不匹配

        /**
         * 不管同意或拒绝都要审核用户申请
         */
        this.auditRepository.auditFriend(auditBO,friendAuditParam);

        if (friendAuditParam.getAgree()) {
            //创建一个聊天房间
            Long roomId = this.roomServiceApi.createFriendRoom(Arrays.asList(loginUser.getUserId(), auditBO.getApplyUserId()));
            //添加联系人
            this.contactRepository.addContact(roomId,auditBO);
            //TODO 分布式事务 MQ消息事务发送一条同意消息。。我们已经是好友了，开始聊天吧 确实房间id
            MessageSendParam messageSendParam = this.auditAssemble.assembleMessageSendParam(roomId);
            this.chatServiceApi.sendMessage(messageSendParam);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditQunApply(QunAuditParam qunAuditParam) throws BusinessException {
        AuditBO auditBO = this.auditRepository.getAudit(qunAuditParam.getAuditId());
        Asserts.isTrue(Objects.isNull(auditBO),ContactError.AUDIT_NOT_EXIST);
        Asserts.isTrue(AuditBusiness.GROUP != auditBO.getAuditBusiness(), ContactError.AUDIT_BUSINESS_TYPE_NOT_MATCH);
        /**
         * 群主才可以审核
         */
        QunBO qunBO = this.qunRepository.qunDetailByRoomId(auditBO.getBusinessId());
        LoginUser loginUser = ThreadContext.getLoginToken();
        /**
         * 被群主邀请的只能本人同意，其余只有群主才能审核
         */
        Asserts.isTrue(!Objects.equals(auditBO.getAuditUserId(),loginUser.getUserId()) ||
                        (Digit.ZERO == auditBO.getAuditUserId() && Objects.equals(qunBO.getOwnerId(),loginUser.getUserId())),
                ContactError.NOT_AUTHORITY_AUDIT);
        /**
         * 不管同意或拒绝都要审核用户申请
         */
        this.auditRepository.auditQun(auditBO, qunAuditParam);
        if (qunAuditParam.getAgree()) {
            QunMemberBO qunMemberBO = new QunMemberBO(null, qunBO.getId(), auditBO.getApplyUserId(), QunRoleEnum.MEMBER.getType(),auditBO.getAuditTime(), auditBO.getApplyTime());
            this.qunMemberRepository.addQunMember(qunMemberBO);
            //TODO 发布一条消息
            UserProfileDTO user = this.userProfileAppService.getUser(auditBO.getApplyUserId());
            MessageSendParam messageSendParam = this.auditAssemble.assembleQunMessageSendParam(qunBO,user);
            this.chatServiceApi.sendMessage(messageSendParam);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void joinQun(JoinQunParam joinQunParam) throws BusinessException {
        Asserts.isTrue(null == joinQunParam.getRoomId(), com.wgq.chat.protocol.enums.BusinessCodeEnum.ROOM_ID_IS_EMPTY);
        QunBO qunBO = this.qunRepository.qunDetailByRoomId(joinQunParam.getRoomId());
        Asserts.isTrue(Objects.isNull(qunBO) || StatusRecord.DISABLE.equals(qunBO.getStatus()), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        AuditBO audit = this.auditRepository.getAudit(joinQunParam.getRoomId(), loginUser.getUserId(), (long) Digit.ZERO);
        if (audit != null){
            //已经被邀请过
            QunAuditParam qunAuditParam = new QunAuditParam(audit.getId(), QunConstant.APPLY_JOIN_QUN_REASON, Boolean.TRUE);
            this.auditQunApply(qunAuditParam);
            return;
        }
        JoinQunBO joinQunBO = new JoinQunBO(joinQunParam.getRoomId(), loginUser.getUserId(),0L, joinQunParam.getReason());
        this.auditRepository.joinQun(joinQunBO);
        //推送给群主
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.JOIN_QUN.getType(),new WebsocketAgreeJoinQunDTO(qunBO.getOwnerId())),qunBO.getOwnerId());

    }

    public FriendUnreadBO applyUnread() {
        LoginUser loginUser = ThreadContext.getLoginToken();
        Integer unReadCount = this.auditRepository.applyUnread(loginUser.getUserId());
        return new FriendUnreadBO(unReadCount);
    }

    public void agreeJoinQun(QunAgreeParam qunAgreeParam) throws BusinessException {
        Asserts.isTrue(Objects.isNull(qunAgreeParam.getAuditId()), BusinessCodeEnum.AUDIT_ID_IS_EMPTY);
        QunAuditParam qunAuditParam = new QunAuditParam(qunAgreeParam.getAuditId(), Symbol.EMPTY, qunAgreeParam.getAgree());
        this.auditQunApply(qunAuditParam);
    }

    public AuditWrapBO getMyQunApplyList() throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        Long currentUserId = loginUser.getUserId();
        //获取房间
        QunBO ownerQun = this.qunRepository.getOwnerQun(currentUserId);
        //获取审核记录
        List<AuditBO> qunAuditBOList = this.auditRepository.getMyQunApplyList(ownerQun.getRoomId());
        Set<Long> fetchUserIds = this.fetchUserId(qunAuditBOList);
        Map<Long, UserProfileDTO> userProfiles = this.userProfileAppService.getUserMap(fetchUserIds);
        //将这些审核记录设为已读
        Set<Long> fetchAuditIds = this.fetchAuditIds(qunAuditBOList);
        //TODO 后期根据权限看谁可以审核
        this.auditRepository.readAudits(fetchAuditIds);
        return new AuditWrapBO(qunAuditBOList,userProfiles);
    }
}
