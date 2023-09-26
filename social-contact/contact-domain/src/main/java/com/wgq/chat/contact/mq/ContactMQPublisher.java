package com.wgq.chat.contact.mq;

import com.sheep.mq.MQEvent;
import com.wgq.chat.protocol.dto.PushBashDTO;

import java.util.List;

/**
 * @ClassName ContactMQPublisher
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/26 11:50
 * @Version 1.0
 **/
public interface ContactMQPublisher {

    void publish(String topic, PushBashDTO<?> message, List<Long> userList);

    void publish(String topic, PushBashDTO<?> message, Long userId);

    void publish(String topic, PushBashDTO<?> message);

    void publish(String topic, MQEvent body, Object key);
}
