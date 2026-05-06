package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志实体类
 * 记录用户的操作日志和系统运行日志
 */
@Data
@TableName("tb_log")
public class Log implements Serializable {

    /**
     * 日志ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作类型：1-登录，2-登出，3-新增，4-修改，5-删除，6-查询
     */
    private Integer operationType;

    /**
     * 请求模块
     */
    private String module;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求方式：GET/POST/PUT/DELETE
     */
    private String requestMethod;

    /**
     * 请求IP地址
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 响应状态码
     */
    private Integer responseCode;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 执行时长（毫秒）
     */
    private Long executionTime;

    /**
     * 操作状态：0-失败，1-成功
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
