package com.wgq.chat.infrastructure.chat.consumer;

import com.sheep.mq.MQConstant;
import com.sheep.mq.PushMessageDTO;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.enums.PushTypeEnum;
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
public class MessagePushConsumer implements RocketMQListener<PushMessageDTO> {

    private static Logger logger = LoggerFactory.getLogger(MessagePushConsumer.class);

    @Inject
    private WebSocketService webSocketService;

    @Override
    public void onMessage(PushMessageDTO pushMessageDTO) {
        PushTypeEnum pushTypeEnum = PushTypeEnum.of(pushMessageDTO.getPushBashDTO().getType());
        switch (pushTypeEnum) {
            case USER:
                webSocketService.sendToUser(pushMessageDTO.getPushBashDTO(), pushMessageDTO.getUserId());
                break;
            case ALL:
//                webSocketService.sendToAllOnline(message.getWsBaseMsg(), null);
//                break;
                logger.info("push all...");
        }
    }
}
