package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单视图对象
 * 用于返回订单信息的视图
 */
@Data
public class OrderVO implements Serializable {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal pointAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单状态描述
     */
    private String orderStatusDesc;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 支付状态描述
     */
    private String payStatusDesc;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 支付方式描述
     */
    private String payTypeDesc;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 收货人信息
     */
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;

    /**
     * 物流信息
     */
    private String expressCompany;
    private String expressNo;

    /**
     * 订单商品列表
     */
    private List<OrderItemVO> items;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 发货时间
     */
    private LocalDateTime shipTime;

    /**
     * 收货时间
     */
    private LocalDateTime receiveTime;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
