package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 发票实体类
 * 存储用户的发票申请信息
 */
@Data
@TableName("tb_invoice")
public class Invoice implements Serializable {

    /**
     * 发票ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 发票类型：1-普通发票，2-增值税专用发票
     */
    private Integer invoiceType;

    /**
     * 发票抬头类型：1-个人，2-企业
     */
    private Integer headerType;

    /**
     * 发票抬头
     */
    private String invoiceHeader;

    /**
     * 纳税人识别号
     */
    private String taxNumber;

    /**
     * 开票内容
     */
    private String invoiceContent;

    /**
     * 发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 接收邮箱
     */
    private String receiveEmail;

    /**
     * 接收手机
     */
    private String receivePhone;

    /**
     * 发票状态：0-待开票，1-已开票，2-已作废
     */
    private Integer invoiceStatus;

    /**
     * 开票时间
     */
    private LocalDateTime invoiceTime;

    /**
     * 发票号
     */
    private String invoiceNo;

    /**
     * 备注
     */
    private String remark;

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
