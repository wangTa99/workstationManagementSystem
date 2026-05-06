package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户注册DTO
 * 用于接收用户注册请求的参数
 */
@Data
public class UserRegisterDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String confirmPassword;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码Key
     */
    private String captchaKey;

    /**
     * 推荐人ID
     */
    private Long referrerId;

    /**
     * 注册来源：1-PC，2-H5，3-Android，4-iOS，5-微信小程序
     */
    private Integer source;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
