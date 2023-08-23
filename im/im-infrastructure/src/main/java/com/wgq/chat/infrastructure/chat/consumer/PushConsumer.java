package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.PushMessageDTO;
import com.wgq.chat.protocol.enums.PushTypeEnum;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

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
public class PushConsumer implements RocketMQListener<PushMessageDTO> {

    @Inject
    private WebSocketService webSocketService;

    @Override
    public void onMessage(PushMessageDTO pushMessageDTO) {
        PushTypeEnum pushTypeEnum = PushTypeEnum.of(pushMessageDTO.getPushType());
        switch (pushTypeEnum) {
            case USER:
                webSocketService.sendToUid(pushMessageDTO.getPushBashDTO(), pushMessageDTO.getUserId());
                break;
            case ALL:
//                webSocketService.sendToAllOnline(message.getWsBaseMsg(), null);
//                break;
        }
    }
}
