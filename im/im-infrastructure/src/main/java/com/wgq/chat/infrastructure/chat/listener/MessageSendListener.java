package com.wgq.chat.infrastructure.chat.listener;

import com.sheep.rocketmq.MQProducer;
import com.wgq.chat.domain.event.MessageSendEvent;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.MessageSendDTO;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: MessageSendListener
 * @Author : wgq
 * @Date :2023/8/23  14:48
 * @Description:
 * @Version :1.0
 */
@Named
public class MessageSendListener {

    @Inject
    private MQProducer mqProducer;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, classes = MessageSendEvent.class, fallbackExecution = true)
    public void messageRoute(MessageSendEvent event) {
        Long messageId = event.getMessageId();
        mqProducer.sendSecureMsg(MQConstant.SEND_MSG_TOPIC, new MessageSendDTO(messageId), messageId);
    }
}
