package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.enums.PushTypeEnum;
import com.wgq.chat.protocol.event.PushMessageEvent;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: PushConsumerImpl
 * @Author : wgq
 * @Date :2023/8/22  17:13
 * @Description:
 * @Version :1.0
 */
@RocketMQMessageListener(topic = MQConstant.PUSH_TOPIC, consumerGroup = MQConstant.PUSH_GROUP, messageModel = MessageModel.BROADCASTING)
@Named
public class MessagePushEventConsumer implements RocketMQListener<PushMessageEvent> {

    private static Logger logger = LoggerFactory.getLogger(MessagePushEventConsumer.class);

    @Inject
    private WebSocketService webSocketService;

    @Override
    public void onMessage(PushMessageEvent pushMessageEvent) {
        PushTypeEnum pushTypeEnum = PushTypeEnum.of(pushMessageEvent.getPushType());
        switch (pushTypeEnum) {
            case USER:
                webSocketService.sendToUser(pushMessageEvent.getPushBashDTO(), pushMessageEvent.getUserId());
                break;
            case ALL:
                webSocketService.sendToAllOnline(pushMessageEvent.getPushBashDTO(), null);
                break;
//                logger.info("push all...");
            default:
                logger.warn("push type is not match...");
        }
    }
}
