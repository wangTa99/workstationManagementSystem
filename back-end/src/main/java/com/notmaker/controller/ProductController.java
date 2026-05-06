package com.notmaker.controller;

import com.notmaker.dto.ProductQueryDTO;
import com.notmaker.entity.Product;
import com.notmaker.service.ProductService;
import com.notmaker.vo.PageVo;
import com.notmaker.vo.ProductDetailVO;
import com.notmaker.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 * 处理商品相关的HTTP请求
 */
@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class ProductController {

    /**
     * 商品服务
     */
    @Autowired
    private ProductService productService;

    /**
     * 获取商品列表
     * @param queryDTO 查询参数
     * @return 商品列表
     */
    @GetMapping("/list")
    public Map<String, Object> getProductList(ProductQueryDTO queryDTO) {
        PageVo<ProductVO> pageVo = productService.getProductList(queryDTO);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 获取商品详情
     * @param productId 商品ID
     * @param request HTTP请求
     * @return 商品详情
     */
    @GetMapping("/{productId}")
    public Map<String, Object> getProductDetail(@PathVariable Long productId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ProductDetailVO productDetail = productService.getProductDetail(productId, userId);
        return Map.of("code", 200, "message", "获取成功", "data", productDetail);
    }

    /**
     * 获取推荐商品
     * @param limit 数量
     * @return 推荐商品列表
     */
    @GetMapping("/recommend")
    public Map<String, Object> getRecommendProducts(@RequestParam(defaultValue = "10") Integer limit) {
        List<ProductVO> products = productService.getRecommendProducts(limit);
        return Map.of("code", 200, "message", "获取成功", "data", products);
    }

    /**
     * 获取热卖商品
     * @param limit 数量
     * @return 热卖商品列表
     */
    @GetMapping("/hot")
    public Map<String, Object> getHotProducts(@RequestParam(defaultValue = "10") Integer limit) {
        List<ProductVO> products = productService.getHotProducts(limit);
        return Map.of("code", 200, "message", "获取成功", "data", products);
    }

    /**
     * 新增商品
     * @param product 商品信息
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> addProduct(@RequestBody Product product) {
        Boolean result = productService.addProduct(product);
        return Map.of("code", 200, "message", "新增成功", "data", result);
    }

    /**
     * 更新商品
     * @param product 商品信息
     * @return 更新结果
     */
    @PutMapping
    public Map<String, Object> updateProduct(@RequestBody Product product) {
        Boolean result = productService.updateProduct(product);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除商品
     * @param productId 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/{productId}")
    public Map<String, Object> deleteProduct(@PathVariable Long productId) {
        Boolean result = productService.deleteProduct(productId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }

    /**
     * 更新商品状态
     * @param productId 商品ID
     * @param status 状态
     * @return 更新结果
     */
    @PutMapping("/{productId}/status")
    public Map<String, Object> updateProductStatus(@PathVariable Long productId, @RequestParam Integer status) {
        Boolean result = productService.updateProductStatus(productId, status);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
