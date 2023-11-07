package com.wgq.chat.contact.infrastructure.service;

import com.sheep.mq.MQEvent;
import com.sheep.mq.MQPublisher;
import com.wgq.chat.contact.mq.ContactMQPublisher;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.event.PushMessageEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

/**
 * @ClassName ContactRocketMQPublisher
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/26 11:50
 * @Version 1.0
 **/
@Named
public class ContactRocketMQPublisher implements ContactMQPublisher {

    @Inject
    private MQPublisher mqPublisher;

    public void publish(String topic, PushBashDTO<?> message, Set<Long> userList) {
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
    public void publish(String topic, MQEvent body, Object key) {
        this.mqPublisher.publish(topic, body,key);
    }
}
