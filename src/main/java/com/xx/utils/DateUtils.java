package com.xx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @discription 时间格式化工具类
 *              工具类采用final关键字修饰并且私有化构造函数表示该对象不可被继承并且无需实例化，
 *              所有方法都是静态方法，传入指定参数就能达到一种开箱即用的效果
 *              所有可以共用的方法，都应该尽可能的使用工具类使其复用
 * @author: xx
 * @date: 2021/2/17 22:48
 */
public final class DateUtils {

    private DateUtils(){}

    /**
     * 时间格式精确到秒
     */
    public static final String SECOND_PRECISE = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date,String format) {
        if (date == null) {
            date = new Date();
        }
        if (StringUtils.isEmpty(format)) {
            format = SECOND_PRECISE;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //jdk1.8+后LocalDate 提供了更加丰富的日期格式化
        return simpleDateFormat.format(date);
    }
}
