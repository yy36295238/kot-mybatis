package com.kot.kotmybatis.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {


    /**
     * Map 中的key 转成驼峰
     */
    public static Map<String, Object> toCamel(Map<String, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            return map;
        }
        Map<String, Object> retMap = new HashMap<>();
        map.forEach((k, v) -> retMap.put(camelCaseName(k), v));
        return retMap;

    }

    /**
     * List<Map> 中的key 转成驼峰
     */
    public static List<Map<String, Object>> toCamel(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        list.forEach(m -> maps.add(toCamel(m)));
        return maps;

    }

    /**
     * 下划线转驼峰
     */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
}
