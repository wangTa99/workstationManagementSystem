package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 评论创建DTO
 * 用于接收创建评论请求的参数
 */
@Data
public class CommentCreateDTO implements Serializable {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 评分，1-5星
     */
    private Integer rating;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论图片，JSON数组格式
     */
    private String images;

    /**
     * 是否匿名：0-否，1-是
     */
    private Integer isAnonymous;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
