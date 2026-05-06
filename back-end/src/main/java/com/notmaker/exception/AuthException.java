package com.notmaker.exception;

import lombok.Getter;

/**
 * 认证异常类
 * 用于处理用户认证相关的异常情况
 */
@Getter
public class AuthException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 构造器
     * @param message 错误消息
     */
    public AuthException(String message) {
        super(message);
        this.code = 401;
    }

    /**
     * 构造器
     * @param code 错误码
     * @param message 错误消息
     */
    public AuthException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造器
     * @param message 错误消息
     * @param cause 原始异常
     */
    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.code = 401;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
