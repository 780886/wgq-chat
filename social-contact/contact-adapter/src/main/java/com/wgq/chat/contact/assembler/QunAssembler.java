package com.wgq.chat.contact.assembler;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.utils.BeanUtils;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.bo.QunDetailWrapBO;
import com.wgq.chat.contact.bo.QunPlazaBO;
import com.wgq.chat.contact.protocol.enums.Category;
import com.wgq.chat.contact.protocol.enums.ContactError;
import com.wgq.chat.contact.protocol.enums.Nationality;
import com.wgq.chat.contact.vo.CategoryVO;
import com.wgq.chat.contact.vo.QunPlazaVO;
import com.wgq.chat.contact.vo.QunVO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
