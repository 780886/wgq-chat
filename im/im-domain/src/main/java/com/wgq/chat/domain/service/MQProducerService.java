package com.wgq.chat.domain.service;

import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;

import java.util.List;

/**
 * @ClassName MQProducerService
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/8 11:44
 * @Version 1.0
 **/
public interface MQProducerService {

    void sendPushMessage(String topic, UserProfileDTO message);

    void sendPushMessage(String topic,PushBashDTO<?> message, List<Long> userList);

    void sendPushMessage(String topic,PushBashDTO<?> message, Long userId);

    void sendPushMessage(String topic,PushBashDTO<?> message);

    void sendSecureMsg(String topic, Object body, Object key);
}
