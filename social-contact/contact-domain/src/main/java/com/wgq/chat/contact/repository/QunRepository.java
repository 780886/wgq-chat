package com.wgq.chat.contact.repository;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;

import java.util.List;

/**
 * @ClassName QunRepository
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
public interface QunRepository {

    Long createQun(QunBO qunCreateBO);

    void modifyQun(QunModifyParam qunModifyParam) throws BusinessException;

    QunBO getQunDetail(Long id);

    List<QunBO> getQunPlaza(Long categoryId);

    QunBO qunDetail(Long qunId);

    Boolean isMember(Long qunId, Long newOwnerId);

    void dissolve(Long roomId);

    void transfer(QunBO newQun, Long newOwnerId);

    List<QunBO> queryQunPlaza();

    List<QunBO> getMyQunList();

    QunBO getOwnerQun(Long ownerId);

    QunBO qunDetailByRoomId(Long roomId);
}
