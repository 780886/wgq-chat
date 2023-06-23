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
public class QunPlazaVo {

    @ApiModelProperty("类别字典")
    private Map<Long,CategoryVo> categoryDicts;

    @ApiModelProperty("群分类列表")
    private Map<Long, List<QunVO>> qunList;


    public Map<Long, CategoryVo> getCategoryDicts() {
        return categoryDicts;
    }

    public void setCategoryDicts(Map<Long, CategoryVo> categoryDicts) {
        this.categoryDicts = categoryDicts;
    }

    public Map<Long, List<QunVO>> getQunList() {
        return qunList;
    }

    public void setQunList(Map<Long, List<QunVO>> qunList) {
        this.qunList = qunList;
    }
}
