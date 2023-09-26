package com.wgq.chat.contact.protocol.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AuditBusiness {

    FRIEND(1, "申请好友"),
    GROUP(2, "申请加入社群");
    ;


    private Integer business;
    private String name;

    private static Map<Integer,AuditBusiness> cache;
    static {
        cache = Arrays.stream(AuditBusiness.values()).collect(Collectors.toMap(AuditBusiness::getBusiness, Function.identity()));
    }


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

    public static AuditBusiness of(Integer business){
        return cache.get(business);
    }
}
