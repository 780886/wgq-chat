package com.wgq.chat.domain.service.strategy;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.assemble.MessageAssemble;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.BusinessCodeEnum;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
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
public class TextMessageHandler extends AbstractMessageHandler<TextMessageDTO> {

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private MessageAssemble messageAssemble;

    @Override
    MessageTypeEnum getMessageTypeEnum() {
        return MessageTypeEnum.TEXT;
    }

    @Override
    public void checkMessage(TextMessageDTO textMessageDTO, Long roomId, Long uid) throws BusinessException {
        //校验下回复消息
        if (Objects.nonNull(textMessageDTO.getReplyMessageId())){
            MessageBO message = this.messageRepository.getMessage(textMessageDTO.getReplyMessageId());
            //回复消息不存在
            Asserts.isTrue(Objects.isNull(message), BusinessCodeEnum.REPLY_MESSAGE_NOT_EXIST);
            //只能回复相同会话内的消息
            Asserts.isTrue(!Objects.equals(message.getRoomId(),roomId),BusinessCodeEnum.ROOM_NOT_MATCH);
        }
        // TODO @用户id
        if (!CollectionsUtils.isNullOrEmpty(textMessageDTO.getAtUserIdList())){
        }
    }

    @Override
    public void saveMessage(MessageBO messageBO, TextMessageDTO textMessageDTO) throws BusinessException {//保存文本消息
        MessageBO updateMessageBO = this.messageAssemble.assembleMessageBO(messageBO,textMessageDTO);
        //构造业务消息
        // TODO 敏感词过滤
        // TODO 扩展消息   textMessageDTO.getContent()

        //如果有回复消息
        if (Objects.nonNull(textMessageDTO.getReplyMessageId())){
            //message.getId() 当前保存后的id
            Integer gapCount = this.messageRepository.getGapCount(messageBO.getRoomId(),textMessageDTO.getReplyMessageId(),messageBO.getId());
            updateMessageBO.setGapCount(gapCount);
            updateMessageBO.setReplyMessageId(textMessageDTO.getReplyMessageId());
        }
        // TODO @功能
        if (!CollectionsUtils.isNullOrEmpty(textMessageDTO.getAtUserIdList())){
            //设置@用户
        }
        this.messageRepository.updateById(updateMessageBO);
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
