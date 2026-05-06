package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知公告实体类
 * 存储系统通知和公告信息
 */
@Data
@TableName("tb_notice")
public class Notice implements Serializable {

    /**
     * 通知ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告类型：1-系统通知，2-活动公告，3-物流通知，4-订单通知
     */
    private Integer noticeType;

    /**
     * 公告封面图
     */
    private String coverImage;

    /**
     * 公告链接
     */
    private String linkUrl;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 是否置顶：0-否，1-是
     */
    private Integer isTop;

    /**
     * 是否紧急：0-否，1-是
     */
    private Integer isUrgent;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 公告状态：0-草稿，1-发布，2-下架
     */
    private Integer status;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

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
