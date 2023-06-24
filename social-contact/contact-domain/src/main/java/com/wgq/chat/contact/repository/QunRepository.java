package com.wgq.chat.contact.repository;

import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;

/**
 * @ClassName QunRepository
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
public interface QunRepository {

    public Long createQun(QunCreateParam qunCreateParam);

    void modify(QunModifyParam qunModifyParam);

    QunBO getQunDetail(Long id);
}
