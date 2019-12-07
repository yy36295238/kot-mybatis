package com.kot.kotmybatis.common;

/**
 * @author YangYu
 */

public enum ResultEnum {
    SUCCESS(200, "成功"),
    PARAM_ERR(400, "参数错误"),
    SYS_ERR(500, "系统错误");

    public Integer code;
    public String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
