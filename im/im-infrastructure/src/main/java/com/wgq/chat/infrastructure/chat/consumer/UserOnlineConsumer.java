package com.wgq.chat.infrastructure.chat.consumer;

import com.sheep.mq.MQConstant;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import com.wgq.passport.protocol.param.UserModifyParam;
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
public class UserOnlineConsumer implements RocketMQListener<UserProfileDTO> {

    private Logger logger = LoggerFactory.getLogger(UserOnlineConsumer.class);

    private UserContainer container = UserContainer.getContainer();

    @Inject
    private UserProfileAppService userProfileAppService;

    @Override
    public void onMessage(UserProfileDTO userProfileDTO) {
        //上线
        logger.info("用户上线:{}",userProfileDTO.getUserId());
        userProfileDTO.setGmtModified(System.currentTimeMillis());
        this.container.online(userProfileDTO.getUserId(), userProfileDTO.getGmtModified());
        UserModifyParam userModifyParam = new UserModifyParam(userProfileDTO.getUserId(), userProfileDTO.getGmtModified(), userProfileDTO.getIp(), StatusRecord.ONLINE);
        try {
            this.userProfileAppService.modify(userModifyParam);
        } catch (BusinessException e) {
            logger.error("用户上线:{},状态修改失败!",userProfileDTO.getUserId(),e);
        }
    }
}
