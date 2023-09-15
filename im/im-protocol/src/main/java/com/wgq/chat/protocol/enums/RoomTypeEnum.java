package com.wgq.chat.protocol.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description: 消息状态
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-19
 */
public enum RoomTypeEnum {
    GROUP(1, "群聊"),
    FRIEND(2, "单聊"),
    ;

    private final Integer type;
    private final String desc;

    RoomTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private static Map<Integer, RoomTypeEnum> cache;

    static {
        cache = Arrays.stream(RoomTypeEnum.values()).collect(Collectors.toMap(RoomTypeEnum::getType, Function.identity()));
    }

    public static RoomTypeEnum of(Integer type) {
        return cache.get(type);
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
