package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 角色Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色编码查询角色
     * @param roleCode 角色编码
     * @return 角色信息
     */
    Role selectByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 查询所有启用角色
     * @return 角色列表
     */
    List<Role> selectAllEnabled();

    /**
     * 查询角色列表（分页）
     * @param keyword 关键词
     * @param status 状态
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 角色列表
     */
    List<Role> selectRoleList(@Param("keyword") String keyword,
                              @Param("status") Integer status,
                              @Param("startIndex") Long startIndex,
                              @Param("pageSize") Integer pageSize);

    /**
     * 查询角色总数
     * @param keyword 关键词
     * @param status 状态
     * @return 角色总数
     */
    Long selectRoleCount(@Param("keyword") String keyword, @Param("status") Integer status);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
