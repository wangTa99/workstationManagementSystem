package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 轮播图实体类
 * 存储首页轮播图配置信息
 */
@Data
@TableName("tb_banner")
public class Banner implements Serializable {

    /**
     * 轮播图ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 轮播图片URL
     */
    private String imageUrl;

    /**
     * 跳转链接
     */
    private String linkUrl;

    /**
     * 跳转类型：1-无链接，2-内部页面，3-外部链接
     */
    private Integer linkType;

    /**
     * 关联业务ID（如商品ID、文章ID）
     */
    private Long businessId;

    /**
     * 排序号，数字越小越靠前
     */
    private Integer sortOrder;

    /**
     * 轮播位置：1-首页，2-商品详情，3-活动页
     */
    private Integer position;

    /**
     * 背景色
     */
    private String backgroundColor;

    /**
     * 轮播图状态：0-禁用，1-启用
     */
    private Integer status;

    /**
     * 开始展示时间
     */
    private LocalDateTime startTime;

    /**
     * 结束展示时间
     */
    private LocalDateTime endTime;

    /**
     * 点击量
     */
    private Integer clickCount;

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
