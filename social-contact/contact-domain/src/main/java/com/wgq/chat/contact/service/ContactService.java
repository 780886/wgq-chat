package com.wgq.chat.contact.service;


import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.CollectionsUtils;
import com.sheep.utils.StringUtils;
import com.wgq.chat.api.RoomServiceApi;
import com.wgq.chat.contact.assemble.ContactAssemble;
import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.chat.contact.bo.ContactsWrapBO;
import com.wgq.chat.contact.bo.FriendBO;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.contact.RemoveFriendParam;
import com.wgq.chat.contact.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.contact.repository.ContactRepository;
import com.wgq.chat.contact.repository.QunRepository;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;


@Named
public class ContactService {


    @Inject
    private SecretService secretService;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private RoomServiceApi roomServiceApi;

    @Inject
    private QunRepository qunRepository;

    @Inject
    private ContactAssemble contactAssemble;

    public ContactBO findFriend(String userIdentify) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(userIdentify), BusinessCodeEnum.USER_DISABLE);
        UserProfileDTO userDto =this.userProfileAppService.getByIdentify(userIdentify);
        String secretIdentify = secretService.encryptUserIdentify(userDto);
        return new ContactBO(userDto, secretIdentify);
    }


    public void removeFriend(RemoveFriendParam removeFriendParam) throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        FriendBO contact = this.contactRepository.findContact(loginUser.getUserId(),removeFriendParam.getFriendId());
        Asserts.isTrue(Objects.isNull(contact), BusinessCodeEnum.FRIEND_NOT_EXIST);
        this.contactRepository.removeById(contact.getContactId());
        //禁用房间
        this.roomServiceApi.disableFriendRoom(Arrays.asList(contact.getUserId(),contact.getFriendId()));
    }

    private Set<Long> fetchFriendIds(List<FriendBO> friendBOS) {
        return friendBOS.stream().map(FriendBO::getFriendId).collect(Collectors.toSet());
    }

    public ContactsWrapBO getContacts() throws BusinessException {
        List<Long> contactUserIds = new ArrayList<>();
        //通讯录加自己
        /**
         * java.lang.UnsupportedOperationException: null
         * 	at java.util.AbstractList.add(AbstractList.java:148) ~[na:1.8.0_281]
         * 	at java.util.AbstractList.add(AbstractList.java:108) ~[na:1.8.0_281]
         * 	at com.sparrow.chat.contact.service.ContactService.getContacts
         */
        contactUserIds.add(ThreadContext.getLoginToken().getUserId());
        List<Long> otherContacts = this.contactRepository.getContacts();
        if (!CollectionsUtils.isNullOrEmpty(otherContacts)) {
            contactUserIds.addAll(otherContacts);
        }
        Map<Long, UserProfileDTO> userProfileMap = this.userProfileAppService.getUserMap(contactUserIds);
        List<QunBO> myQuns = this.qunRepository.getMyQunList();
        return new ContactsWrapBO(userProfileMap.values(), myQuns);
    }

}
