package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 地址Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 根据用户ID查询地址列表
     * @param userId 用户ID
     * @return 地址列表
     */
    List<Address> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询默认地址
     * @param userId 用户ID
     * @return 默认地址
     */
    Address selectDefaultByUserId(@Param("userId") Long userId);

    /**
     * 清除用户默认地址
     * @param userId 用户ID
     * @return 影响行数
     */
    Integer clearDefaultByUserId(@Param("userId") Long userId);

    /**
     * 设置地址为默认
     * @param id 地址ID
     * @param userId 用户ID
     * @return 影响行数
     */
    Integer setDefault(@Param("id") Long id, @Param("userId") Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
