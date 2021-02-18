package com.xx.exceptions;

import lombok.Data;

/**
 * @discription 自定义异常类
 * @author: xx
 * @date: 2021/2/18 14:40
 */
@Data
public class XXException extends RuntimeException{

    /**
     * 错误编码
     */
    private String code;

    public XXException(String code,String msg) {
        super(msg);
        this.code = code;
    }
}
