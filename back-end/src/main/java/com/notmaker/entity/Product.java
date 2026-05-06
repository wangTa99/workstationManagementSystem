package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体类
 * 存储商品的基本信息和库存价格等核心数据
 */
@Data
@TableName("tb_product")
public class Product implements Serializable {

    /**
     * 商品ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 商品主图URL
     */
    private String mainImage;

    /**
     * 商品图片列表，JSON格式存储
     */
    private String images;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品原价
     */
    private BigDecimal originalPrice;

    /**
     * 商品库存数量
     */
    private Integer stock;

    /**
     * 已售数量
     */
    private Integer salesCount;

    /**
     * 商品单位，如：件、个、箱
     */
    private String unit;

    /**
     * 商品重量（克）
     */
    private Double weight;

    /**
     * 商品详情描述
     */
    private String description;

    /**
     * 商品规格，JSON格式存储
     */
    private String specifications;

    /**
     * 商品状态：0-下架，1-上架，2-售罄
     */
    private Integer status;

    /**
     * 是否推荐：0-否，1-是
     */
    private Integer isRecommend;

    /**
     * 是否热卖：0-否，1-是
     */
    private Integer isHot;

    /**
     * 审核状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer auditStatus;

    /**
     * 审核备注
     */
    private String auditRemark;

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
