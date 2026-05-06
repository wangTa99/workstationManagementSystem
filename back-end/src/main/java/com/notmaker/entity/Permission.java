package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限实体类
 * 定义系统中的权限信息，用于细粒度的访问控制
 */
@Data
@TableName("tb_permission")
public class Permission implements Serializable {

    /**
     * 权限ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码，用于代码中权限判断
     */
    private String permissionCode;

    /**
     * 权限类型：1-菜单，2-按钮，3-接口
     */
    private Integer permissionType;

    /**
     * 父权限ID，顶级权限为0
     */
    private Long parentId;

    /**
     * 权限路径或URL
     */
    private String path;

    /**
     * 组件路径（前端）
     */
    private String component;

    /**
     * 图标标识
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 是否隐藏：0-显示，1-隐藏
     */
    private Integer hidden;

    /**
     * 权限状态：0-禁用，1-启用
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
