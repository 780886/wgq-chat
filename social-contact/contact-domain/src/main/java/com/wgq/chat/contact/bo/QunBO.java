package com.wgq.chat.contact.bo;

import com.sheep.protocol.enums.StatusRecord;

/**
 * @ClassName QunBO
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/24 22:11
 * @Version 1.0
 **/
public class QunBO{


    private Long id;//群ID
    /**
     * 房间id
     */
    private Long roomId;
    private String name;//群名称
    private String announcement;//群公告
    private Integer nationalityId;
    private String nationality;//所在国籍
    private Long organizationId;//所在组织
    private Long ownerId;//群主
    private Long ownerName;//群主Name
    private Long categoryId;//类型
    private String categoryName;//类型名称
    private String remark;//备注
    private StatusRecord status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public StatusRecord getStatus() {
        return status;
    }

    public void setStatus(StatusRecord status) {
        this.status = status;
    }
}
