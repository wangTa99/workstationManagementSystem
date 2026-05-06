package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券实体类
 * 存储优惠券的定义和规则信息
 */
@Data
@TableName("tb_coupon")
public class Coupon implements Serializable {

    /**
     * 优惠券ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 优惠券编码
     */
    private String couponCode;

    /**
     * 优惠券类型：1-满减券，2-折扣券，3-无门槛券
     */
    private Integer couponType;

    /**
     * 优惠券面额或折扣
     */
    private BigDecimal value;

    /**
     * 使用门槛：满X元可用
     */
    private BigDecimal minAmount;

    /**
     * 最大优惠金额
     */
    private BigDecimal maxDiscount;

    /**
     * 发行总量
     */
    private Integer totalCount;

    /**
     * 已领取数量
     */
    private Integer receivedCount;

    /**
     * 已使用数量
     */
    private Integer usedCount;

    /**
     * 每人限领数量
     */
    private Integer perLimit;

    /**
     * 有效期开始时间
     */
    private LocalDateTime startTime;

    /**
     * 有效期结束时间
     */
    private LocalDateTime endTime;

    /**
     * 适用商品范围：1-全部商品，2-指定分类，3-指定商品
     */
    private Integer适用范围;

    /**
     * 适用分类ID列表，JSON格式
     */
    private String categoryIds;

    /**
     * 适用商品ID列表，JSON格式
     */
    private String productIds;

    /**
     * 优惠券状态：0-未发布，1-进行中，2-已结束
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
