package com.wgq.chat.contact.infrastructure.persistence.data.mapper;

import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.protocol.enums.StatusRecord;
import com.sheep.utils.BeanUtils;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.po.Qun;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        qun.setName(qunCreateParam.getName());
        qun.setAnnouncement(qunCreateParam.getAnnouncement());
        qun.setNationalityId(qunCreateParam.getNationalityId());
        qun.setOrganizationId(qunCreateParam.getOrganizationId());
        qun.setOwnerId(loginUser.getUserId());
        qun.setCategoryId(qunCreateParam.getCategoryId());
        qun.setRemark(qunCreateParam.getRemark());
        qun.setStatus(StatusRecord.ENABLE);
        qun.setCreateUserId(loginUser.getUserId());
        qun.setGmtCreate(System.currentTimeMillis());
        qun.setModifiedUserId(loginUser.getUserId());
        qun.setGmtModified(qun.getGmtCreate());
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
        qunBO.setId(qun.getId());
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

    public List<QunBO> qunList2qunBOList(List<Qun> quns) {
        ArrayList<QunBO> qunBOS = new ArrayList<>();
        for (Qun qun : quns) {
            QunBO qunBO = new QunBO();
            BeanUtils.copyProperties(qun, qunBO);
            qunBOS.add(qunBO);
        }
        return qunBOS;
    }

    public Qun convert2po(QunBO qunBO, Long newOwnerId) {
        Qun qun = new Qun();
        qun.setId(qunBO.getId());
        qun.setOwnerId(newOwnerId);
        return qun;
    }

    public List<QunBO> poList2BoList(List<Qun> quns) {
        if (CollectionsUtils.isNullOrEmpty(quns)) {
            return Collections.emptyList();
        }
        List<QunBO> qunBos = new ArrayList<>(quns.size());
        for (Qun qun : quns) {
            qunBos.add(this.po2Bo(qun));
        }
        return qunBos;
    }

    public QunBO po2Bo(Qun qun) {
        QunBO qunBo = new QunBO();
        BeanUtils.copyProperties(qun, qunBo);
        return qunBo;
    }
}
