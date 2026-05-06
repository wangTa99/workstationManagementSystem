package com.notmaker.interceptor;

import com.notmaker.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 * 用于验证用户登录状态和JWT令牌有效性
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * JWT工具类
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 请求头中的令牌名称
     */
    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 请求属性中存储的用户ID键名
     */
    private static final String USER_ID_ATTRIBUTE = "userId";

    /**
     * 请求属性中存储的用户名键名
     */
    private static final String USER_NAME_ATTRIBUTE = "userName";

    /**
     * 排除拦截的URL列表
     */
    private static final String[] EXCLUDE_URLS = {
            "/api/user/login",
            "/api/user/register",
            "/api/user/sendSms",
            "/api/user/sendEmail",
            "/api/user/resetPassword",
            "/api/file/upload",
            "/api/captcha",
            "/uploads/**",
            "/static/**",
            "/error"
    };

    /**
     * 前置处理：验证用户身份
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param handler 处理器
     * @return 是否继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestUri = request.getRequestURI();

        // 检查是否在排除列表中
        for (String excludeUrl : EXCLUDE_URLS) {
            if (requestUri.contains(excludeUrl) || excludeUrl.contains(requestUri)) {
                return true;
            }
        }

        // 获取令牌
        String token = getToken(request);

        // 验证令牌
        if (!StringUtils.hasText(token)) {
            // 未登录，返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登录，请先登录\"}");
            return false;
        }

        // 验证令牌有效性
        try {
            // 解析令牌获取用户信息
            Long userId = jwtUtil.getUserIdFromToken(token);
            String userName = jwtUtil.getUserNameFromToken(token);

            // 将用户信息存储到请求属性中
            request.setAttribute(USER_ID_ATTRIBUTE, userId);
            request.setAttribute(USER_NAME_ATTRIBUTE, userName);

            // 验证通过，继续执行
            return true;
        } catch (Exception e) {
            // 令牌无效或已过期
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"登录已过期，请重新登录\"}");
            return false;
        }
    }

    /**
     * 从请求中获取令牌
     * @param request HTTP请求对象
     * @return 令牌字符串
     */
    private String getToken(HttpServletRequest request) {
        // 优先从Authorization请求头获取
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authorization)) {
            // 如果是Bearer开头的令牌，去掉Bearer前缀
            if (authorization.startsWith("Bearer ")) {
                return authorization.substring(7);
            }
            return authorization;
        }

        // 其次从请求参数中获取
        String token = request.getParameter("token");
        if (StringUtils.hasText(token)) {
            return token;
        }

        // 最后从Cookie中获取
        if (request.getCookies() != null) {
            for (javax.servlet.http.Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    /**
     * 获取客户端IP地址
     * @param request HTTP请求对象
     * @return IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
