package com.kot.kotmybatis.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class KotStringUtils {

    private static final char UNDERLINE_CHAR = '_';

    public static String joinSplit(Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        collection.forEach(c -> {
            sb.append(c.toString()).append(",");
        });
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    /**
     * 截取后缀
     */
    public static String subSuffix(String oriStr, String suffix) {
        return oriStr.substring(0, oriStr.lastIndexOf(suffix));
    }

    /**
     * 驼峰转下划线
     */
    public static String camel2Underline(String name) {

        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append(UNDERLINE_CHAR);
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString().toLowerCase();
    }

//    public static String tableName(Object mapper) {
//        try {
//            final Field hField = mapper.getClass().getSuperclass().getDeclaredField("h");
//            hField.setAccessible(true);
//            final Object h = hField.get(mapper);
//
//            Field mapperInterfaceField = h.getClass().getDeclaredField("mapperInterface");
//            mapperInterfaceField.setAccessible(true);
//            final Object mapperInterface = mapperInterfaceField.get(h);
//
//            final Field genericInfoField = mapperInterface.getClass().getDeclaredField("genericInfo");
//            genericInfoField.setAccessible(true);
//            final Object genericInfo = genericInfoField.get(mapperInterface);
//
//            final Field superInterfacesField = genericInfo.getClass().getDeclaredField("superInterfaces");
//            superInterfacesField.setAccessible(true);
//            final Object[] superInterfaces = (Object[]) superInterfacesField.get(genericInfo);
//
//            ParameterizedType pType = (ParameterizedType) superInterfaces[0];
//            Type tArgs = pType.getActualTypeArguments()[0];
//            final Class<?> entityClass = Class.forName(tArgs.getTypeName());
//            return tableByClazz(entityClass);
//        } catch (Exception e) {
//            log.error("get table name error", e);
//        }
//        return null;
//
//
//    }


}
