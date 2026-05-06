package com.notmaker.controller;

import com.notmaker.dto.CartDTO;
import com.notmaker.service.CartService;
import com.notmaker.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 购物车控制器
 * 处理购物车相关的HTTP请求
 */
@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CartController {

    /**
     * 购物车服务
     */
    @Autowired
    private CartService cartService;

    /**
     * 获取购物车列表
     * @param request HTTP请求
     * @return 购物车列表
     */
    @GetMapping("/list")
    public Map<String, Object> getCartList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<CartVO> cartList = cartService.getCartList(userId);
        return Map.of("code", 200, "message", "获取成功", "data", cartList);
    }

    /**
     * 添加商品到购物车
     * @param cartDTO 购物车参数
     * @param request HTTP请求
     * @return 添加结果
     */
    @PostMapping
    public Map<String, Object> addToCart(@RequestBody CartDTO cartDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.addToCart(userId, cartDTO);
        return Map.of("code", 200, "message", "添加成功", "data", result);
    }

    /**
     * 更新购物车商品数量
     * @param cartId 购物车ID
     * @param quantity 数量
     * @param request HTTP请求
     * @return 更新结果
     */
    @PutMapping("/{cartId}/quantity")
    public Map<String, Object> updateQuantity(@PathVariable Long cartId,
                                               @RequestParam Integer quantity,
                                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.updateQuantity(userId, cartId, quantity);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 选择/取消选择购物车商品
     * @param cartId 购物车ID
     * @param selected 是否选中
     * @param request HTTP请求
     * @return 更新结果
     */
    @PutMapping("/{cartId}/selected")
    public Map<String, Object> updateSelected(@PathVariable Long cartId,
                                                @RequestParam Integer selected,
                                                HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.updateSelected(userId, cartId, selected);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除购物车商品
     * @param cartId 购物车ID
     * @param request HTTP请求
     * @return 删除结果
     */
    @DeleteMapping("/{cartId}")
    public Map<String, Object> deleteCartItem(@PathVariable Long cartId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.deleteCartItem(userId, cartId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }

    /**
     * 清空购物车
     * @param request HTTP请求
     * @return 清空结果
     */
    @DeleteMapping("/clear")
    public Map<String, Object> clearCart(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.clearCart(userId);
        return Map.of("code", 200, "message", "清空成功", "data", result);
    }

    /**
     * 全选/取消全选
     * @param selected 是否选中
     * @param request HTTP请求
     * @return 操作结果
     */
    @PutMapping("/selectAll")
    public Map<String, Object> selectAll(@RequestParam Integer selected, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = cartService.selectAll(userId, selected);
        return Map.of("code", 200, "message", "操作成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
