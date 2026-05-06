package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地区实体类
 * 存储省市区等行政区划信息
 */
@Data
@TableName("tb_area")
public class Area implements Serializable {

    /**
     * 地区ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 父地区ID
     */
    private Long parentId;

    /**
     * 地区层级深度
     */
    private Integer level;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 区号
     */
    private String cityCode;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 地区状态：0-禁用，1-启用
     */
    private Integer status;

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
