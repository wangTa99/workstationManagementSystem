package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 商品查询DTO
 * 用于接收商品列表查询请求的参数
 */
@Data
public class ProductQueryDTO implements Serializable {

    /**
     * 商品名称关键字
     */
    private String keyword;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 价格区间开始
     */
    private Double minPrice;

    /**
     * 价格区间结束
     */
    private Double maxPrice;

    /**
     * 商品状态：1-上架，0-下架
     */
    private Integer status;

    /**
     * 是否推荐：1-是，0-否
     */
    private Integer isRecommend;

    /**
     * 是否热卖：1-是，0-否
     */
    private Integer isHot;

    /**
     * 排序字段：price/salesCount/createTime
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
