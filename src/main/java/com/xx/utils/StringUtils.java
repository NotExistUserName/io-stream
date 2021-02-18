package com.xx.utils;

/**
 * @discription 字符串判断工具类
 * @author: xx
 * @date: 2021/2/17 16:18
 */
public final class StringUtils {

    private StringUtils(){}

    /**
     * 判断目标字符串不为空
     * @param target
     * @return
     */
    public static boolean isNotEmpty(String target) {
        return target != null && target.trim().length() > 0;
    }

    /**
     * 判断目标字符串是否为空
     * @param target
     * @return
     */
    public static boolean isEmpty(String target) {
        return !isNotEmpty(target);
    }
}
