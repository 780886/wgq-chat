package com.wgq.chat.contact.bo;

/**
 * @ClassName QunBO
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/24 22:11
 * @Version 1.0
 **/
public class QunBO {

    private Long qunId;//群ID
    private String name;//群名称
    private String announcement;//群公告
    private String nationality;//所在国籍
    //private Long organizationId;//所在组织
    private String remark;//备注
    private Long ownerId;//群主
    private Long ownerName;//群主Name
    private Long categoryId;//类型
    private String categoryName;//类型名称

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(Long ownerName) {
        this.ownerName = ownerName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
