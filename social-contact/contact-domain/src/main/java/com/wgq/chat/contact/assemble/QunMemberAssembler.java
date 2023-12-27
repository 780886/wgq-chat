package com.wgq.chat.contact.assemble;

import com.wgq.chat.contact.bo.QunMemberBO;
import com.wgq.chat.contact.protocol.enums.QunRoleEnum;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

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
        StringBuilder builder = new StringBuilder();
        builder.append("创建").append(qunName).append("成功");
        messageSendParam.setBody(builder.toString());
        return messageSendParam;
    }

    public List<QunMemberBO> assembleQunMemberList(Long oldOwnerId, Long newOwnerId) {
        List<QunMemberBO> qunMemberBOList = new ArrayList<>();
        QunMemberBO oldQunMemberBO = new QunMemberBO();
        oldQunMemberBO.setMemberId(oldOwnerId);
        oldQunMemberBO.setRoleType(QunRoleEnum.MEMBER.getType());
        QunMemberBO newQunMemberBO = new QunMemberBO();
        newQunMemberBO.setMemberId(newOwnerId);
        newQunMemberBO.setRoleType(QunRoleEnum.LEADER.getType());
        qunMemberBOList.add(oldQunMemberBO);
        qunMemberBOList.add(newQunMemberBO);
        return qunMemberBOList;
    }
}
