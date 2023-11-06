package com.wgq.chat.contact.assemble;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.passport.protocol.dto.UserProfileDTO;

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

    public MessageSendParam assembleMessageSendParam(Long roomId) {
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(roomId);
        messageSendParam.setMessageType(MessageTypeEnum.TEXT.getType());
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setContent("我们已经成为好友了，开始聊天吧");
        messageSendParam.setBody(textMessageDTO);
        return messageSendParam;
    }

    public MessageSendParam assembleQunMessageSendParam(QunBO qunBO, UserProfileDTO user) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(qunBO.getRoomId());
        messageSendParam.setMessageType(MessageTypeEnum.SYSTEM.getType());
        StringBuilder builder = new StringBuilder();
        builder.append("\"")
                .append(loginUser.getNickName())
                .append("\"")
                .append("邀请")
                .append(user.getNickName())
                .append("\"")
                .append("加入群聊");
        messageSendParam.setBody(builder.toString());
        return messageSendParam;
    }

}
