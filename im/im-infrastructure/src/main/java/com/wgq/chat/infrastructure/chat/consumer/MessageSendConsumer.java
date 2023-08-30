package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.infrastructure.chat.produce.PushService;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.repository.MessageRepository;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MessageSendConsumer
 * @Author : wgq
 * @Date :2023/8/23  14:53
 * @Description:
 * @Version :1.0
 */
@RocketMQMessageListener(consumerGroup = MQConstant.SEND_MSG_GROUP, topic = MQConstant.SEND_MSG_TOPIC)
@Named
public class MessageSendConsumer implements RocketMQListener<MessageSendDTO> {

    @Inject
    private PushService pushService;

    @Inject
    private MessageRepository messageRepository;


    @Override
    public void onMessage(MessageSendDTO messageSendDTO) {
        MessageBO messageBO = this.messageRepository.getMessage(messageSendDTO.getMessageId());
        List<Long> memberUidList = new ArrayList<>();
        memberUidList.add(1L);
        pushService.sendPushMsg(new PushBashDTO<>(1,2),memberUidList);
    }
}
