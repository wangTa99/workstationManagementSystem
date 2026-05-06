package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 评论查询DTO
 * 用于接收评论列表查询请求的参数
 */
@Data
public class CommentQueryDTO implements Serializable {

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评分开始范围
     */
    private Integer minRating;

    /**
     * 评分结束范围
     */
    private Integer maxRating;

    /**
     * 是否有图片：1-有，0-无
     */
    private Integer hasImage;

    /**
     * 排序字段：createTime/rating/likeCount
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
