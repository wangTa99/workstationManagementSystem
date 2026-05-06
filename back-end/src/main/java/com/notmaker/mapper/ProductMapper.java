package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 根据商品编码查询商品
     * @param productCode 商品编码
     * @return 商品信息
     */
    Product selectByProductCode(@Param("productCode") String productCode);

    /**
     * 查询商品列表（分页）
     * @param keyword 关键词
     * @param categoryId 分类ID
     * @param status 商品状态
     * @param isRecommend 是否推荐
     * @param isHot 是否热卖
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @param sortBy 排序字段
     * @param sortOrder 排序方式
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 商品列表
     */
    List<Product> selectProductList(@Param("keyword") String keyword,
                                    @Param("categoryId") Long categoryId,
                                    @Param("status") Integer status,
                                    @Param("isRecommend") Integer isRecommend,
                                    @Param("isHot") Integer isHot,
                                    @Param("minPrice") BigDecimal minPrice,
                                    @Param("maxPrice") BigDecimal maxPrice,
                                    @Param("sortBy") String sortBy,
                                    @Param("sortOrder") String sortOrder,
                                    @Param("startIndex") Long startIndex,
                                    @Param("pageSize") Integer pageSize);

    /**
     * 查询商品总数
     * @param keyword 关键词
     * @param categoryId 分类ID
     * @param status 商品状态
     * @param isRecommend 是否推荐
     * @param isHot 是否热卖
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @return 商品总数
     */
    Long selectProductCount(@Param("keyword") String keyword,
                            @Param("categoryId") Long categoryId,
                            @Param("status") Integer status,
                            @Param("isRecommend") Integer isRecommend,
                            @Param("isHot") Integer isHot,
                            @Param("minPrice") BigDecimal minPrice,
                            @Param("maxPrice") BigDecimal maxPrice);

    /**
     * 查询推荐商品列表
     * @param limit 查询数量
     * @return 推荐商品列表
     */
    List<Product> selectRecommendProducts(@Param("limit") Integer limit);

    /**
     * 查询热卖商品列表
     * @param limit 查询数量
     * @return 热卖商品列表
     */
    List<Product> selectHotProducts(@Param("limit") Integer limit);

    /**
     * 更新商品库存
     * @param productId 商品ID
     * @param stockChange 库存变化量
     * @return 影响行数
     */
    Integer updateStock(@Param("productId") Long productId, @Param("stockChange") Integer stockChange);

    /**
     * 更新商品销量
     * @param productId 商品ID
     * @param salesChange 销量变化量
     * @return 影响行数
     */
    Integer updateSalesCount(@Param("productId") Long productId, @Param("salesChange") Integer salesChange);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
