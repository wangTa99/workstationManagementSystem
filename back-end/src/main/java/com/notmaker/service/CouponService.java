package com.notmaker.service;

import com.notmaker.vo.CouponVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 优惠券服务接口
 * 定义优惠券相关的业务操作
 */
public interface CouponService {

    /**
     * 获取用户优惠券列表
     * @param userId 用户ID
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 优惠券列表
     */
    PageVo<CouponVO> getUserCouponList(Long userId, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 获取可领取优惠券列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 可领取优惠券列表
     */
    PageVo<CouponVO> getAvailableCouponList(Integer pageNum, Integer pageSize);

    /**
     * 领取优惠券
     * @param userId 用户ID
     * @param couponId 优惠券ID
     * @return 是否成功
     */
    Boolean receiveCoupon(Long userId, Long couponId);

    /**
     * 使用优惠券
     * @param userId 用户ID
     * @param couponId 优惠券ID
     * @param orderId 订单ID
     * @return 是否成功
     */
    Boolean useCoupon(Long userId, Long couponId, Long orderId);

    /**
     * 计算优惠券优惠金额
     * @param userId 用户ID
     * @param couponId 优惠券ID
     * @param orderAmount 订单金额
     * @return 优惠金额
     */
    java.math.BigDecimal calculateDiscount(Long userId, Long couponId, java.math.BigDecimal orderAmount);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
