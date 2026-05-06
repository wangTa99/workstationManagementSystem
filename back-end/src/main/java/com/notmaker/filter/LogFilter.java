package com.notmaker.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志过滤器
 * 用于记录所有HTTP请求和响应的日志信息
 */
@Component
public class LogFilter implements Filter {

    /**
     * 请求开始时间键名
     */
    private static final String REQUEST_START_TIME = "logFilterStartTime";

    /**
     * 初始化过滤器
     * @param filterConfig 过滤器配置
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化逻辑
        System.out.println("LogFilter initialized");
    }

    /**
     * 执行过滤
     * @param request 请求对象
     * @param response 响应对象
     * @param chain 过滤器链
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 转换为HttpServletRequest和HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        httpRequest.setAttribute(REQUEST_START_TIME, startTime);

        // 获取请求信息
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();
        String ip = getClientIp(httpRequest);

        // 拼接完整URL
        String fullUrl = uri;
        if (queryString != null && !queryString.isEmpty()) {
            fullUrl = uri + "?" + queryString;
        }

        // 打印请求开始日志
        printRequestLog(method, fullUrl, ip);

        try {
            // 执行过滤器链
            chain.doFilter(request, response);
        } finally {
            // 计算请求处理时长
            Long requestStartTime = (Long) httpRequest.getAttribute(REQUEST_START_TIME);
            if (requestStartTime != null) {
                long duration = System.currentTimeMillis() - requestStartTime;
                // 打印请求结束日志
                printResponseLog(method, uri, httpResponse.getStatus(), duration);
            }
        }
    }

    /**
     * 打印请求日志
     * @param method 请求方法
     * @param url 请求URL
     * @param ip 客户端IP
     */
    private void printRequestLog(String method, String url, String ip) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("【请求开始】\n");
        sb.append("时间: ").append(java.time.LocalDateTime.now()).append("\n");
        sb.append("方法: ").append(method).append("\n");
        sb.append("URL: ").append(url).append("\n");
        sb.append("IP: ").append(ip).append("\n");
        sb.append("========================================\n");
        System.out.println(sb.toString());
    }

    /**
     * 打印响应日志
     * @param method 请求方法
     * @param uri 请求URI
     * @param status 响应状态码
     * @param duration 处理时长
     */
    private void printResponseLog(String method, String uri, int status, long duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("【请求结束】\n");
        sb.append("时间: ").append(java.time.LocalDateTime.now()).append("\n");
        sb.append("方法: ").append(method).append("\n");
        sb.append("URI: ").append(uri).append("\n");
        sb.append("状态: ").append(status).append("\n");
        sb.append("时长: ").append(duration).append("ms\n");
        sb.append("========================================\n");
        System.out.println(sb.toString());
    }

    /**
     * 获取客户端真实IP地址
     * @param request HTTP请求对象
     * @return IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        // 优先从代理头获取
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
        // 如果获取到多个IP，取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    /**
     * 销毁过滤器
     */
    @Override
    public void destroy() {
        // 过滤器销毁逻辑
        System.out.println("LogFilter destroyed");
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
