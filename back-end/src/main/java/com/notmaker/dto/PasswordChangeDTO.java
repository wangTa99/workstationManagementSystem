package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 密码修改DTO
 * 用于接收修改密码请求的参数
 */
@Data
public class PasswordChangeDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 原密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认新密码
     */
    private String confirmPassword;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码Key
     */
    private String captchaKey;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
