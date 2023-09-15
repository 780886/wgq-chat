package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.mq.*;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.api.ChatServiceApi;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.contact.assemble.AuditAssemble;
import com.wgq.chat.contact.bo.*;
import com.wgq.chat.contact.protocol.audit.FriendApplyParam;
import com.wgq.chat.contact.protocol.audit.FriendAuditParam;
import com.wgq.chat.contact.protocol.audit.JoinQunParam;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.enums.AuditBusiness;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.repository.AuditRepository;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunRepository;
import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private MQPublisher mqPublisher;

    @Inject
    private AuditAssemble auditAssemble;



    public void applyFriend(FriendApplyParam friendApplyParam) throws BusinessException {
        //获取当前登录信息
        LoginUser loginUser = ThreadContext.getLoginToken();
        //通过密码标识获取好友的id
        Long friendId = this.secretService.parseUserSecretIdentify(friendApplyParam.getFriendSecretIdentify());
        //查看是否是好友关系
        FriendBO friendBO = this.contactRepository.findContact(friendId,loginUser.getUserId());
        Asserts.isTrue(Objects.isNull(friendBO), BusinessCodeEnum.EXIST_FRIEND_RELATIONSHIP);
        //是否有待审批的申请记录(自己的)
        AuditBO ownAuditBO = this.auditRepository.getAudit(loginUser.getUserId(),friendId);
        if (Objects.nonNull(ownAuditBO)){
            logger.info("已有好友申请记录,userId:{},friendId:{}",loginUser.getUserId(),friendId);
            return;
        }
        //是否有待审批的申请记录(别人请求自己的)
        AuditBO friendAuditBO = this.auditRepository.getAudit(friendId,loginUser.getUserId());
        if (Objects.nonNull(friendAuditBO)){
            //直接同意
            this.auditFriendApply(new FriendAuditParam(friendAuditBO.getAuditId(),friendApplyParam.getReason(),true));
            return;
        }
        //构建好友申请的内部逻辑对象
        FriendApplyBo friendApplyBo = new FriendApplyBo(loginUser.getUserId(),friendId,friendApplyParam.getReason());
        //提交申请
        this.auditRepository.applyFriend(friendApplyBo);
        // 用户申请消息
        this.mqPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.APPLY.getType(),new WebsocketFriendApplyDTO(friendApplyBo.getFriendId(),1)),friendApplyBo.getFriendId());

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
        //返回
        return new AuditWrapBO(auditBOS,userProfiles);
    }

    private Set<Long> fetchAuditIds(List<AuditBO> auditBOS) {
        HashSet<Long> auditIds = new HashSet<>();
        for (AuditBO auditBO : auditBOS) {
            auditIds.add(auditBO.getAuditId());
        }
        return auditIds;
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
        Asserts.isTrue(Objects.isNull(auditBO),ContactError.AUDIT_NOT_EXIST);
        Asserts.isTrue(AuditBusiness.FRIEND != auditBO.getAuditBusiness(), ContactError.AUDIT_BUSINESS_TYPE_NOT_MATCH);
        Asserts.isTrue(auditBO.getAuditStatus().equals(StatusRecord.ENABLE), ContactError.AGREE_FRIEND_APPLY);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(auditBO.getBusinessId().equals(loginUser.getUserId()),ContactError.AUDIT_USER_IS_NOT_MATCH);
        //审核用户申请
        this.auditRepository.auditFriend(auditBO,friendAuditParam);
        if (friendAuditParam.getAgree()){
            //添加联系人
            this.contactRepository.addContact(auditBO,friendAuditParam);
            //创建一个聊天房间
            Long roomId = this.roomServiceApi.createFriendRoom(Arrays.asList(loginUser.getUserId(), auditBO.getApplyUserId()));
            //TODO 分布式事务 MQ消息事务发送一条同意消息。。我们已经是好友了，开始聊天吧 确实房间id
            MessageSendDTO messageSendDTO = this.auditAssemble.assembleMessageSendDTO(roomId);
            this.chatServiceApi.sendMessage(messageSendDTO,loginUser.getUserId());
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

    public FriendUnreadBO applyUnread() {
        LoginUser loginUser = ThreadContext.getLoginToken();
        Integer unReadCount = this.auditRepository.applyUnread(loginUser.getUserId());
        return new FriendUnreadBO(unReadCount);
    }
}
