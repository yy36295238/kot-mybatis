package com.kot.kotmybatis.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static void toOtherKey(Map<String, Object> map, String format) {
        Map<String, Object> newMap = new HashMap<>();
        map.keySet().forEach(k -> newMap.put(String.format(format, k), map.get(k)));
        map.putAll(newMap);

    }
}
