package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 签到记录实体类
 * 存储用户每日的签到信息
 */
@Data
@TableName("tb_sign")
public class Sign implements Serializable {

    /**
     * 签到ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 签到日期
     */
    private String signDate;

    /**
     * 签到时间
     */
    private LocalDateTime signTime;

    /**
     * 签到类型：1-每日签到，2-连续签到，3-补签
     */
    private Integer signType;

    /**
     * 获得积分
     */
    private Integer rewardPoints;

    /**
     * 连续签到天数
     */
    private Integer continuousDays;

    /**
     * 签到IP地址
     */
    private String ip;

    /**
     * 签到设备
     */
    private String device;

    /**
     * 签到备注
     */
    private String remark;

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
