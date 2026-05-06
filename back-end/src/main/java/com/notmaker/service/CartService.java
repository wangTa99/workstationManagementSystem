package com.notmaker.service;

import com.notmaker.dto.CartDTO;
import com.notmaker.vo.CartVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 购物车服务接口
 * 定义购物车相关的业务操作
 */
public interface CartService {

    /**
     * 获取用户购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<CartVO> getCartList(Long userId);

    /**
     * 添加商品到购物车
     * @param userId 用户ID
     * @param cartDTO 购物车参数
     * @return 是否成功
     */
    Boolean addToCart(Long userId, CartDTO cartDTO);

    /**
     * 更新购物车商品数量
     * @param userId 用户ID
     * @param cartId 购物车ID
     * @param quantity 数量
     * @return 是否成功
     */
    Boolean updateQuantity(Long userId, Long cartId, Integer quantity);

    /**
     * 选择/取消选择购物车商品
     * @param userId 用户ID
     * @param cartId 购物车ID
     * @param selected 是否选中
     * @return 是否成功
     */
    Boolean updateSelected(Long userId, Long cartId, Integer selected);

    /**
     * 删除购物车商品
     * @param userId 用户ID
     * @param cartId 购物车ID
     * @return 是否成功
     */
    Boolean deleteCartItem(Long userId, Long cartId);

    /**
     * 清空购物车
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean clearCart(Long userId);

    /**
     * 清空选中的购物车商品
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean clearSelectedItems(Long userId);

    /**
     * 全选/取消全选
     * @param userId 用户ID
     * @param selected 是否选中
     * @return 是否成功
     */
    Boolean selectAll(Long userId, Integer selected);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
