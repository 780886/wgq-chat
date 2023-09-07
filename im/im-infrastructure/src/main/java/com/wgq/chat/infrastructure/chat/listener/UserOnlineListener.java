package com.wgq.chat.infrastructure.chat.listener;

import com.wgq.chat.domain.event.UserOnlineEvent;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.infrastructure.chat.produce.PushService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName UserOnlineListener
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/3 17:59
 * @Version 1.0
 **/
@Named
public class UserOnlineListener {

    @Inject
    private PushService pushService;
    private UserContainer container = UserContainer.getContainer();

    /**
     * TODO 远程推送
     * @param event
     */
    @Async
    @EventListener(classes = UserOnlineEvent.class)
    public void saveRedisAndPush(UserOnlineEvent event) {
        UserProfileDTO userProfileDTO = event.getUserProfileDTO();
        //上线
        container.online(userProfileDTO.getUserId(), userProfileDTO.getGmtModified());
//        //推送给所有在线用户，该用户登录成功
//        pushService.sendPushMsg(wsAdapter.buildOnlineNotifyResp(event.getUser()));
    }

    /**
     * TODO 远程推送
     * @param event
     */
    @Async
    @EventListener(classes = UserOnlineEvent.class)
    public void saveDB(UserOnlineEvent event) {
//        User user = event.getUser();
//        User update = new User();
//        update.setId(user.getId());
//        update.setLastOptTime(user.getLastOptTime());
//        update.setIpInfo(user.getIpInfo());
//        update.setActiveStatus(ChatActiveStatusEnum.ONLINE.getStatus());
//        userDao.updateById(update);
//        //更新用户ip详情
//        ipService.refreshIpDetailAsync(user.getId());
    }

}
