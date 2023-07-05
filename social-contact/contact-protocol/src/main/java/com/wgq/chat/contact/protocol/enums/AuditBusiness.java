package com.wgq.chat.contact.protocol.enums;

public enum AuditBusiness {

    FRIEND(1, "申请好友"),
    GROUP(2, "申请加入社群");
    ;


    private Integer business;
    private String name;


    AuditBusiness(Integer business, String name) {
        this.business = business;
        this.name = name;
    }

    public Integer getBusiness() {
        return business;
    }

    public String getName() {
        return name;
    }
}
