package com.wgq.chat.domain.service.strategy;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

/**
 * @ClassName: TextMessageHandler
 * @Author : wgq
 * @Date :2023/9/14  17:14
 * @Description:
 * @Version :1.0
 */
@Named
public class TextMessageHandler extends AbstractMessageHandler {

    @Inject
    private MessageRepository messageRepository;

    @Override
    MessageTypeEnum getMessageTypeEnum() {
        return MessageTypeEnum.TEXT;
    }

    @Override
    public void checkMessage(MessageSendParam messageSendParam, Long uid) throws BusinessException {
        //TODO
        TextMessageDTO body = new TextMessageDTO();
        //校验下回复消息
        if (Objects.nonNull(body.getReplyMessageId())){
            MessageBO message = this.messageRepository.getMessage(body.getReplyMessageId());
            //TODO 回复消息不存在
            Asserts.isTrue(Objects.isNull(message),null);
            //TODO 只能回复相同会话内消息
            Asserts.isTrue(!message.getRoomId().equals(messageSendParam.getRoomId()),null);
        }
//        //TOOD @用户id
//        if (!CollectionsUtils.isNullOrEmpty(body.getAtUserIdList())){
//            List<Long> atUserIdList = body.getAtUserIdList();
//        }
    }

    @Override
    public void saveMessage(MessageBO message, MessageSendParam messageSendParam) {//保存文本消息
        //TODO
        TextMessageDTO body = new TextMessageDTO();
        //TODO 扩展消息
        MessageBO messageBO = new MessageBO();
        messageBO.setId(message.getId());
        messageBO.setBody(body.getContent());
        //如果有回复消息
        if (Objects.nonNull(body.getReplyMessageId())){
            Integer gapCount = this.messageRepository.getGapCount(messageSendParam.getRoomId(),body.getReplyMessageId(),message.getId());
            messageBO.setGapCount(gapCount);
            messageBO.setReplyMessageId(body.getReplyMessageId());
        }
        // TODO @功能
        if (!CollectionsUtils.isNullOrEmpty(body.getAtUserIdList())){
            //存扩展字段里面
        }
        this.messageRepository.updateById(messageBO);
    }

    @Override
    public Object showMessage(MessageBO message) {
        return null;
    }

    @Override
    public Object showReplyMessage(MessageBO message) {
        return null;
    }

    @Override
    public String showContactMessage(MessageBO message) {
        return null;
    }
}
