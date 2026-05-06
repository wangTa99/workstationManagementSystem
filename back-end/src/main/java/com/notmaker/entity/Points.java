package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 积分记录实体类
 * 存储用户的积分变动历史
 */
@Data
@TableName("tb_points")
public class Points implements Serializable {

    /**
     * 记录ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 变动积分数，正数表示增加，负数表示减少
     */
    private Integer points;

    /**
     * 变动后积分余额
     */
    private Integer balance;

    /**
     * 变动类型：1-订单奖励，2-签到奖励，3-活动奖励，4-兑换商品，5-退款扣减，6-系统调整
     */
    private Integer changeType;

    /**
     * 相关业务类型：order/sign/activity/exchange
     */
    private String businessType;

    /**
     * 相关业务ID
     */
    private Long businessId;

    /**
     * 变动描述
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
