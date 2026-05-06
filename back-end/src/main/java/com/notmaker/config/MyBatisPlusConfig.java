package com.notmaker.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus配置类
 * 配置分页插件等
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 配置MyBatis-Plus分页插件
     * 适用于MyBatis-Plus 3.3.x版本
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        
        // 设置数据库类型为MySQL
        paginationInterceptor.setDbType(DbType.MYSQL);
        
        // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求
        paginationInterceptor.setOverflow(false);
        
        // 设置最大单页限制数量，默认500条，-1不受限制
        paginationInterceptor.setLimit(500L);
        
        return paginationInterceptor;
    }
}

