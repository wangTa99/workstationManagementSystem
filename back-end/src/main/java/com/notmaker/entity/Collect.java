package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏实体类
 * 存储用户收藏的商品或其他内容
 */
@Data
@TableName("tb_collect")
public class Collect implements Serializable {

    /**
     * 收藏ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收藏类型：1-商品，2-文章，3-店铺
     */
    private Integer collectType;

    /**
     * 收藏对象ID
     */
    private Long objectId;

    /**
     * 收藏对象名称
     */
    private String objectName;

    /**
     * 收藏对象图片
     */
    private String objectImage;

    /**
     * 收藏备注
     */
    private String remark;

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
