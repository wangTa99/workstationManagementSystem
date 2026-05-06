package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 日志Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 查询日志列表（分页）
     * @param userId 用户ID
     * @param module 模块
     * @param operationType 操作类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 日志列表
     */
    List<Log> selectLogList(@Param("userId") Long userId,
                              @Param("module") String module,
                              @Param("operationType") Integer operationType,
                              @Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              @Param("startIndex") Long startIndex,
                              @Param("pageSize") Integer pageSize);

    /**
     * 查询日志总数
     * @param userId 用户ID
     * @param module 模块
     * @param operationType 操作类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 日志总数
     */
    Long selectLogCount(@Param("userId") Long userId,
                         @Param("module") String module,
                         @Param("operationType") Integer operationType,
                         @Param("startDate") String startDate,
                         @Param("endDate") String endDate);

    /**
     * 根据用户ID查询最近的日志
     * @param userId 用户ID
     * @param limit 查询数量
     * @return 日志列表
     */
    List<Log> selectRecentByUserId(@Param("userId") Long userId, @Param("limit") Integer limit);

    /**
     * 清理指定日期之前的日志
     * @param beforeDate 清理日期
     * @return 影响行数
     */
    Integer deleteBeforeDate(@Param("beforeDate") LocalDateTime beforeDate);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
