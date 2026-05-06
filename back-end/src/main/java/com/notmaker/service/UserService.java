package com.notmaker.service;

import com.notmaker.entity.User;
import com.notmaker.dto.UserLoginDTO;
import com.notmaker.dto.UserRegisterDTO;
import com.notmaker.dto.UserUpdateDTO;
import com.notmaker.dto.PasswordChangeDTO;
import com.notmaker.vo.LoginVO;
import com.notmaker.vo.UserVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 用户服务接口
 * 定义用户相关的业务操作
 */
public interface UserService {

    /**
     * 用户登录
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    LoginVO login(UserLoginDTO loginDTO);

    /**
     * 用户注册
     * @param registerDTO 注册参数
     * @return 注册结果
     */
    Boolean register(UserRegisterDTO registerDTO);

    /**
     * 退出登录
     * @param token 令牌
     * @return 是否成功
     */
    Boolean logout(String token);

    /**
     * 获取当前登录用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO getCurrentUser(Long userId);

    /**
     * 更新用户信息
     * @param updateDTO 更新参数
     * @return 是否成功
     */
    Boolean updateUser(UserUpdateDTO updateDTO);

    /**
     * 修改密码
     * @param passwordChangeDTO 修改密码参数
     * @return 是否成功
     */
    Boolean changePassword(PasswordChangeDTO passwordChangeDTO);

    /**
     * 重置密码
     * @param phone 手机号
     * @param password 新密码
     * @param captcha 验证码
     * @return 是否成功
     */
    Boolean resetPassword(String phone, String password, String captcha);

    /**
     * 发送注册验证码
     * @param phone 手机号
     * @return 是否成功
     */
    Boolean sendRegisterCaptcha(String phone);

    /**
     * 发送登录验证码
     * @param phone 手机号
     * @return 是否成功
     */
    Boolean sendLoginCaptcha(String phone);

    /**
     * 分页查询用户列表
     * @param keyword 关键词
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageVo<UserVO> getUserList(String keyword, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO getUserById(Long userId);

    /**
     * 批量更新用户状态
     * @param ids 用户ID列表
     * @param status 状态
     * @return 是否成功
     */
    Boolean batchUpdateStatus(List<Long> ids, Integer status);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean deleteUser(Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
