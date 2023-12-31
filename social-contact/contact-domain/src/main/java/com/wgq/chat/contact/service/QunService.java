package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.StringUtils;
import com.wgq.chat.api.ChatServiceApi;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.contact.assemble.QunAssemble;
import com.wgq.chat.contact.assemble.QunMemberAssembler;
import com.wgq.chat.contact.bo.*;
import com.wgq.chat.contact.mq.ContactMQPublisher;
import com.wgq.chat.contact.protocol.audit.QunAuditParam;
import com.wgq.chat.contact.protocol.constant.QunConstant;
import com.wgq.chat.contact.protocol.contact.dto.QunDTO;
import com.wgq.chat.contact.protocol.enums.Category;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.enums.QunRoleEnum;
import com.wgq.chat.contact.protocol.qun.*;
import com.wgq.chat.contact.repository.AuditRepository;
import com.wgq.chat.contact.repository.QunMemberRepository;
import com.wgq.chat.contact.repository.QunRepository;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.*;
import com.wgq.chat.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName QunService
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
@Named
public class QunService {

    public static final String INVITE = "邀请";

    @Inject
    private QunRepository qunRepository;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private QunMemberRepository qunMemberRepository;

    @Inject
    private RoomServiceApi roomServiceApi;

    @Inject
    private QunAssemble qunAssemble;

    @Inject
    private AuditRepository auditRepository;

    @Inject
    private QunMemberAssembler qunMemberAssembler;

    @Inject
    private ChatServiceApi chatServiceApi;

    @Inject
    private ContactMQPublisher contactMQPublisher;

    @Inject
    private AuditService auditService;

    @Transactional(rollbackFor = Exception.class)
    public Long createQun(QunCreateParam qunCreateParam) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunCreateParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        Asserts.isTrue(qunCreateParam.getCategoryId() == null,ContactError.QUN_CATEGORY_IS_EMPTY);
        Asserts.isTrue(null == qunCreateParam.getNationalityId(), ContactError.NATIONALITY_OF_QUN_EMPTY);

        LoginUser loginUser = ThreadContext.getLoginToken();
        QunBO qunBO = this.qunRepository.getOwnerQun(loginUser.getUserId());
        Asserts.isTrue(Objects.nonNull(qunBO), com.wgq.chat.contact.protocol.enums.BusinessCodeEnum.MY_QUN_IS_EXIST);
        Long roomId = this.roomServiceApi.createQunRoom(loginUser.getUserId());
        QunBO qunCreateBO = this.qunAssemble.assembleQunBO(roomId,qunCreateParam);
        Long qunId = this.qunRepository.createQun(qunCreateBO);
        //构建业务对象
        QunMemberBO qunMemberBO = new QunMemberBO(null,qunId,loginUser.getUserId(), QunRoleEnum.LEADER.getType(),System.currentTimeMillis(),System.currentTimeMillis());
        this.qunMemberRepository.addQunMember(qunMemberBO);
        //推送MQ给群主
        MessageSendParam messageSendParam = this.qunMemberAssembler.assembleMessageSendParam(roomId,qunCreateParam.getName());
        this.chatServiceApi.sendMessage(messageSendParam);
        return roomId;
    }


    public void modify(QunModifyParam qunModifyParam) throws BusinessException {
        Asserts.isTrue(qunModifyParam.getQunId() == null,ContactError.QUN_ID_IS_EMPTY);
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunModifyParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        this.qunRepository.modifyQun(qunModifyParam);
    }

    public QunDetailWrapBO detail(Long roomId) throws BusinessException {
        QunBO qunBo = this.qunRepository.qunDetailByRoomId(roomId);
        UserProfileDTO owner = this.userProfileAppService.getUser(qunBo.getOwnerId());
        return new QunDetailWrapBO(qunBo, owner);
    }

    public QunPlazaBO qunPlazaOfCategoryId(Long categoryId) throws BusinessException {
        List<QunBO> qunBOS = this.qunRepository.getQunPlaza(categoryId);
        return this.wrapQunPlaza(qunBOS);
    }


    public QunPlazaBO qunPlaza() throws BusinessException {
        List<QunBO> qunBOs = this.qunRepository.queryQunPlaza();
        return this.wrapQunPlaza(qunBOs);
    }

    private QunPlazaBO wrapQunPlaza(List<QunBO> qunBOS) throws BusinessException {
        QunPlazaBO qunPlaza = new QunPlazaBO();
        Set<Long> userIds = new HashSet<>();
        //Set<Long> categories = new HashSet<>();
        for (QunBO qun : qunBOS) {
            userIds.add(qun.getOwnerId());
            //categories.add(qun.getCategoryId());
        }
        Map<Long, UserProfileDTO> userProfileMap = this.userProfileAppService.getUserMap(userIds);
        qunPlaza.setUserDicts(userProfileMap);
        qunPlaza.setCategoryDicts(Category.getMap());
        qunPlaza.setQunList(qunBOS);
        return qunPlaza;
    }



    public void existQun(ExistQunParam existQunParam) throws BusinessException {
        Asserts.isTrue(existQunParam.getRoomId() == null,BusinessCodeEnum.ROOM_ID_IS_EMPTY);
        QunBO existQun = this.qunRepository.qunDetailByRoomId(existQunParam.getRoomId());
        Asserts.isTrue( Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Boolean isMember = this.qunRepository.isMember(existQun.getId(), loginUser.getUserId());
        Asserts.isTrue(!isMember, ContactError.QUN_ID_IS_EMPTY);
        RemoveMemberParam removeMemberParam = new RemoveMemberParam(existQun.getId(), loginUser.getUserId());
        this.qunMemberRepository.removeMember(removeMemberParam);
        //todo 通知群主和当前用户
        Set<Long> userIds = this.fetchIds(existQun.getOwnerId(),loginUser.getUserId());
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.EXIST_QUN.getType(),new WebsocketExistQunDTO(userIds)),userIds);
    }

    private Set<Long> fetchIds(Long...userId) {
        Set<Long> userIds = new HashSet<>(Arrays.asList(userId));
        return userIds;
    }

    public Long inviteFriend(InviteFriendParam inviteFriendParam) throws BusinessException {
        Asserts.isTrue(Objects.isNull(inviteFriendParam.getFriendId()),BusinessCodeEnum.FRIEND_ID_IS_EMPTY);
        RoomDTO roomDTO = this.roomServiceApi.getRoom(inviteFriendParam.getRoomId());
        Asserts.isTrue(Objects.isNull(roomDTO), BusinessCodeEnum.ROOM_NOT_FOUND);
        QunBO existQun = this.qunRepository.qunDetailByRoomId(roomDTO.getId());
        Asserts.isTrue(Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        //TODO 目前只有群主才可以邀请好友
        Asserts.isTrue(!Objects.equals(existQun.getOwnerId(),loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        UserProfileDTO newMember = this.userProfileAppService.getUser(inviteFriendParam.getFriendId());
        Asserts.isTrue(Objects.isNull(newMember), ContactError.USER_IDENTIFY_INFO_EMPTY);
        //只能拉一个人 是否已经是群成员
        Boolean isMember = this.qunRepository.isMember(existQun.getId(), inviteFriendParam.getFriendId());
        Asserts.isTrue(isMember, ContactError.USER_IS_MEMBER);
        /**
         * 判断好友是否已经申请过，如果有则直接同意
         */
        AuditBO auditBO = this.auditRepository.getAudit(inviteFriendParam.getRoomId(),inviteFriendParam.getFriendId(),inviteFriendParam.getFriendId());
        if (auditBO != null){
            QunAuditParam qunAuditParam = new QunAuditParam(auditBO.getId(), QunConstant.APPLY_JOIN_QUN_REASON, Boolean.TRUE);
            this.auditService.auditQunApply(qunAuditParam);
            return auditBO.getId();
        }
        String reason = loginUser.getUserName() + INVITE;
        JoinQunBO joinQunBO = new JoinQunBO(existQun.getRoomId(), inviteFriendParam.getFriendId(), inviteFriendParam.getFriendId(),reason);
        Long auditId = this.auditRepository.joinQun(joinQunBO);
//        //TODO 发送消息给好友
//        MessageSendParam messageSendParam = this.qunAssemble.assembleInviteFriendMessageSendParam(inviteFriendParam.getRoomId(),existQun);
//        // TODO 应该是文本消息
//        this.chatServiceApi.sendMessage(messageSendParam,loginUser.getUserId());
        WebsocketInviteJoinQunDTO inviteJoinQunDTO = new WebsocketInviteJoinQunDTO(auditId, loginUser.getUserName() + "邀请您加入群聊:" + existQun.getName() + "!");
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.JOIN_QUN.getType(),inviteJoinQunDTO),inviteFriendParam.getFriendId());
        return auditId;

    }

    public void removeMember(RemoveMemberOfQunParam removeMemberOfQunParam) throws BusinessException {
        QunBO existQun = this.qunRepository.qunDetailByRoomId(removeMemberOfQunParam.getRoomId());
        Asserts.isTrue(Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!Objects.equals(existQun.getOwnerId(),loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        RemoveMemberParam removeMemberParam = new RemoveMemberParam(existQun.getId(), removeMemberOfQunParam.getMemberId());
        this.qunMemberRepository.removeMember(removeMemberParam);
        //todo 发消息给群主和群成员
        Set<Long> userIds = this.fetchIds(existQun.getOwnerId(),loginUser.getUserId());
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.EXIST_QUN.getType(),new WebsocketExistQunDTO(userIds)),userIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public void dissolve(DissolveParam dissolveParam) throws BusinessException {
        Asserts.isTrue(dissolveParam.getRoomId() == null,BusinessCodeEnum.ROOM_ID_IS_EMPTY);
        QunBO existQun = this.qunRepository.qunDetailByRoomId(dissolveParam.getRoomId());
        Asserts.isTrue(Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!Objects.equals(existQun.getOwnerId(),loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        List<QunMemberBO> qunMemberBOList = this.qunMemberRepository.getQunMembers(existQun.getId());
        this.roomServiceApi.dissolve(dissolveParam.getRoomId());
        this.qunRepository.dissolve(dissolveParam.getRoomId());
        this.qunMemberRepository.dissolve(existQun.getId());
        Set<Long> memberIds = this.fetchMemberIds(qunMemberBOList);
        //todo 推消息给群所有成员
        MessageSendParam messageSendParam = this.qunAssemble.assembleDissolveMessageSendParam(dissolveParam.getRoomId(),existQun);
//        this.chatServiceApi.sendMessage(messageSendParam);
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.DISSOLVE.getType(),new WebsocketDissolveQunDTO(messageSendParam)),memberIds);
    }

    private Set<Long> fetchMemberIds(List<QunMemberBO> memberBOList) {
        return memberBOList.stream().map(QunMemberBO::getMemberId).collect(Collectors.toSet());
    }

    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferOwnerOfQunParam transferOwnerOfQun) throws BusinessException {
        Asserts.isTrue(Objects.isNull(transferOwnerOfQun.getRoomId()), BusinessCodeEnum.ROOM_NOT_FOUND);
        QunBO existQun = this.qunRepository.qunDetailByRoomId(transferOwnerOfQun.getRoomId());
        Asserts.isTrue(Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!Objects.equals(existQun.getOwnerId(),loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        UserProfileDTO newOwner = this.userProfileAppService.getUser(transferOwnerOfQun.getNewOwnerId());
        Asserts.isTrue(newOwner == null, ContactError.USER_IDENTIFY_INFO_EMPTY);
        Boolean isMember = this.qunRepository.isMember(existQun.getId(), transferOwnerOfQun.getNewOwnerId());
        Asserts.isTrue(!isMember, ContactError.USER_IS_NOT_MEMBER);
        this.qunRepository.transfer(existQun, transferOwnerOfQun.getNewOwnerId());
        // TODO 修改群成员类型
        List<QunMemberBO> memberBOList = this.qunMemberAssembler.assembleQunMemberList(existQun.getOwnerId(),transferOwnerOfQun.getNewOwnerId());
        this.qunMemberRepository.updateBatchQunMemberRoleTypeByQunIdAndMemberId(memberBOList);
        //todo 推消息 给新群主
        this.contactMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.TRANSFER.getType(),new WebsocketTransferQunDTO(transferOwnerOfQun.getNewOwnerId())),transferOwnerOfQun.getNewOwnerId());

    }

    public QunDTO getQunByRoomId(Long roomId) {
        QunBO existQun = this.qunRepository.qunDetailByRoomId(roomId);
        return this.qunAssemble.assembleDTO(existQun);
    }
}
