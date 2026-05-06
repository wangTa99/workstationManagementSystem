package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 权限Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色ID查询权限列表
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<Permission> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据用户ID查询权限列表
     * @param userId 用户ID
     * @return 权限列表
     */
    List<Permission> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据父ID查询子权限
     * @param parentId 父权限ID
     * @return 子权限列表
     */
    List<Permission> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 查询所有菜单类型的权限
     * @return 菜单权限列表
     */
    List<Permission> selectAllMenus();

    /**
     * 根据权限编码查询权限
     * @param permissionCode 权限编码
     * @return 权限信息
     */
    Permission selectByPermissionCode(@Param("permissionCode") String permissionCode);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
