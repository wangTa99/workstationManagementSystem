package com.notmaker.controller;

import com.notmaker.dto.UserLoginDTO;
import com.notmaker.dto.UserRegisterDTO;
import com.notmaker.dto.UserUpdateDTO;
import com.notmaker.dto.PasswordChangeDTO;
import com.notmaker.service.UserService;
import com.notmaker.vo.LoginVO;
import com.notmaker.vo.PageVo;
import com.notmaker.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * 处理用户相关的HTTP请求
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class UserController {

    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginDTO loginDTO) {
        LoginVO loginVO = userService.login(loginDTO);
        return Map.of("code", 200, "message", "登录成功", "data", loginVO);
    }

    /**
     * 用户注册
     * @param registerDTO 注册参数
     * @return 注册结果
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserRegisterDTO registerDTO) {
        Boolean result = userService.register(registerDTO);
        return Map.of("code", 200, "message", "注册成功", "data", result);
    }

    /**
     * 退出登录
     * @param request HTTP请求
     * @return 退出结果
     */
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Boolean result = userService.logout(token);
        return Map.of("code", 200, "message", "退出成功", "data", result);
    }

    /**
     * 获取当前登录用户信息
     * @param request HTTP请求
     * @return 用户信息
     */
    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        UserVO userVO = userService.getCurrentUser(userId);
        return Map.of("code", 200, "message", "获取成功", "data", userVO);
    }

    /**
     * 更新用户信息
     * @param updateDTO 更新参数
     * @param request HTTP请求
     * @return 更新结果
     */
    @PutMapping("/update")
    public Map<String, Object> updateUser(@RequestBody UserUpdateDTO updateDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        updateDTO.setId(userId);
        Boolean result = userService.updateUser(updateDTO);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 修改密码
     * @param passwordChangeDTO 密码修改参数
     * @return 修改结果
     */
    @PostMapping("/changePassword")
    public Map<String, Object> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        Boolean result = userService.changePassword(passwordChangeDTO);
        return Map.of("code", 200, "message", "密码修改成功", "data", result);
    }

    /**
     * 发送注册验证码
     * @param phone 手机号
     * @return 发送结果
     */
    @GetMapping("/sendRegisterCaptcha/{phone}")
    public Map<String, Object> sendRegisterCaptcha(@PathVariable String phone) {
        Boolean result = userService.sendRegisterCaptcha(phone);
        return Map.of("code", 200, "message", "验证码发送成功", "data", result);
    }

    /**
     * 发送登录验证码
     * @param phone 手机号
     * @return 发送结果
     */
    @GetMapping("/sendLoginCaptcha/{phone}")
    public Map<String, Object> sendLoginCaptcha(@PathVariable String phone) {
        Boolean result = userService.sendLoginCaptcha(phone);
        return Map.of("code", 200, "message", "验证码发送成功", "data", result);
    }

    /**
     * 获取用户列表（管理端）
     * @param keyword 关键词
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 用户列表
     */
    @GetMapping("/list")
    public Map<String, Object> getUserList(@RequestParam(required = false) String keyword,
                                            @RequestParam(required = false) Integer status,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageVo<UserVO> pageVo = userService.getUserList(keyword, status, pageNum, pageSize);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 根据ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    public Map<String, Object> getUserById(@PathVariable Long userId) {
        UserVO userVO = userService.getUserById(userId);
        return Map.of("code", 200, "message", "获取成功", "data", userVO);
    }

    /**
     * 批量更新用户状态
     * @param ids 用户ID列表
     * @param status 状态
     * @return 更新结果
     */
    @PutMapping("/batchUpdateStatus")
    public Map<String, Object> batchUpdateStatus(@RequestBody Map<String, Object> params) {
        List<Long> ids = (List<Long>) params.get("ids");
        Integer status = (Integer) params.get("status");
        Boolean result = userService.batchUpdateStatus(ids, status);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{userId}")
    public Map<String, Object> deleteUser(@PathVariable Long userId) {
        Boolean result = userService.deleteUser(userId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
