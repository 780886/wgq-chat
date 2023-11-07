package com.wgq.chat.contact.assemble;

import com.wgq.chat.protocol.dto.MessageSendDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

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

    public MessageSendParam assembleMessageSendParam(Long roomId, String qunName) {
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(roomId);
        messageSendParam.setMessageType(MessageTypeEnum.SYSTEM.getType());
        messageSendParam.setBody("创建" + qunName + "成功");
        return messageSendParam;
    }
}