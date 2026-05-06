package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品详情视图对象
 * 用于返回商品详情页的完整信息
 */
@Data
public class ProductDetailVO implements Serializable {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类父级名称
     */
    private String parentCategoryName;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品图片列表
     */
    private List<String> imageList;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品原价
     */
    private BigDecimal originalPrice;

    /**
     * 折扣比例
     */
    private BigDecimal discountRate;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 已售数量
     */
    private Integer salesCount;

    /**
     * 商品单位
     */
    private String unit;

    /**
     * 商品重量
     */
    private Double weight;

    /**
     * 商品详情（富文本）
     */
    private String description;

    /**
     * 商品规格列表
     */
    private List<SpecVO> specifications;

    /**
     * 商品评价列表
     */
    private List<CommentVO> comments;

    /**
     * 平均评分
     */
    private BigDecimal avgRating;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 好评率
     */
    private BigDecimal goodRatingRate;

    /**
     * 是否收藏
     */
    private Boolean isCollected;

    /**
     * 商家信息
     */
    private MerchantVO merchant;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 规格视图内部类
     */
    @Data
    public static class SpecVO implements Serializable {
        private String specName;
        private String specValue;
    }

    /**
     * 评价视图内部类
     */
    @Data
    public static class CommentVO implements Serializable {
        private Long id;
        private String userName;
        private String userAvatar;
        private Integer rating;
        private String content;
        private String images;
        private LocalDateTime createTime;
    }

    /**
     * 商家视图内部类
     */
    @Data
    public static class MerchantVO implements Serializable {
        private Long id;
        private String name;
        private String logo;
        private Double rating;
        private Integer productCount;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
