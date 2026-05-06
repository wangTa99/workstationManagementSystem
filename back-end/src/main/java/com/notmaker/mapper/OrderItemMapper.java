package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 订单项Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 根据订单ID查询订单项列表
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据订单编号查询订单项列表
     * @param orderNo 订单编号
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据商品ID查询订单项列表
     * @param productId 商品ID
     * @return 订单项列表
     */
    List<OrderItem> selectByProductId(@Param("productId") Long productId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
