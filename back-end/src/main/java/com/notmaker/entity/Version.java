package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * App版本实体类
 * 存储移动端App的版本信息
 */
@Data
@TableName("tb_version")
public class Version implements Serializable {

    /**
     * 版本ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 版本号
     */
    private String versionNo;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 版本类型：1-Android，2-iOS
     */
    private Integer versionType;

    /**
     * 更新内容
     */
    private String updateContent;

    /**
     * 安装包大小（MB）
     */
    private Double packageSize;

    /**
     * 安装包下载地址
     */
    private String downloadUrl;

    /**
     * 是否强制更新：0-否，1-是
     */
    private Integer isForce;

    /**
     * 最低支持版本
     */
    private String minVersion;

    /**
     * 版本状态：0-停用，1-启用
     */
    private Integer status;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    private Integer deleted;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
