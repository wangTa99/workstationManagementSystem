package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户更新DTO
 * 用于接收更新用户信息的参数
 */
@Data
public class UserUpdateDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 所在城市
     */
    private String city;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
