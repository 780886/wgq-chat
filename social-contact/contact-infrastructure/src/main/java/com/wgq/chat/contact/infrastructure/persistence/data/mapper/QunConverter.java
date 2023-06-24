package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.po.Qun;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;

import javax.inject.Named;

/**
 * @ClassName QunConverter
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:19
 * @Version 1.0
 **/
@Named
public class QunConverter {

    public Qun convert2po(QunCreateParam qunCreateParam){
        LoginUser loginUser = ThreadContext.getLoginToken();
        Qun qun = new Qun();
        qun.setName(qun.getName());
        qun.setAnnouncement(qun.getAnnouncement());
        qun.setNationalityId(qun.getNationalityId());
        qun.setOrganizationId(qun.getOrganizationId());
        qun.setOwnerId(loginUser.getUserId());
        qun.setCategoryId(qun.getCategoryId());
        qun.setRemark(qun.getRemark());
        qun.setStatus(StatusRecord.ENABLE);
        qun.setCreateUserId(loginUser.getUserId());
        qun.setGmtCreate(System.currentTimeMillis());
        qun.setModifiedUserId(loginUser.getUserId());
        qun.setGmtModified(qun.getGmtModified());
        qun.setCreateUserName(loginUser.getUserName());
        qun.setModifiedUserName(loginUser.getUserName());
        return qun;
    }

    public Qun convert2po(QunModifyParam qunModifyParam) {
        Qun qun = new Qun();
        qun.setId(qunModifyParam.getQunId());
        qun.setName(qunModifyParam.getName());
        qun.setAnnouncement(qunModifyParam.getAnnouncement());
        qun.setRemark(qunModifyParam.getRemark());
        return qun;
    }

    public QunBO qun2QunBO(Qun qun) {
        //TODO
        QunBO qunBO = new QunBO();
        qunBO.setQunId(qun.getId());
        qunBO.setName(qun.getName());
        qunBO.setAnnouncement(qun.getAnnouncement());
        qunBO.setNationality("");
        qunBO.setRemark(qun.getRemark());
        qunBO.setOwnerId(qun.getOwnerId());
        qunBO.setOwnerName(1L);
        qunBO.setCategoryId(qun.getCategoryId());
        qunBO.setCategoryName("");
        return qunBO;
    }
}
