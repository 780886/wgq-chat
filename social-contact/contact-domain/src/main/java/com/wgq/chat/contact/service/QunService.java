package com.wgq.chat.contact.service;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.StringUtils;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;
import com.wgq.chat.contact.repository.QunRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName QunService
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:14
 * @Version 1.0
 **/
@Named
public class QunService {

    @Inject
    private QunRepository qunRepository;

    public Long createQun(QunCreateParam qunCreateParam) throws BusinessException {
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunCreateParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        Asserts.isTrue(qunCreateParam.getCategoryId() == null,ContactError.QUN_CATEGORY_IS_EMPTY);
        return this.qunRepository.createQun(qunCreateParam);
    }


    public void modify(QunModifyParam qunModifyParam) throws BusinessException {
        Asserts.isTrue(qunModifyParam.getQunId() == null,ContactError.QUN_ID_IS_EMPTY);
        Asserts.isTrue(StringUtils.isNullOrEmpty(qunModifyParam.getName()),ContactError.QUN_NAME_IS_EMPTY);
        this.qunRepository.modify(qunModifyParam);
    }

    public QunBO detail(Long qunId) {
        return this.qunRepository.getQunDetail(qunId);
    }
}
