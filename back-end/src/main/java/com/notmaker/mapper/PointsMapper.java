package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Points;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 积分记录Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface PointsMapper extends BaseMapper<Points> {

    /**
     * 根据用户ID查询积分记录列表
     * @param userId 用户ID
     * @param changeType 变动类型
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 积分记录列表
     */
    List<Points> selectByUserId(@Param("userId") Long userId,
                                 @Param("changeType") Integer changeType,
                                 @Param("startIndex") Long startIndex,
                                 @Param("pageSize") Integer pageSize);

    /**
     * 查询用户积分余额
     * @param userId 用户ID
     * @return 积分余额
     */
    Integer selectUserPointsBalance(@Param("userId") Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
