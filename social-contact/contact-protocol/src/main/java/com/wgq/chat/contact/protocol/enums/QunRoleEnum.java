package com.wgq.chat.contact.protocol.enums;

/**
 * @ClassName QunRoleEnum
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/10 15:12
 * @Version 1.0
 **/
public enum QunRoleEnum {

    LEADER(1, "群主"),
    MANAGER(2, "管理"),
    MEMBER(3, "普通成员"),
    ;

    private Integer type;
    private String name;

    QunRoleEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
