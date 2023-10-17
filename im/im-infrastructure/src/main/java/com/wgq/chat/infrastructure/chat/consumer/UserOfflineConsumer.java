package com.wgq.chat.infrastructure.chat.consumer;

import com.sheep.protocol.BusinessException;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.event.PushMessageEvent;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import com.wgq.passport.protocol.param.UserModifyParam;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

/**
 * @ClassName UserOfflineConsumer
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/8 14:23
 * @Version 1.0
 **/
@RocketMQMessageListener(topic = MQConstant.USER_OFFLINE_TOPIC,consumerGroup = MQConstant.USER_OFFLINE_GROUP)
@Named
public class UserOfflineConsumer implements RocketMQListener<PushMessageEvent> {

    private Logger logger = LoggerFactory.getLogger(UserOfflineConsumer.class);

    private UserContainer container = UserContainer.getContainer();

    @Inject
    private UserProfileAppService userProfileAppService;


    @Override
    public void onMessage(PushMessageEvent pushMessageEvent) {
        if (Objects.nonNull(pushMessageEvent.getPushBashDTO().getData()) && pushMessageEvent.getPushBashDTO().getData() instanceof UserProfileDTO){
            UserProfileDTO userProfileDTO = (UserProfileDTO) pushMessageEvent.getPushBashDTO().getData();
            logger.info("用户下线:{}",userProfileDTO.getUserId());
            userProfileDTO.setGmtModified(System.currentTimeMillis());
            container.offline(userProfileDTO.getUserId(), userProfileDTO.getGmtModified());
            UserModifyParam userModifyParam = new UserModifyParam(userProfileDTO.getUserId(), userProfileDTO.getGmtModified(), userProfileDTO.getIp(), StatusRecord.OFFLINE);
            try {
                this.userProfileAppService.modify(userModifyParam);
            } catch (BusinessException e) {
                logger.error("用户下线:{},下线状态修改失败!",userProfileDTO.getUserId(),e);
            }
        }
    }
}
