package com.wgq.chat.infrastructure.chat.produce;

import com.sheep.rocketmq.MQProducer;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.dto.PushMessageDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Description:
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-08-12
 */
@Named
public class PushService {

    @Inject
    private MQProducer mqProducer;

    public void sendPushMsg(PushBashDTO<?> message, List<Long> userList) {
        userList.parallelStream().forEach(userId -> {
            mqProducer.sendMsg(MQConstant.PUSH_TOPIC, new PushMessageDTO(userId, message));
        });
    }

    public void sendPushMsg(PushBashDTO<?> message, Long userId) {
        mqProducer.sendMsg(MQConstant.PUSH_TOPIC, new PushMessageDTO(userId, message));
    }

    /**
     * 推送所有用户 群聊功能
     * @param message
     */
    public void sendPushMsg(PushBashDTO<?> message) {
        mqProducer.sendMsg(MQConstant.PUSH_TOPIC, new PushMessageDTO(message));
    }
}
