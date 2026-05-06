package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 订单Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单信息
     */
    Order selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据用户ID查询订单列表（分页）
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 订单列表
     */
    List<Order> selectByUserId(@Param("userId") Long userId,
                                @Param("orderStatus") Integer orderStatus,
                                @Param("startIndex") Long startIndex,
                                @Param("pageSize") Integer pageSize);

    /**
     * 查询订单列表（分页）
     * @param orderNo 订单编号
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param payStatus 支付状态
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 订单列表
     */
    List<Order> selectOrderList(@Param("orderNo") String orderNo,
                                  @Param("userId") Long userId,
                                  @Param("orderStatus") Integer orderStatus,
                                  @Param("payStatus") Integer payStatus,
                                  @Param("startDate") String startDate,
                                  @Param("endDate") String endDate,
                                  @Param("startIndex") Long startIndex,
                                  @Param("pageSize") Integer pageSize);

    /**
     * 查询订单总数
     * @param orderNo 订单编号
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param payStatus 支付状态
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 订单总数
     */
    Long selectOrderCount(@Param("orderNo") String orderNo,
                          @Param("userId") Long userId,
                          @Param("orderStatus") Integer orderStatus,
                          @Param("payStatus") Integer payStatus,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate);

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param orderStatus 订单状态
     * @return 影响行数
     */
    Integer updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") Integer orderStatus);

    /**
     * 更新支付状态
     * @param orderId 订单ID
     * @param payStatus 支付状态
     * @param payTime 支付时间
     * @return 影响行数
     */
    Integer updatePayStatus(@Param("orderId") Long orderId,
                            @Param("payStatus") Integer payStatus,
                            @Param("payTime") java.time.LocalDateTime payTime);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
