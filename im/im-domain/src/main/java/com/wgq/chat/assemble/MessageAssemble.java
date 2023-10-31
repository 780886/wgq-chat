package com.wgq.chat.assemble;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageStatusEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.inject.Named;

/**
 * @ClassName ChatAssemble
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/16 0:08
 * @Version 1.0
 **/
@Named
public class MessageAssemble {

    public MessageBO assembleMessageBO(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        MessageBO messageBO = new MessageBO();
        messageBO.setRoomId(messageSendParam.getRoomId());
        messageBO.setSenderUserId(userId);
        messageBO.setStatus(MessageStatusEnum.NORMAL.getStatus());
        messageBO.setType(messageSendParam.getMessageType());
        return messageBO;
    }

    public MessageBO assembleMessageBO(MessageBO messageBO, TextMessageDTO textMessageDTO) {
        MessageBO updateMessageBO = new MessageBO();
        updateMessageBO.setId(messageBO.getId());
        updateMessageBO.setContent(textMessageDTO.getContent());
        return updateMessageBO;
    }
}
