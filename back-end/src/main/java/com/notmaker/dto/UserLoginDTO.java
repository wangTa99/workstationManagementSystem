package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户登录DTO
 * 用于接收用户登录请求的参数
 */
@Data
public class UserLoginDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码Key
     */
    private String captchaKey;

    /**
     * 记住我
     */
    private Boolean rememberMe;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
