package com.notmaker.service;

import com.notmaker.entity.Role;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 角色服务接口
 * 定义角色相关的业务操作
 */
public interface RoleService {

    /**
     * 获取所有角色列表
     * @return 角色列表
     */
    List<Role> getAllRoles();

    /**
     * 分页查询角色列表
     * @param keyword 关键词
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 角色列表
     */
    PageVo<Role> getRoleList(String keyword, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取角色信息
     * @param roleId 角色ID
     * @return 角色信息
     */
    Role getRoleById(Long roleId);

    /**
     * 新增角色
     * @param role 角色信息
     * @return 是否成功
     */
    Boolean addRole(Role role);

    /**
     * 更新角色
     * @param role 角色信息
     * @return 是否成功
     */
    Boolean updateRole(Role role);

    /**
     * 删除角色
     * @param roleId 角色ID
     * @return 是否成功
     */
    Boolean deleteRole(Long roleId);

    /**
     * 分配权限给角色
     * @param roleId 角色ID
     * @param permissionIds 权限ID列表
     * @return 是否成功
     */
    Boolean assignPermissions(Long roleId, List<Long> permissionIds);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
