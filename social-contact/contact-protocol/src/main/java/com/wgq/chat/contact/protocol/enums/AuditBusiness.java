package com.wgq.chat.contact.protocol.enums;

public enum AuditBusiness {

    FRIEND(1,"朋友")
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