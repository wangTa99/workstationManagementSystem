package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 通知查询DTO
 * 用于接收通知公告列表查询请求的参数
 */
@Data
public class NoticeQueryDTO implements Serializable {

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 通知类型：1-系统通知，2-活动公告，3-物流通知，4-订单通知
     */
    private Integer noticeType;

    /**
     * 是否置顶：1-是，0-否
     */
    private Integer isTop;

    /**
     * 公告状态：1-发布，0-草稿，2-下架
     */
    private Integer status;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 排序字段：createTime/sortOrder/publishTime
     */
    private String sortBy;

    /**
     * 排序方式：asc/desc
     */
    private String sortOrder;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
