package com.wgq.chat.contact.assemble;

import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;

import javax.inject.Named;

/**
 * @ClassName QunMemberAssemble
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/3 17:05
 * @Version 1.0
 **/
@Named
public class QunMemberAssembler {

    public MessageSendDTO assembleMessageSendDTO(Long roomId, String qunName) {
        MessageSendDTO messageSendDTO = new MessageSendDTO();
        messageSendDTO.setRoomId(roomId);
        messageSendDTO.setMessageType(MessageTypeEnum.TEXT.getType());
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setContent("您以进去"+qunName+"群");
        messageSendDTO.setBody(textMessageDTO);
        return messageSendDTO;
    }
}
