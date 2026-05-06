package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 登录视图对象
 * 用于返回登录成功后的用户信息和令牌
 */
@Data
public class LoginVO implements Serializable {

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 过期时间（秒）
     */
    private Long expiresIn;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户角色列表
     */
    private String[] roles;

    /**
     * 权限列表
     */
    private String[] permissions;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
