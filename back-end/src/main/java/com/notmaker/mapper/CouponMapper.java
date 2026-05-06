package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 优惠券Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {

    /**
     * 查询可领取的优惠券列表
     * @param status 状态
     * @return 可领取的优惠券列表
     */
    List<Coupon> selectAvailableCoupons(@Param("status") Integer status);

    /**
     * 更新优惠券已领取数量
     * @param couponId 优惠券ID
     * @return 影响行数
     */
    Integer incrementReceivedCount(@Param("couponId") Long couponId);

    /**
     * 更新优惠券已使用数量
     * @param couponId 优惠券ID
     * @return 影响行数
     */
    Integer incrementUsedCount(@Param("couponId") Long couponId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
