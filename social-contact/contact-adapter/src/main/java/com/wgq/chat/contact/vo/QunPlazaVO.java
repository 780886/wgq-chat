package com.wgq.chat.contact.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @ClassName QunPlazaVo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:40
 * @Version 1.0
 **/
@ApiModel("群广场")
public class QunPlazaVO {

    @ApiModelProperty("类别字典")
    private Map<Integer,CategoryVO> categoryDicts;

    @ApiModelProperty("群分类列表")
    private Map<Long, List<QunVO>> qunMap;


    public Map<Integer, CategoryVO> getCategoryDicts() {
        return categoryDicts;
    }

    public void setCategoryDicts(Map<Integer, CategoryVO> categoryDicts) {
        this.categoryDicts = categoryDicts;
    }

    public Map<Long, List<QunVO>> getQunMap() {
        return qunMap;
    }

    public void setQunMap(Map<Long, List<QunVO>> qunMap) {
        this.qunMap = qunMap;
    }
}
