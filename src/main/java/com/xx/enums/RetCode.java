package com.xx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @discription 返回码定义枚举类
 * @author: xx
 * @date: 2021/2/18 14:43
 */
@Getter
@AllArgsConstructor
public enum RetCode {

    SUCCESS("200","操作成功"),
    ERROR("500","服务器处理出错，请稍后重试")
    ;

    private String code;

    private String msg;
}
