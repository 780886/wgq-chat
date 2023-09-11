package com.wgq.chat.infrastructure.service;

import com.wgq.chat.domain.service.MQProducerService;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.dto.PushMessageDTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName MQProducerServiceImpl
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/8 11:45
 * @Version 1.0
 **/
@Named
public class MQProducerServiceImpl implements MQProducerService {

    @Inject
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendPushMessage(String topic, UserProfileDTO message) {
        sendMsg(topic,message);
    }

    @Override
    public void sendPushMessage(String topic,PushBashDTO<?> message, List<Long> userList) {
        userList.parallelStream().forEach(userId -> {
            sendMsg(topic, new PushMessageDTO(userId, message));
        });
    }

    @Override
    public void sendPushMessage(String topic,PushBashDTO<?> message, Long userId) {
        sendMsg(topic, new PushMessageDTO(userId, message));
    }

    @Override
    public void sendPushMessage(String topic,PushBashDTO<?> message) {
        sendMsg(topic, new PushMessageDTO(message));
    }

    @Override
    public void sendSecureMsg(String topic, Object body, Object key) {
        Message<Object> build = MessageBuilder.withPayload(body).setHeader("KEYS", key).build();
        this.rocketMQTemplate.send(topic, build);
    }

    private void sendMsg(String topic, Object body) {
        Message<Object> build = MessageBuilder.withPayload(body).build();
        this.rocketMQTemplate.send(topic, build);
    }
}
