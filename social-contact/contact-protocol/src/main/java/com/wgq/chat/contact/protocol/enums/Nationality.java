package com.wgq.chat.contact.protocol.enums;

/**
 * @ClassName Nationality
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:42
 * @Version 1.0
 **/
public enum Nationality {

    CHINA(1,"中国")
    ;

    private Integer id;

    private String name;

    Nationality(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
