package com.notmaker.controller;

import com.notmaker.dto.AddressDTO;
import com.notmaker.service.AddressService;
import com.notmaker.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 地址控制器
 * 处理收货地址相关的HTTP请求
 */
@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class AddressController {

    /**
     * 地址服务
     */
    @Autowired
    private AddressService addressService;

    /**
     * 获取地址列表
     * @param request HTTP请求
     * @return 地址列表
     */
    @GetMapping("/list")
    public Map<String, Object> getAddressList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<AddressVO> addresses = addressService.getAddressList(userId);
        return Map.of("code", 200, "message", "获取成功", "data", addresses);
    }

    /**
     * 获取默认地址
     * @param request HTTP请求
     * @return 默认地址
     */
    @GetMapping("/default")
    public Map<String, Object> getDefaultAddress(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        AddressVO address = addressService.getDefaultAddress(userId);
        return Map.of("code", 200, "message", "获取成功", "data", address);
    }

    /**
     * 获取地址详情
     * @param addressId 地址ID
     * @param request HTTP请求
     * @return 地址详情
     */
    @GetMapping("/{addressId}")
    public Map<String, Object> getAddressDetail(@PathVariable Long addressId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        AddressVO address = addressService.getAddressDetail(addressId, userId);
        return Map.of("code", 200, "message", "获取成功", "data", address);
    }

    /**
     * 新增地址
     * @param addressDTO 地址参数
     * @param request HTTP请求
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> addAddress(@RequestBody AddressDTO addressDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = addressService.addAddress(userId, addressDTO);
        return Map.of("code", 200, "message", "新增成功", "data", result);
    }

    /**
     * 更新地址
     * @param addressDTO 地址参数
     * @param request HTTP请求
     * @return 更新结果
     */
    @PutMapping
    public Map<String, Object> updateAddress(@RequestBody AddressDTO addressDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = addressService.updateAddress(userId, addressDTO);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除地址
     * @param addressId 地址ID
     * @param request HTTP请求
     * @return 删除结果
     */
    @DeleteMapping("/{addressId}")
    public Map<String, Object> deleteAddress(@PathVariable Long addressId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = addressService.deleteAddress(addressId, userId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }

    /**
     * 设置默认地址
     * @param addressId 地址ID
     * @param request HTTP请求
     * @return 设置结果
     */
    @PostMapping("/{addressId}/default")
    public Map<String, Object> setDefaultAddress(@PathVariable Long addressId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = addressService.setDefaultAddress(addressId, userId);
        return Map.of("code", 200, "message", "设置成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
