package com.wgq.chat.protocol.enums;


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description: 热点枚举
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-19
 */
public enum HotFlagEnum {
    NOT(0, "非热点"),
    YES(1, "热点"),
    ;

    private final Integer type;
    private final String desc;

    HotFlagEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private static Map<Integer, HotFlagEnum> cache;

    static {
        cache = Arrays.stream(HotFlagEnum.values()).collect(Collectors.toMap(HotFlagEnum::getType, Function.identity()));
    }

    public static HotFlagEnum of(Integer type) {
        return cache.get(type);
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
