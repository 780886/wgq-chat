package com.wgq.chat.contact.vo;

/**
 * @ClassName CategoryVo
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:42
 * @Version 1.0
 **/
public class CategoryVo {

    private Long id;

    private String categoryName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
