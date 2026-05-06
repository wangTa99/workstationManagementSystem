package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据字典实体类
 * 存储系统中使用的静态数据配置
 */
@Data
@TableName("tb_dictionary")
public class Dictionary implements Serializable {

    /**
     * 字典ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典类型：1-系统，2-业务
     */
    private Integer dictType;

    /**
     * 父字典ID，顶级为0
     */
    private Long parentId;

    /**
     * 字典键
     */
    private String dictKey;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 是否启用：0-禁用，1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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
