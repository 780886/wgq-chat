package com.wgq.chat.contact.assemble;

import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;

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
}