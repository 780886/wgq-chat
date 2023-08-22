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
public enum LoginStatusEnum {

    LOGIN(1, "请求登录地址"),
    HEARTBEAT(2, "心跳包"),
    AUTHORIZE(3, "登录认证");

    private final Integer type;
    private final String desc;

    private static Map<Integer, LoginStatusEnum> cache;

    static {
        cache = Arrays.stream(LoginStatusEnum.values()).collect(Collectors.toMap(LoginStatusEnum::getType, Function.identity()));
    }

    LoginStatusEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<Integer, LoginStatusEnum> getCache() {
        return cache;
    }

    public static void setCache(Map<Integer, LoginStatusEnum> cache) {
        LoginStatusEnum.cache = cache;
    }

    public static LoginStatusEnum of(Integer type) {
        return cache.get(type);
    }
}
