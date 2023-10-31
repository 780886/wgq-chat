package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.event.UserOnlineEvent;
import com.wgq.chat.protocol.event.UserProfileEvent;
import com.wgq.passport.api.UserProfileAppService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName UserOnlineConsume
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/8 14:10
 * @Version 1.0
 **/
@RocketMQMessageListener(topic = MQConstant.USER_ONLINE_TOPIC,consumerGroup = MQConstant.USER_ONLINE_GROUP)
@Named
public class UserOnlineConsumer implements RocketMQListener<UserOnlineEvent> {

    private Logger logger = LoggerFactory.getLogger(UserOnlineConsumer.class);

    private UserContainer container = UserContainer.getContainer();

    @Inject
    private UserProfileAppService userProfileAppService;

    @Override
    public void onMessage(UserOnlineEvent userOnlineEvent) {
        try {
            UserProfileEvent userProfileEvent = userOnlineEvent.getUserProfileEvent();
            logger.info("用户上线:{} 上线时间:{}",userOnlineEvent.getUserId(),userProfileEvent.getLastLoginTime());
            this.container.online(userOnlineEvent.getUserId(), userProfileEvent.getLastLoginTime());
        } catch (Exception e) {
            logger.error("用户上线:{},状态修改失败!",userOnlineEvent.getUserId(),e);
        }
    }
}
