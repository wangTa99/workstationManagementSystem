package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    User selectByPhone(@Param("phone") String phone);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User selectByEmail(@Param("email") String email);

    /**
     * 根据用户名或手机号查询用户
     * @param loginString 用户名或手机号
     * @return 用户信息
     */
    User selectByUsernameOrPhone(@Param("loginString") String loginString);

    /**
     * 查询用户列表（分页）
     * @param keyword 关键词
     * @param status 状态
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 用户列表
     */
    List<User> selectUserList(@Param("keyword") String keyword,
                              @Param("status") Integer status,
                              @Param("startIndex") Long startIndex,
                              @Param("pageSize") Integer pageSize);

    /**
     * 查询用户总数
     * @param keyword 关键词
     * @param status 状态
     * @return 用户总数
     */
    Long selectUserCount(@Param("keyword") String keyword, @Param("status") Integer status);

    /**
     * 批量更新用户状态
     * @param ids 用户ID列表
     * @param status 状态
     * @return 影响行数
     */
    Integer batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);

    /**
     * 更新用户积分
     * @param userId 用户ID
     * @param points 积分数
     * @return 影响行数
     */
    Integer updateUserScore(@Param("userId") Long userId, @Param("points") Integer points);

    /**
     * 更新用户最后登录信息
     * @param userId 用户ID
     * @param lastLoginTime 最后登录时间
     * @param lastLoginIp 最后登录IP
     * @return 影响行数
     */
    Integer updateLastLoginInfo(@Param("userId") Long userId,
                               @Param("lastLoginTime") java.time.LocalDateTime lastLoginTime,
                               @Param("lastLoginIp") String lastLoginIp);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
