package com.notmaker.service;

import com.notmaker.entity.Product;
import com.notmaker.dto.ProductQueryDTO;
import com.notmaker.vo.ProductVO;
import com.notmaker.vo.ProductDetailVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 商品服务接口
 * 定义商品相关的业务操作
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    PageVo<ProductVO> getProductList(ProductQueryDTO queryDTO);

    /**
     * 获取商品详情
     * @param productId 商品ID
     * @param userId 用户ID（可为空）
     * @return 商品详情
     */
    ProductDetailVO getProductDetail(Long productId, Long userId);

    /**
     * 获取推荐商品列表
     * @param limit 查询数量
     * @return 推荐商品列表
     */
    List<ProductVO> getRecommendProducts(Integer limit);

    /**
     * 获取热卖商品列表
     * @param limit 查询数量
     * @return 热卖商品列表
     */
    List<ProductVO> getHotProducts(Integer limit);

    /**
     * 新增商品
     * @param product 商品信息
     * @return 是否成功
     */
    Boolean addProduct(Product product);

    /**
     * 更新商品
     * @param product 商品信息
     * @return 是否成功
     */
    Boolean updateProduct(Product product);

    /**
     * 删除商品
     * @param productId 商品ID
     * @return 是否成功
     */
    Boolean deleteProduct(Long productId);

    /**
     * 更新商品状态
     * @param productId 商品ID
     * @param status 状态
     * @return 是否成功
     */
    Boolean updateProductStatus(Long productId, Integer status);

    /**
     * 更新商品库存
     * @param productId 商品ID
     * @param stockChange 库存变化量
     * @return 是否成功
     */
    Boolean updateStock(Long productId, Integer stockChange);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
