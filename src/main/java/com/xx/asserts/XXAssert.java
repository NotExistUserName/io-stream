package com.xx.asserts;

import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.StringUtils;

/**
 * @discription 断言类
 * @author: xx
 * @date: 2021/2/18 14:39
 */
public class XXAssert{

    /**
     * 断言Boolean表达式是否为真
     * @param pattern
     */
    public static void isTrue(boolean pattern) {
        if (!pattern) {
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
    }

    /**
     * 断言对象不能为空
     * @param target
     */
    public static void notNull(Object target) {
        isTrue(target != null);
    }

    public static void notEmpty(String target) {
        isTrue(StringUtils.isNotEmpty(target));
    }
}
