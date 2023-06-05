package com.wgq.chat.common.utils;

import com.sparrow.protocol.constant.Constant;
import com.sparrow.protocol.constant.magic.Symbol;

import java.util.Map;

public class ConfigUtils {

    private static Cache<String, String> configCache;
    private static Cache<String, Map<String, String>> internationalization;
    public static final String LANGUAGE = "language";
    public static final String ROOT_PATH = "root_path";

    public static String getLanguageValue(String propertiesKey) {
        String language = getValue(LANGUAGE);
        return getLanguageValue(propertiesKey, language);
    }

    public static String getLanguageValue(String key, String language) {
        if (StringUtils.isNullOrEmpty(language)) {
            language = "zh_cn";
        }
        return getLanguageValue(key, language, null);
    }

    public static String getLanguageValue(String key, String language, String defaultValue) {
        if (StringUtils.isNullOrEmpty(language)) {
            language = getValue(LANGUAGE);
            if (StringUtils.isNullOrEmpty(language)) {
                language = Constant.DEFAULT_LANGUAGE;
            }
        } else {
            language = language.toLowerCase();
        }

        if (internationalization == null) {
            return defaultValue;
        }

        Map<String, String> internationalizationMap = internationalization
                .get(language);
        if (internationalizationMap == null) {
            return defaultValue;
        }
        String value = internationalizationMap.get(key.toLowerCase());
        if (value == null) {
            return defaultValue;
        }
        String rootPath = ConfigUtils.getValue(ROOT_PATH);
        if (!StringUtils.isNullOrEmpty(rootPath) && value.contains(Symbol.DOLLAR + ROOT_PATH)) {
            value = value.replace(Symbol.DOLLAR + ROOT_PATH, rootPath);
        }
        return value;
    }

    public static String getValue(String key) {
        return getValue(key, null);
    }

    public static String getValue(String key, String defaultValue) {
        Object value = configCache.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value.toString();
    }
}
