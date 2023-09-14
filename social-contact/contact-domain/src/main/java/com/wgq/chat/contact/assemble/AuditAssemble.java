package com.wgq.chat.contact.assemble;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;

import javax.inject.Named;

/**
 * @ClassName: AuditAssemble
 * @Author : wgq
 * @Date :2023/9/14  16:58
 * @Description:
 * @Version :1.0
 */
@Named
public class AuditAssemble {
    public MessageSendDTO assembleMessageSendDTO(Long roomId) {
        MessageSendDTO messageSendDTO = new MessageSendDTO();
        messageSendDTO.setRoomId(roomId);
        messageSendDTO.setMessageType(MessageTypeEnum.TEXT.getType());
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setContent("我们已经成为好友了，开始聊天吧");
        messageSendDTO.setBody(textMessageDTO);
        return messageSendDTO;
    }
}
