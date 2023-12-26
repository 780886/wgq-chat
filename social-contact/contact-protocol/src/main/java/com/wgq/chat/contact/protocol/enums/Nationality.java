package com.wgq.chat.contact.protocol.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName Nationality
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:42
 * @Version 1.0
 **/
public enum Nationality {

    CHINA(1, "中国", "china");

    private Integer id;
    private String name;
    private String flag;

    private static Map<Integer,Nationality> cache;

    static {
        cache = Arrays.asList(Nationality.values()).stream().collect(Collectors.toMap(Nationality::getId, Function.identity()));
    }


    public static Nationality of(Integer id){
        return cache.get(id);
    }


    Nationality(Integer id, String name, String flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

//    public static Nationality getById(Integer id) {
//        for (Nationality nationality : Nationality.values()) {
//            if (nationality.getId().equals(id)) {
//                return nationality;
//            }
//        }
//        return null;
//    }
}
