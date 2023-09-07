package com.wgq.chat.infrastructure.chat.listener;

import com.wgq.chat.domain.event.UserOfflineEvent;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.infrastructure.chat.produce.PushService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 用户下线监听器
 *
 * @author zhongzb create on 2022/08/26
 */

@Named
public class UserOfflineListener {

    private Logger logger = LoggerFactory.getLogger(UserOfflineListener.class);

    private UserContainer container = UserContainer.getContainer();

    @Inject
    private WebSocketService webSocketService;
    @Autowired
    private PushService pushService;

    @Async
    @EventListener(classes = UserOfflineEvent.class)
    public void saveRedisAndPush(UserOfflineEvent event) {
        UserProfileDTO userProfileDTO = event.getUserProfileDTO();
        logger.info("用户下线:{}",userProfileDTO.getUserId());
        container.online(userProfileDTO.getUserId(), userProfileDTO.getGmtModified());
        //推送给所有在线用户，该用户登录成功
//        pushService.sendPushMsg(new PushBashDTO<>(5,2),memberUidList);
    }

//    @Async
//    @EventListener(classes = UserOfflineEvent.class)
//    public void saveDB(UserOfflineEvent event) {
////        User user = event.getUser();
////        User update = new User();
////        update.setId(user.getId());
////        update.setLastOptTime(user.getLastOptTime());
////        update.setActiveStatus(ChatActiveStatusEnum.OFFLINE.getStatus());
////        userDao.updateById(update);
//    }

}
