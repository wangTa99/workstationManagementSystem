package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    /**
     * 根据配置键名查询配置值
     * @param configKey 配置键名
     * @return 配置值
     */
    String selectValueByKey(@Param("configKey") String configKey);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
