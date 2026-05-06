package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录实体类
 * 存储退款申请和处理信息
 */
@Data
@TableName("tb_refund")
public class Refund implements Serializable {

    /**
     * 退款ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 退款单号
     */
    private String refundNo;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款类型：1-仅退款，2-退货退款
     */
    private Integer refundType;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 退款说明
     */
    private String refundDescription;

    /**
     * 退款凭证图片
     */
    private String refundImages;

    /**
     * 退款状态：0-待处理，1-同意退款，2-拒绝退款，3-已退款，4-已取消
     */
    private Integer refundStatus;

    /**
     * 商家处理备注
     */
    private String handleRemark;

    /**
     * 处理人ID
     */
    private Long handlerId;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 退款方式：1-原路退回，2-退至余额
     */
    private Integer refundWay;

    /**
     * 物流公司
     */
    private String expressCompany;

    /**
     * 物流单号
     */
    private String expressNo;

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
