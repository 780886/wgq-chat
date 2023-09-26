package com.wgq.chat.mq;

import com.sheep.mq.MQEvent;
import com.wgq.chat.protocol.dto.PushBashDTO;

import java.util.List;

/**
 * @ClassName ImMQPublisher
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/26 11:34
 * @Version 1.0
 **/
public interface ImMQPublisher {

    void publish(String topic, PushBashDTO<?> message, List<Long> userList);

    void publish(String topic, PushBashDTO<?> message, Long userId);

    void publish(String topic, PushBashDTO<?> message);

    void publish(String topic, MQEvent body, Object key);
}
