package com.wgq.chat.protocol.enums;



import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum PushTypeEnum {
    USER(1, "个人"),
    ALL(2, "全部连接用户"),
    ;

    private final Integer type;
    private final String desc;

    private static Map<Integer, PushTypeEnum> cache;

    static {
        cache = Arrays.stream(PushTypeEnum.values()).collect(Collectors.toMap(PushTypeEnum::getType, Function.identity()));
    }

    PushTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<Integer, PushTypeEnum> getCache() {
        return cache;
    }

    public static PushTypeEnum of(Integer type) {
        return cache.get(type);
    }


}
