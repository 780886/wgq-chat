package com.wgq.chat.contact.repository;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.bo.AuditBO;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;
import com.wgq.chat.contact.protocol.qun.RemoveMemberOfQunParam;

import java.util.List;

/**
 * @ClassName QunRepository
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
public interface QunRepository {

    Long createQun(QunCreateParam qunCreateParam);

    void modifyQun(QunModifyParam qunModifyParam) throws BusinessException;

    QunBO getQunDetail(Long id);

    List<QunBO> getQunPlaza(Long categoryId);

    Long joinQun(AuditBO auditBO);

    QunBO qunDetail(Long qunId);

    Boolean isMember(Long qunId, Long newOwnerId);

    void removeMember(RemoveMemberOfQunParam removeMemberOfQunParam);

    void dissolve(Long qunId);

    void transfer(QunBO newQun, Long newOwnerId);

}
