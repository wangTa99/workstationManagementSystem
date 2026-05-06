package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 秒杀活动实体类
 * 存储秒杀商品的活动信息
 */
@Data
@TableName("tb_seckill")
public class Seckill implements Serializable {

    /**
     * 秒杀ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 秒杀活动名称
     */
    private String seckillName;

    /**
     * 秒杀活动描述
     */
    private String description;

    /**
     * 秒杀商品ID
     */
    private Long productId;

    /**
     * 商品名称（冗余）
     */
    private String productName;

    /**
     * 商品主图（冗余）
     */
    private String productImage;

    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 秒杀库存
     */
    private Integer seckillStock;

    /**
     * 已秒杀数量
     */
    private Integer seckillCount;

    /**
     * 每人限购数量
     */
    private Integer perLimit;

    /**
     * 秒杀开始时间
     */
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    private LocalDateTime endTime;

    /**
     * 活动状态：0-未开始，1-进行中，2-已结束
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer sortOrder;

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
