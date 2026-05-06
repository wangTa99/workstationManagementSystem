package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户足迹实体类
 * 记录用户的浏览历史
 */
@Data
@TableName("tb_footprint")
public class Footprint implements Serializable {

    /**
     * 足迹ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 浏览对象类型：1-商品，2-文章
     */
    private Integer objectType;

    /**
     * 对象ID
     */
    private Long objectId;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 对象图片
     */
    private String objectImage;

    /**
     * 商品价格（如果浏览的是商品）
     */
    private Double objectPrice;

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
