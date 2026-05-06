package com.notmaker.service;

import com.notmaker.dto.AddressDTO;
import com.notmaker.vo.AddressVO;

import java.util.List;

/**
 * 地址服务接口
 * 定义收货地址相关的业务操作
 */
public interface AddressService {

    /**
     * 获取用户地址列表
     * @param userId 用户ID
     * @return 地址列表
     */
    List<AddressVO> getAddressList(Long userId);

    /**
     * 获取默认地址
     * @param userId 用户ID
     * @return 默认地址
     */
    AddressVO getDefaultAddress(Long userId);

    /**
     * 获取地址详情
     * @param addressId 地址ID
     * @param userId 用户ID
     * @return 地址详情
     */
    AddressVO getAddressDetail(Long addressId, Long userId);

    /**
     * 新增地址
     * @param userId 用户ID
     * @param addressDTO 地址参数
     * @return 是否成功
     */
    Boolean addAddress(Long userId, AddressDTO addressDTO);

    /**
     * 更新地址
     * @param userId 用户ID
     * @param addressDTO 地址参数
     * @return 是否成功
     */
    Boolean updateAddress(Long userId, AddressDTO addressDTO);

    /**
     * 删除地址
     * @param addressId 地址ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean deleteAddress(Long addressId, Long userId);

    /**
     * 设置默认地址
     * @param addressId 地址ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean setDefaultAddress(Long addressId, Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
