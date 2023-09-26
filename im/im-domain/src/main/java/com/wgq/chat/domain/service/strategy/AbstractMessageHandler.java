package com.wgq.chat.domain.service.strategy;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.annotation.PostConstruct;

/**
 * @ClassName: AbstractMsgHandler
 * @Author : wgq
 * @Date :2023/9/14  17:06
 * @Description:
 * @Version :1.0
 */
public abstract class AbstractMessageHandler {
    @PostConstruct
    private void init() {
        MessageHandlerFactory.register(getMessageTypeEnum().getType(), this);
    }

    /**
     * 消息类型
     */
    abstract MessageTypeEnum getMessageTypeEnum();

    /**
     * 校验消息——保存前校验
     */
    public abstract void checkMessage(MessageSendParam messageSendParam, Long uid) throws BusinessException;

    /**
     * 保存消息
     */
    public abstract void saveMessage( Long messageId, MessageSendParam messageSendParam) throws BusinessException;

    /**
     * 展示消息
     */
    public abstract Object showMessage(MessageBO message);

    /**
     * 被回复时——展示的消息
     */
    public abstract Object showReplyMessage(MessageBO message);

    /**
     * 会话列表——展示的消息
     */
    public abstract String showContactMessage(MessageBO message);
}
