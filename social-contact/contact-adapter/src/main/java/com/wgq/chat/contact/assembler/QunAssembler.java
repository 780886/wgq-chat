package com.wgq.chat.contact.assembler;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.enums.StatusRecord;
import com.sheep.utils.BeanUtils;
import com.sheep.utils.EnumUtils;
import com.wgq.chat.contact.bo.*;
import com.wgq.chat.contact.protocol.enums.Category;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.enums.Nationality;
import com.wgq.chat.contact.vo.*;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.*;

/**
 * @ClassName QunAssermbler
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/24 22:21
 * @Version 1.0
 **/
@Named
public class QunAssembler {

    private static Logger logger = LoggerFactory.getLogger(QunAssembler.class);

    public QunVO assemblerQun(QunDetailWrapBO qunDetail) throws BusinessException {
        QunBO qunBO = qunDetail.getQun();
        return this.assemblerQun(qunBO,qunDetail.getOwner());
    }

    public QunVO assemblerQun(QunBO qunBO, UserProfileDTO userProfile) throws BusinessException {
        QunVO qunVo = new QunVO();
        BeanUtils.copyProperties(qunBO, qunVo);
        qunVo.setQunId(qunBO.getId());
        Nationality nationality = Nationality.getById(qunBO.getNationalityId());
        Asserts.isTrue(nationality == null, ContactError.NATIONALITY_OF_QUN_EMPTY);
        qunVo.setNationality(nationality.getName());
        qunVo.setOwnerName(userProfile.getUserName());
        //todo
        Category category = Category.getById(qunBO.getCategoryId().intValue());
        Asserts.isTrue(category == null, ContactError.CATEGORY_OF_QUN_EMPTY);
        qunVo.setCategoryName(category.getName());
        return qunVo;
    }
    public QunPlazaVO assembleQunPlaza(QunPlazaBO qunPlaza) {
        QunPlazaVO qunPlazaVO = new QunPlazaVO();
        List<QunBO> qunList = qunPlaza.getQunList();
        Map<Long, UserProfileDTO> userDicts = qunPlaza.getUserDicts();

        Map<Integer, Category> categories = qunPlaza.getCategoryDicts();
        Map<Long, List<QunVO>> qunMap = new HashMap<>();
        for (QunBO qunBO : qunList) {
            QunVO qunVO = null;
            try {
                qunVO = this.assemblerQun(qunBO, userDicts.get(qunBO.getOwnerId()));
            } catch (BusinessException e) {
                logger.error("qun assemble error qunId:{},qunName:{}", qunBO.getId(), qunBO.getName(), e);
                continue;
            }
            if (!qunMap.containsKey(qunVO.getCategoryId())) {
                qunMap.put(qunVO.getCategoryId(), new ArrayList<>());
            }
            qunMap.get(qunVO.getCategoryId()).add(qunVO);
        }
        qunPlazaVO.setQunMap(qunMap);

        Map<Integer, CategoryVO> categoryVOMap = new HashMap<>();
        for (Integer categoryId : categories.keySet()) {
            categoryVOMap.put(categoryId, this.assembleCategory(categories.get(categoryId)));
        }
        qunPlazaVO.setCategoryDicts(categoryVOMap);
        return qunPlazaVO;
    }

    private CategoryVO assembleCategory(Category category) {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setId(category.getId());
        categoryVO.setCategoryName(category.getName());
        categoryVO.setDescription(category.getDescription());
        return categoryVO;
    }

    public QunAuditWrapVO toQunApplyVoList(AuditWrapBO qunAuditWrapBO) {
        List<AuditBO> auditBOS = qunAuditWrapBO.getAuditList();
        Map<Long, UserProfileDTO> userDictionaries = qunAuditWrapBO.getUserMap();
        List<QunAuditVO> qunApplyList = new ArrayList<>();
        for (AuditBO audit : auditBOS) {
            QunAuditVO qunAuditVO = new QunAuditVO();
            qunAuditVO.setId(audit.getId());
            qunAuditVO.setAuditStatus(audit.getAuditStatus());
            UserProfileDTO applyUser = userDictionaries.get(audit.getApplyUserId());
            if (Objects.nonNull(applyUser)){
                qunAuditVO.setAvatar(applyUser.getAvatar());
                qunAuditVO.setNickName(applyUser.getNickName());
            }
            qunApplyList.add(qunAuditVO);
        }

        /**
         * 1.枚举变的时候，这部分的逻辑不需要改
         * 2.枚举国际化自动支持
         * 3.支持任务枚举的map字典
         * TODO StatusRecord可能需要拆分
         */
        Map<String, String> auditStatusDict = EnumUtils.getOrdinalValueMap(StatusRecord.class);
        return new QunAuditWrapVO(auditStatusDict, qunApplyList);
    }
}
