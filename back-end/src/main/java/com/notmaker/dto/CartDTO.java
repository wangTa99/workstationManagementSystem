package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 购物车DTO
 * 用于接收购物车相关请求的参数
 */
@Data
public class CartDTO implements Serializable {

    /**
     * 购物车ID（更新和删除时需要）
     */
    private Long id;

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

    /**
     * 是否选中：0-未选中，1-已选中
     */
    private Integer selected;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
