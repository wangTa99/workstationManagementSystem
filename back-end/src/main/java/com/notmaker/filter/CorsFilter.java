package com.notmaker.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS过滤器
 * 用于处理跨域资源共享（CORS）请求
 */
@Component
public class CorsFilter implements Filter {

    /**
     * 允许的来源列表
     */
    private static final String ALLOWED_ORIGINS = "*";

    /**
     * 允许的方法列表
     */
    private static final String ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS,PATCH";

    /**
     * 允许的头部列表
     */
    private static final String ALLOWED_HEADERS = "Origin,Content-Type,Accept,Authorization,Token,X-Requested-With";

    /**
     * 暴露的头部列表
     */
    private static final String EXPOSED_HEADERS = "Content-Disposition";

    /**
     * 是否允许携带凭据
     */
    private static final String ALLOW_CREDENTIALS = "true";

    /**
     * 预检请求缓存时间
     */
    private static final String MAX_AGE = "3600";

    /**
     * 初始化过滤器
     * @param filterConfig 过滤器配置
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化逻辑
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

        // 设置CORS响应头
        setCorsHeaders(httpRequest, httpResponse);

        // 如果是OPTIONS预检请求，直接返回
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 继续执行过滤器链
        chain.doFilter(request, response);
    }

    /**
     * 设置CORS响应头
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void setCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求来源
        String origin = request.getHeader("Origin");

        // 设置允许的来源
        if (origin != null && !origin.isEmpty()) {
            // 生产环境中应该验证origin，这里简化处理
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGINS);
        }

        // 设置允许的方法
        response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);

        // 设置允许的头部
        response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);

        // 设置暴露的头部
        response.setHeader("Access-Control-Expose-Headers", EXPOSED_HEADERS);

        // 设置是否允许携带凭据
        response.setHeader("Access-Control-Allow-Credentials", ALLOW_CREDENTIALS);

        // 设置预检请求缓存时间
        response.setHeader("Access-Control-Max-Age", MAX_AGE);

        // 设置内容类型
        response.setContentType("application/json;charset=UTF-8");
    }

    /**
     * 销毁过滤器
     */
    @Override
    public void destroy() {
        // 过滤器销毁逻辑
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
