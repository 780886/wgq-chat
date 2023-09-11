package com.wgq.chat.protocol.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: LoginStatusEnum
 * @Author : wgq
 * @Date :2023/8/22  15:15
 * @Description:
 * @Version :1.0
 */
public enum RequestTypeEnum {

    AUTHORIZE(1, "登录认证"),
    LOGIN(2, "请求登录地址"),
    HEARTBEAT(3, "心跳包");


    private final Integer type;
    private final String desc;

    private static Map<Integer, RequestTypeEnum> cache;

    static {
        cache = Arrays.stream(RequestTypeEnum.values()).collect(Collectors.toMap(RequestTypeEnum::getType, Function.identity()));
    }

    RequestTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<Integer, RequestTypeEnum> getCache() {
        return cache;
    }

    public static void setCache(Map<Integer, RequestTypeEnum> cache) {
        RequestTypeEnum.cache = cache;
    }

    public static RequestTypeEnum of(Integer type) {
        return cache.get(type);
    }
}
