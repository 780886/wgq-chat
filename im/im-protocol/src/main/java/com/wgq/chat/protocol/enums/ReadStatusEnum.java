package com.wgq.chat.protocol.enums;

/**
 * @author : limeng
 * @description : 申请阅读状态枚举
 * @date : 2023/07/20
 */
public enum ReadStatusEnum {

    UNREAD(1, "未读"),

    READ(2, "已读");

    private final Integer code;

    private final String desc;

    ReadStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
