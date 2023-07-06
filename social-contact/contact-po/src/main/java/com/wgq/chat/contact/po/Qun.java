package com.wgq.chat.contact.po;//package com.sparrow.chat.contact.po;

import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;
import com.sheep.protocol.enums.StatusRecord;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "qun")
public class Qun implements POJO {

    private Long id;

    private String name;

    private String announcement;

    private Integer nationalityId;

    private Long organizationId;

    private Long ownerId;

    private Long categoryId;

    private String remark;

    private StatusRecord status;

    private Long createUserId;

    private Long gmtCreate;

    private Long modifiedUserId;

    private Long gmtModified;

    private String createUserName;

    private String modifiedUserName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED AUTO_INCREMENT")
    @MethodOrder(order = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @MethodOrder(order = 2)
    @Column(name = "name", columnDefinition = "varchar(64)  DEFAULT '' COMMENT '群名称'", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @MethodOrder(order = 3)
    @Column(name = "announcement", columnDefinition = "varchar(255)  DEFAULT '' COMMENT '群公告'", nullable = false, updatable = false)
    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @MethodOrder(order = 4)
    @Column(name = "nationality_id", columnDefinition = "int(11) UNSIGNED DEFAULT 0 COMMENT '国籍id'", nullable = false, updatable = false)
    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    @MethodOrder(order = 4)
    @Column(name = "organization_id", columnDefinition = "int(11) UNSIGNED DEFAULT 0 COMMENT '机构id'", nullable = false, updatable = false)
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @MethodOrder(order = 5)
    @Column(name = "owner_id", columnDefinition = "int(11) UNSIGNED DEFAULT 0 COMMENT '群主id'", nullable = false, updatable = false)
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @MethodOrder(order = 6)
    @Column(name = "category_id", columnDefinition = "int(11) UNSIGNED DEFAULT 0 COMMENT '分类id'", nullable = false, updatable = false)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @MethodOrder(
            order = 7
    )
    @Column(
            name = "remark",
            columnDefinition = "varchar(255)  DEFAULT 0 COMMENT '备注'",
            nullable = false
    )
    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    @MethodOrder(
            order = 8
    )
    @Column(
            name = "status",
            columnDefinition = "tinyint(3)  UNSIGNED DEFAULT 0 COMMENT 'STATUS'",
            nullable = false
    )
    public StatusRecord getStatus() {
        return status;
    }

    public void setStatus(StatusRecord status) {
        this.status = status;
    }


    @MethodOrder(
        order = 9
    )
    @Column(
        name = "create_user_id",
        columnDefinition = "int(11)  UNSIGNED DEFAULT 0 COMMENT '创建人ID'",
        nullable = false,
        updatable = false
    )
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @MethodOrder(
            order = 10
    )
    @Column(
            name = "gmt_create",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '创建时间'",
            nullable = false,
            updatable = false
    )
    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @MethodOrder(
        order = 11
    )
    @Column(
        name = "modified_user_id",
        columnDefinition = "int(11)  UNSIGNED DEFAULT 0 COMMENT '更新人ID'",
        nullable = false
    )
    public Long getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(Long modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }

    @MethodOrder(
        order = 12
    )
    @Column(
        name = "gmt_modified",
        columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '更新时间'",
        nullable = false
    )
    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }


    @MethodOrder(
            order = 13
    )
    @Column(
            name = "create_user_name",
            columnDefinition = "varchar(64)  DEFAULT '' COMMENT '创建人'",
            nullable = false
    )
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @MethodOrder(
            order = 14
    )
    @Column(
            name = "modified_user_name",
            columnDefinition = "varchar(64)  DEFAULT '' COMMENT '更新人'",
            nullable = false
    )
    public String getModifiedUserName() {
        return modifiedUserName;
    }

    public void setModifiedUserName(String modifiedUserName) {
        this.modifiedUserName = modifiedUserName;
    }
}
