package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置实体类
 * 存储系统的各种配置参数
 */
@Data
@TableName("tb_config")
public class Config implements Serializable {

    /**
     * 配置ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 配置分组
     */
    private String configGroup;

    /**
     * 配置项名称
     */
    private String configName;

    /**
     * 配置项键名
     */
    private String configKey;

    /**
     * 配置项值
     */
    private String configValue;

    /**
     * 配置类型：1-文本框，2-下拉框，3-单选框，4-复选框，5-文本域
     */
    private Integer configType;

    /**
     * 是否系统内置：0-否，1-是
     */
    private Integer isSystem;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;

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
