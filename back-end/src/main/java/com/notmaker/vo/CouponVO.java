package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券视图对象
 * 用于返回优惠券信息的视图
 */
@Data
public class CouponVO implements Serializable {

    /**
     * 优惠券ID
     */
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
     * 优惠券类型
     */
    private Integer couponType;

    /**
     * 优惠券类型描述
     */
    private String couponTypeDesc;

    /**
     * 优惠面额或折扣
     */
    private BigDecimal value;

    /**
     * 使用门槛
     */
    private BigDecimal minAmount;

    /**
     * 最大优惠金额
     */
    private BigDecimal maxDiscount;

    /**
     * 剩余数量
     */
    private Integer remainingCount;

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
     * 适用商品范围描述
     */
    private String适用范围Desc;

    /**
     * 优惠券状态
     */
    private Integer status;

    /**
     * 是否已领取
     */
    private Boolean isReceived;

    /**
     * 是否已使用
     */
    private Boolean isUsed;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
