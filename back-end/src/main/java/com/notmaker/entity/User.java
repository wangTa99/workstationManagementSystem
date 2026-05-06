package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 存储系统用户的基本信息，包括登录凭证、个人资料和状态管理
 */
@Data
@TableName("tb_user")
public class User implements Serializable {

    /**
     * 用户ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名，用于登录系统
     */
    private String username;

    /**
     * 密码，经过MD5加密存储
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像URL地址
     */
    private String avatar;

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
     * 用户状态：0-禁用，1-启用
     */
    private Integer status;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标记：0-未删除，1-已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 备注信息
     */
    private String remark;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
