package com.wgq.chat.contact.assemble;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.utils.BeanUtils;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.contact.dto.QunDTO;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.protocol.dto.TextMessageDTO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.inject.Named;

/**
 * @ClassName QunAssemble
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/1 11:06
 * @Version 1.0
 **/
@Named
public class QunAssemble {
    public QunBO assembleQunBO(Long roomId, QunCreateParam qunCreateParam) {
        QunBO qunBO = new QunBO();
        qunBO.setRoomId(roomId);
        qunBO.setName(qunCreateParam.getName());
        qunBO.setAnnouncement(qunCreateParam.getAnnouncement());
        qunBO.setRemark(qunCreateParam.getRemark());
        qunBO.setCategoryId(qunCreateParam.getCategoryId());
        qunBO.setNationalityId(qunCreateParam.getNationalityId());
        qunBO.setOrganizationId(qunCreateParam.getOrganizationId());
        return qunBO;
    }

    public MessageSendParam assembleMessageSendParam(Long roomId, LoginUser loginUser) {
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(roomId);
        messageSendParam.setMessageType(MessageTypeEnum.SYSTEM.getType());
        messageSendParam.setBody(loginUser.getUserName()+"退出群聊!");
        return messageSendParam;
    }

    public MessageSendParam assembleInviteFriendMessageSendParam(Long roomId, QunBO existQun) {
        LoginUser loginUser = ThreadContext.getLoginToken();
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(roomId);
        messageSendParam.setMessageType(MessageTypeEnum.TEXT.getType());
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setContent(loginUser.getUserName()+"邀请您加入群聊:"+existQun.getName()+"!");
        messageSendParam.setBody(textMessageDTO);
        return messageSendParam;
    }

    public MessageSendParam assembleDissolveMessageSendParam(Long roomId,QunBO existQun) {
        MessageSendParam messageSendParam = new MessageSendParam();
        messageSendParam.setRoomId(roomId);
        messageSendParam.setMessageType(MessageTypeEnum.SYSTEM.getType());
        messageSendParam.setBody(existQun.getName()+"群聊已解散!");
        return messageSendParam;
    }

    public QunDTO assembleDTO(QunBO existQun) {
        QunDTO qunDTO = new QunDTO();
        BeanUtils.copyProperties(existQun,qunDTO);
        return qunDTO;
    }
}
