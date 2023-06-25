package com.wgq.chat.contact.assembler;

import com.sheep.utils.BeanUtils;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.vo.QunVO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QunAssermbler
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/24 22:21
 * @Version 1.0
 **/
@Named
public class QunAssembler {

    public QunVO toQunVo(QunBO qunBO){
        QunVO qunVO = new QunVO();
        BeanUtils.copyProperties(qunBO,qunVO);
        return qunVO;
    }

    public List<QunVO> toQunVOList(List<QunBO> qunBOS) {
        ArrayList<QunVO> qunVOS = new ArrayList<>();
        for (QunBO qunBO : qunBOS) {
            QunVO qunVO = new QunVO();
            BeanUtils.copyProperties(qunBO,qunVO);
            qunVOS.add(qunVO);
        }
        return qunVOS;
    }
}
