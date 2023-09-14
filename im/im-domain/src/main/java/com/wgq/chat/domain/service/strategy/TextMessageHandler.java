package com.wgq.chat.domain.service.strategy;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.inject.Named;

/**
 * @ClassName: TextMessageHandler
 * @Author : wgq
 * @Date :2023/9/14  17:14
 * @Description:
 * @Version :1.0
 */
@Named
public class TextMessageHandler extends AbstractMessageHandler {

    @Override
    MessageTypeEnum getMessageTypeEnum() {
        return MessageTypeEnum.TEXT;
    }

    @Override
    public void checkMsg(MessageSendParam messageSendParam, Long uid) {

    }

    @Override
    public void saveMsg(MessageBO message, MessageSendParam messageSendParam) {

    }

    @Override
    public Object showMsg(MessageBO message) {
        return null;
    }

    @Override
    public Object showReplyMsg(MessageBO message) {
        return null;
    }

    @Override
    public String showContactMsg(MessageBO message) {
        return null;
    }
}
