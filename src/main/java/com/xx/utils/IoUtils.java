package com.xx.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @discription io流操作工具类
 * @author: xx
 * @date: 2021/2/17 14:36
 */
@Slf4j
public final class IoUtils {

    private IoUtils(){}

    public static void closeGracefully(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error("关闭流失败",e);
        }
    }
}
