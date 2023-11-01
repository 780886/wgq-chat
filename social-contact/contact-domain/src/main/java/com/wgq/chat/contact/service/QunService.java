package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.StringUtils;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.contact.assemble.QunAssemble;
import com.wgq.chat.contact.bo.ExistQunBO;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.bo.QunDetailWrapBO;
import com.wgq.chat.contact.bo.QunPlazaBO;
import com.wgq.chat.contact.protocol.enums.Category;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.qun.*;
import com.wgq.chat.contact.repository.QunMemberRepository;
import com.wgq.chat.contact.repository.QunRepository;
import com.wgq.chat.protocol.dto.RoomDTO;
import com.wgq.chat.protocol.enums.BusinessCodeEnum;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * @ClassName QunService
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
@Named
public class QunService {

    @Inject
    private QunRepository qunRepository;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private QunMemberRepository qunMemberRepository;

    @Inject
    private InviteFriendSecurity inviteFriendSecurity;

    @Inject
    private RoomServiceApi roomServiceApi;

    @Inject
    private QunAssemble qunAssemble;

    @Transactional(rollbackFor = Exception.class)
    public Long createQun(QunCreateParam qunCreateParam) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunCreateParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        Asserts.isTrue(qunCreateParam.getCategoryId() == null,ContactError.QUN_CATEGORY_IS_EMPTY);
        Asserts.isTrue(null == qunCreateParam.getNationalityId(), ContactError.NATIONALITY_OF_QUN_EMPTY);

        LoginUser loginUser = ThreadContext.getLoginToken();
        QunBO qunBO = this.qunRepository.getOwnerQun(loginUser.getUserId());
        // TODO 每人只能创建一个群聊
        Asserts.isTrue(Objects.nonNull(qunBO),null);
        //创建群聊房间
        Long roomId = this.roomServiceApi.createQunRoom(loginUser.getUserId());
        QunBO qunCreateBO = this.qunAssemble.assembleQunBO(roomId,qunCreateParam);
        Long qunId = this.qunRepository.createQun(qunCreateBO);
        //插入群主
        this.qunMemberRepository.addQunMember(qunId);
        return roomId;
    }


    public void modify(QunModifyParam qunModifyParam) throws BusinessException {
        Asserts.isTrue(qunModifyParam.getQunId() == null,ContactError.QUN_ID_IS_EMPTY);
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunModifyParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        this.qunRepository.modifyQun(qunModifyParam);
    }

    public QunDetailWrapBO detail(Long qunId) throws BusinessException {
        QunBO qunBo = this.qunRepository.qunDetail(qunId);
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



    public void existQun(Long qunId) throws BusinessException {
        QunBO existQun = this.qunRepository.qunDetail(qunId);
        Asserts.isTrue(existQun == null, ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Boolean isMember = this.qunRepository.isMember(qunId, loginUser.getUserId());
        Asserts.isTrue(!isMember, ContactError.QUN_ID_IS_EMPTY);
        ExistQunBO existQunBO = new ExistQunBO();
        existQunBO.setQunId(qunId);
        existQunBO.setMemberId(loginUser.getUserId());
        this.qunRepository.removeMember(new RemoveMemberOfQunParam(qunId, loginUser.getUserId()));
        //todo 发消息
    }

    public String inviteFriend(InviteFriendParam inviteFriendParam) throws BusinessException {
        Asserts.isTrue(Objects.isNull(inviteFriendParam.getFriendId()),BusinessCodeEnum.FRIEND_ID_IS_EMPTY);
        RoomDTO roomDTO = this.roomServiceApi.getRoom(inviteFriendParam.getRoomId());
        Asserts.isTrue(Objects.isNull(roomDTO), BusinessCodeEnum.ROOM_NOT_FOUND);
        QunBO existQun = this.qunRepository.qunDetailByRoomId(roomDTO.getId());
        Asserts.isTrue(Objects.isNull(existQun), ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        //TODO 目前只有群主才可以邀请好友
        Asserts.isTrue(!Objects.equals(existQun.getOwnerId(),loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        //获取好友的用户信息
        UserProfileDTO newMember = this.userProfileAppService.getUser(inviteFriendParam.getFriendId());
        Asserts.isTrue(Objects.isNull(newMember), ContactError.USER_IDENTIFY_INFO_EMPTY);
        //只能啦一个人
        Boolean isMember = this.qunRepository.isMember(existQun.getId(), inviteFriendParam.getFriendId());
        Asserts.isTrue(isMember, ContactError.USER_IS_MEMBER);
        return this.inviteFriendSecurity.encryptInviteFriend(inviteFriendParam);
    }

    public void removeMember(RemoveMemberOfQunParam removeMemberOfQunParam) throws BusinessException {
        QunBO existQun = this.qunRepository.qunDetail(removeMemberOfQunParam.getQunId());
        Asserts.isTrue(existQun == null, ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!existQun.getOwnerId().equals(loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        this.qunRepository.removeMember(removeMemberOfQunParam);
        //todo 发消息
    }

    public void dissolve(Long qunId) throws BusinessException {
        QunBO existQun = this.qunRepository.qunDetail(qunId);
        Asserts.isTrue(existQun == null, ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!existQun.getOwnerId().equals(loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        this.qunRepository.dissolve(qunId);
        //todo 推消息 走mq
    }

    public void transfer(TransferOwnerOfQunParam transferOwnerOfQun) throws BusinessException {
        QunBO existQun = this.qunRepository.qunDetail(transferOwnerOfQun.getQunId());
        Asserts.isTrue(existQun == null, ContactError.QUN_NOT_FOUND);
        LoginUser loginUser = ThreadContext.getLoginToken();
        Asserts.isTrue(!existQun.getOwnerId().equals(loginUser.getUserId()), ContactError.QUN_OWNER_IS_NOT_MATCH);
        UserProfileDTO newOwner = this.userProfileAppService.getUser(transferOwnerOfQun.getNewOwnerId());
        Asserts.isTrue(newOwner == null, ContactError.USER_IDENTIFY_INFO_EMPTY);
        Boolean isMember = this.qunRepository.isMember(transferOwnerOfQun.getQunId(), transferOwnerOfQun.getNewOwnerId());
        Asserts.isTrue(!isMember, ContactError.USER_IS_NOT_MEMBER);
        this.qunRepository.transfer(existQun, transferOwnerOfQun.getNewOwnerId());
        //todo 推消息 mq
    }

}
