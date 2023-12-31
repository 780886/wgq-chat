package com.wgq.chat.infrastructure.service;

import com.sheep.mq.MQEvent;
import com.sheep.mq.MQPublisher;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.event.PushMessageEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName ImRocketMQPublisher
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/26 11:35
 * @Version 1.0
 **/
@Named
public class ImRocketMQPublisher implements ImMQPublisher {

    @Inject
    private MQPublisher mqPublisher;

    @Override
    public void publish(String topic, PushBashDTO<?> message, List<Long> userList) {
        userList.parallelStream().forEach(userId -> {
            this.mqPublisher.publish(topic, new PushMessageEvent(userId, message));
        });
    }

    @Override
    public void publish(String topic, PushBashDTO<?> message, Long userId) {
        this.mqPublisher.publish(topic, new PushMessageEvent(userId, message));
    }

    @Override
    public void publish(String topic, PushBashDTO<?> message) {
        this.mqPublisher.publish(topic, new PushMessageEvent(message));
    }

    @Override
    public void publish(String topic, MQEvent body) {
        this.mqPublisher.publish(topic, body);
    }

    @Override
    public void publish(String topic, MQEvent body, Object key) {
        this.mqPublisher.publish(topic, body,key);
    }

}
