package com.notmaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置类
 * 配置静态资源访问路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 文件上传根路径
     */
    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * 配置静态资源映射
     * 将/uploads/**路径映射到文件上传目录
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置文件上传目录的静态资源映射
        // 访问路径：http://localhost:18007/uploads/2024/01/01/xxx.jpg
        // 实际路径：D:/uploads/2024/01/01/xxx.jpg
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath);
    }
}

