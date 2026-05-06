package com.notmaker.service;

import com.notmaker.dto.OrderCreateDTO;
import com.notmaker.entity.Order;
import com.notmaker.vo.OrderVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 订单服务接口
 * 定义订单相关的业务操作
 */
public interface OrderService {

    /**
     * 创建订单
     * @param userId 用户ID
     * @param createDTO 创建订单参数
     * @return 订单信息
     */
    OrderVO createOrder(Long userId, OrderCreateDTO createDTO);

    /**
     * 获取订单详情
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 订单详情
     */
    OrderVO getOrderDetail(Long orderId, Long userId);

    /**
     * 获取用户订单列表
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 订单列表
     */
    PageVo<OrderVO> getUserOrderList(Long userId, Integer orderStatus, Integer pageNum, Integer pageSize);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param userId 用户ID
     * @param reason 取消原因
     * @return 是否成功
     */
    Boolean cancelOrder(Long orderId, Long userId, String reason);

    /**
     * 确认收货
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean confirmReceipt(Long orderId, Long userId);

    /**
     * 支付订单
     * @param orderId 订单ID
     * @param payType 支付方式
     * @return 支付结果
     */
    String payOrder(Long orderId, Integer payType);

    /**
     * 支付回调处理
     * @param orderNo 订单编号
     * @param payType 支付方式
     * @param transactionId 交易流水号
     * @return 是否成功
     */
    Boolean handlePayCallback(String orderNo, Integer payType, String transactionId);

    /**
     * 发货
     * @param orderId 订单ID
     * @param expressCompany 物流公司
     * @param expressNo 物流单号
     * @return 是否成功
     */
    Boolean shipOrder(Long orderId, String expressCompany, String expressNo);

    /**
     * 删除订单
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean deleteOrder(Long orderId, Long userId);

    /**
     * 管理端：分页查询订单列表
     * @param orderNo 订单编号
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param payStatus 支付状态
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 订单列表
     */
    PageVo<OrderVO> getOrderList(String orderNo, Long userId, Integer orderStatus, Integer payStatus,
                                   String startDate, String endDate, Integer pageNum, Integer pageSize);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
