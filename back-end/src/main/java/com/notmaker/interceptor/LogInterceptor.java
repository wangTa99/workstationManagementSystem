package com.notmaker.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 * 用于记录请求的基本信息，如请求路径、请求方法、IP地址等
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 请求开始时间键名
     */
    private static final String REQUEST_START_TIME = "requestStartTime";

    /**
     * 前置处理：记录请求开始时间
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param handler 处理器
     * @return 是否继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute(REQUEST_START_TIME, startTime);

        // 打印请求日志
        logRequest(request);

        // 继续执行
        return true;
    }

    /**
     * 后置处理：记录请求处理完成后的信息
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param handler 处理器
     * @param modelAndView 视图模型
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 获取请求开始时间
        Long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        if (startTime != null) {
            // 计算处理时长
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // 打印响应日志
            logResponse(request, response, duration);
        }
    }

    /**
     * 打印请求日志
     * @param request HTTP请求对象
     */
    private void logRequest(HttpServletRequest request) {
        // 获取请求信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String ip = getClientIp(request);
        String userAgent = request.getHeader("User-Agent");

        // 拼接完整URL
        String fullUrl = uri;
        if (queryString != null && !queryString.isEmpty()) {
            fullUrl = uri + "?" + queryString;
        }

        // 打印请求日志
        System.out.println("============= Request Start =============");
        System.out.println("Method: " + method);
        System.out.println("URL: " + fullUrl);
        System.out.println("IP: " + ip);
        System.out.println("User-Agent: " + userAgent);
        System.out.println("==========================================");
    }

    /**
     * 打印响应日志
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param duration 处理时长（毫秒）
     */
    private void logResponse(HttpServletRequest request, HttpServletResponse response, long duration) {
        // 获取响应信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        int status = response.getStatus();

        // 打印响应日志
        System.out.println("============= Request End =============");
        System.out.println("Method: " + method);
        System.out.println("URL: " + uri);
        System.out.println("Status: " + status);
        System.out.println("Duration: " + duration + "ms");
        System.out.println("=========================================");
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
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
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
