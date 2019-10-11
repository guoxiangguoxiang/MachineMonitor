package com.zju.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 条件判断工具
 */
public class ConditionUtil {

    public static <T> boolean isNotNull(T t) {
        return !isNull(t);
    }

    public static <T> boolean isNull(T t) {
        if (t == null) {
            return true;
        } else if (t instanceof String){
            return isNull((String)t);
        } else if (t instanceof Integer){
            return isNull((Integer)t);
        } else if (t instanceof Boolean){
            return isNull((Boolean)t);
        } else if (t instanceof Collection){
            return isNull((Collection)t);
        } else if (t instanceof Map){
            return isNull((Map)t);
        } else {
            return false;
        }
    }

    private static boolean isNull(String str) {
        return str == null || str.equals("");
    }

    private static boolean isNull(Integer integer) {
        return integer == null;
    }

    private static boolean isNull(Boolean bool) {
        return bool == null;
    }

    private static boolean isNull(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    private static boolean isNull(Map collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> T listFirst(List<T> list) {
        if (!isNull(list)) {
            return list.get(0);
        }
        return null;
    }


}
