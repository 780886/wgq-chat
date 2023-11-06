package com.wgq.chat.domain.service.strategy;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.assemble.MessageAssemble;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName SystemMessageHandler
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/6 15:26
 * @Version 1.0
 **/
@Named
public class SystemMessageHandler extends AbstractMessageHandler<String>{

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private MessageAssemble messageAssemble;

    @Override
    MessageTypeEnum getMessageTypeEnum() {
        return MessageTypeEnum.SYSTEM;
    }

    @Override
    protected void saveMessage(MessageBO messageBO, String body) throws BusinessException {
        MessageBO updateMessageBO = this.messageAssemble.assembleMessageBO(messageBO,body);
        this.messageRepository.updateById(updateMessageBO);

    }

    @Override
    public Object showMessage(MessageBO message) {
        return message.getContent();
    }

    @Override
    public Object showReplyMessage(MessageBO message) {
        return message.getContent();
    }

    @Override
    public String showContactMessage(MessageBO message) {
        return message.getContent();
    }
}
