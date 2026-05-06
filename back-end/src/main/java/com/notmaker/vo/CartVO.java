package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车视图对象
 * 用于返回购物车信息的视图
 */
@Data
public class CartVO implements Serializable {

    /**
     * 购物车ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品主图
     */
    private String productImage;

    /**
     * 商品规格
     */
    private String specifications;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 小计金额
     */
    private BigDecimal subtotal;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 是否选中
     */
    private Integer selected;

    /**
     * 是否有效
     */
    private Boolean valid;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
