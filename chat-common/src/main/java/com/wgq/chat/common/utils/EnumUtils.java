package com.wgq.chat.common.utils;

import com.sheep.protocol.constant.magic.Symbol;
import com.sheep.utils.ConfigUtils;
import com.sheep.utils.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnumUtils {




    public static Map<String, String> getNameValueMap(Class<?> e, String business){
        return getMap(e, true, business);
    }


    public static Map<String, String> getOrdinalValueMap(Class<?> e, String business){
        return getMap(e, false, business);
    }




    /**
     * 将枚举转成map
     * @param e
     * @param nameAsKey
     * @param business
     * @return
     */
    private static Map<String, String> getMap(Class<?> e, boolean nameAsKey, String business){
        Map<String, String> map = new LinkedHashMap<String, String>();
        Class<Enum<?>> c =(Class<Enum<?>>)e;
        Enum<?>[] enums = c.getEnumConstants();
        for (Enum<?> en : enums){
            String key = nameAsKey ? en.name() : String.valueOf(en.ordinal());
            String value = getValue(en, business);
            if (StringUtils.isNullOrEmpty(value)){
                map.put(key, en.toString());
            }else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 获取enum对应的key
     * @param clazz
     * @param business
     * @return
     */
    public static String getValue(Class<?> clazz, int index,String business) {
        String classKey = StringUtils.humpToLower(clazz.getSimpleName());
        Enum<?>enumInstance =(Enum<?>) clazz.getEnumConstants()[index];
        String enumKey = enumInstance.toString().toLowerCase();
        String key = "enum" + Symbol.UNDERLINE + classKey + Symbol.UNDERLINE + enumKey;
        if(!StringUtils.isNullOrEmpty(business)){
            key += Symbol.UNDERLINE + business;
        }
        String languageValue = ConfigUtils.getLanguageValue(key);
        return StringUtils.isNullOrEmpty(languageValue)?enumInstance.name():languageValue;
    }


    public static String getValue(Enum<?> enumInstance, String business){
        String simpleName = enumInstance.getDeclaringClass().getSimpleName();
        String classKey = StringUtils.humpToLower(simpleName);
        String key = "enum" + Symbol.UNDERLINE + classKey + Symbol.UNDERLINE +enumInstance.name().toLowerCase();
        if (StringUtils.isNullOrEmpty(business)){
            key += Symbol.UNDERLINE + business;
        }
        String languageValue = ConfigUtils.getLanguageValue(key);
        return StringUtils.isNullOrEmpty(languageValue)?enumInstance.name():languageValue;
    }

}
