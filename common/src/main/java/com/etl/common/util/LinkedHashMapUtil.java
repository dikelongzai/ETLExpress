package com.etl.common.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by houlongbin on 2017/9/1.
 */
public class LinkedHashMapUtil {
    /**
     * 通过反射获取LinkedHashMap中的末尾元素：
     * <p>
     * 时间复杂度O(1)，访问tail属性
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <K, V> Map.Entry<K, V> getTailByReflection(LinkedHashMap<K, V> map)
            throws NoSuchFieldException, IllegalAccessException {
        Field tail = map.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        return (Map.Entry<K, V>) tail.get(map);
    }
}
