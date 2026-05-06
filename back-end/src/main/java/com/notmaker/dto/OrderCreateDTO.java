package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建DTO
 * 用于接收创建订单请求的参数
 */
@Data
public class OrderCreateDTO implements Serializable {

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 积分抵扣数量
     */
    private Integer points;

    /**
     * 用户备注
     */
    private String userRemark;

    /**
     * 订单来源：1-PC，2-H5，3-Android，4-iOS，5-微信小程序
     */
    private Integer source;

    /**
     * 订单商品列表
     */
    private List<OrderItemDTO> items;

    /**
     * 订单项DTO内部类
     */
    @Data
    public static class OrderItemDTO implements Serializable {
        /**
         * 商品ID
         */
        private Long productId;

        /**
         * 购买数量
         */
        private Integer quantity;

        /**
         * 商品规格，JSON格式
         */
        private String specifications;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
