package com.wgq.chat.domain.listener;

import com.sheep.protocol.LoginUser;
import com.wgq.chat.domain.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 用户下线监听器
 *
 * @author zhongzb create on 2022/08/26
 */

@Component
public class UserOfflineListener {

    private Logger logger = LoggerFactory.getLogger(UserOfflineListener.class);
    @Autowired
    private WebSocketService webSocketService;
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private UserCache userCache;
//    @Autowired
//    private WSAdapter wsAdapter;

    @Async
    @EventListener(classes = UserOfflineEvent.class)
    public void saveRedisAndPush(UserOfflineEvent event) {
        LoginUser loginUser = event.getLoginUser();
        logger.info("用户下线:{}",loginUser.getUserId());
//        userCache.offline(user.getId(), user.getLastOptTime());
//        //推送给所有在线用户，该用户下线
//        webSocketService.sendToAllOnline(wsAdapter.buildOfflineNotifyResp(event.getUser()), event.getUser().getId());
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
