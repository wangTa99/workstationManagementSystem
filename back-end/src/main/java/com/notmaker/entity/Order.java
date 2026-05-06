package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 * 存储订单的核心信息，包括金额、状态、收货信息等
 */
@Data
@TableName("tb_order")
public class Order implements Serializable {

    /**
     * 订单ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 积分抵扣金额
     */
    private BigDecimal pointAmount;

    /**
     * 订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消，5-已退款
     */
    private Integer orderStatus;

    /**
     * 支付状态：0-未支付，1-已支付，2-已退款
     */
    private Integer payStatus;

    /**
     * 支付方式：1-微信支付，2-支付宝，3-银行卡
     */
    private Integer payType;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货地址省份
     */
    private String receiverProvince;

    /**
     * 收货地址城市
     */
    private String receiverCity;

    /**
     * 收货地址区县
     */
    private String receiverDistrict;

    /**
     * 详细收货地址
     */
    private String receiverAddress;

    /**
     * 收货邮编
     */
    private String receiverZip;

    /**
     * 用户备注
     */
    private String userRemark;

    /**
     * 管理员备注
     */
    private String adminRemark;

    /**
     * 发货时间
     */
    private LocalDateTime shipTime;

    /**
     * 物流公司
     */
    private String expressCompany;

    /**
     * 物流单号
     */
    private String expressNo;

    /**
     * 收货时间
     */
    private LocalDateTime receiveTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 取消原因
     */
    private String cancelReason;

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
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
