package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 购物车Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 根据用户ID查询购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<Cart> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询选中的购物车商品
     * @param userId 用户ID
     * @return 选中的购物车商品列表
     */
    List<Cart> selectSelectedByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID和商品ID查询购物车项
     * @param userId 用户ID
     * @param productId 商品ID
     * @param specifications 商品规格
     * @return 购物车项
     */
    Cart selectByUserIdAndProductId(@Param("userId") Long userId,
                                    @Param("productId") Long productId,
                                    @Param("specifications") String specifications);

    /**
     * 清空用户购物车
     * @param userId 用户ID
     * @return 影响行数
     */
    Integer deleteByUserId(@Param("userId") Long userId);

    /**
     * 清空用户购物车中选中的商品
     * @param userId 用户ID
     * @return 影响行数
     */
    Integer deleteSelectedByUserId(@Param("userId") Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
