package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知视图对象
 * 用于返回通知公告信息的视图
 */
@Data
public class NoticeVO implements Serializable {

    /**
     * 通知ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 通知类型
     */
    private Integer noticeType;

    /**
     * 通知类型描述
     */
    private String noticeTypeDesc;

    /**
     * 封面图
     */
    private String coverImage;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 是否紧急
     */
    private Integer isUrgent;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
