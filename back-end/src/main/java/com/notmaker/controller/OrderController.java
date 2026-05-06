package com.notmaker.controller;

import com.notmaker.dto.OrderCreateDTO;
import com.notmaker.service.OrderService;
import com.notmaker.vo.OrderVO;
import com.notmaker.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 订单控制器
 * 处理订单相关的HTTP请求
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class OrderController {

    /**
     * 订单服务
     */
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param createDTO 创建订单参数
     * @param request HTTP请求
     * @return 订单信息
     */
    @PostMapping
    public Map<String, Object> createOrder(@RequestBody OrderCreateDTO createDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        OrderVO orderVO = orderService.createOrder(userId, createDTO);
        return Map.of("code", 200, "message", "订单创建成功", "data", orderVO);
    }

    /**
     * 获取订单详情
     * @param orderId 订单ID
     * @param request HTTP请求
     * @return 订单详情
     */
    @GetMapping("/{orderId}")
    public Map<String, Object> getOrderDetail(@PathVariable Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        OrderVO orderVO = orderService.getOrderDetail(orderId, userId);
        return Map.of("code", 200, "message", "获取成功", "data", orderVO);
    }

    /**
     * 获取用户订单列表
     * @param orderStatus 订单状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param request HTTP请求
     * @return 订单列表
     */
    @GetMapping("/list")
    public Map<String, Object> getUserOrderList(@RequestParam(required = false) Integer orderStatus,
                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PageVo<OrderVO> pageVo = orderService.getUserOrderList(userId, orderStatus, pageNum, pageSize);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param reason 取消原因
     * @param request HTTP请求
     * @return 取消结果
     */
    @PostMapping("/{orderId}/cancel")
    public Map<String, Object> cancelOrder(@PathVariable Long orderId,
                                             @RequestParam(required = false) String reason,
                                             HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = orderService.cancelOrder(orderId, userId, reason);
        return Map.of("code", 200, "message", "订单取消成功", "data", result);
    }

    /**
     * 确认收货
     * @param orderId 订单ID
     * @param request HTTP请求
     * @return 确认结果
     */
    @PostMapping("/{orderId}/confirm")
    public Map<String, Object> confirmReceipt(@PathVariable Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = orderService.confirmReceipt(orderId, userId);
        return Map.of("code", 200, "message", "确认收货成功", "data", result);
    }

    /**
     * 支付订单
     * @param orderId 订单ID
     * @param payType 支付方式
     * @return 支付结果
     */
    @PostMapping("/{orderId}/pay")
    public Map<String, Object> payOrder(@PathVariable Long orderId, @RequestParam Integer payType) {
        String payResult = orderService.payOrder(orderId, payType);
        return Map.of("code", 200, "message", "获取支付信息成功", "data", payResult);
    }

    /**
     * 删除订单
     * @param orderId 订单ID
     * @param request HTTP请求
     * @return 删除结果
     */
    @DeleteMapping("/{orderId}")
    public Map<String, Object> deleteOrder(@PathVariable Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = orderService.deleteOrder(orderId, userId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
