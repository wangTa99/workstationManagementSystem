package com.notmaker.service;

import com.notmaker.vo.PageVo;

/**
 * 日志服务接口
 * 定义系统日志相关的业务操作
 */
public interface LogService {

    /**
     * 分页查询日志列表
     * @param userId 用户ID
     * @param module 模块
     * @param operationType 操作类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 日志列表
     */
    PageVo<?> getLogList(Long userId, String module, Integer operationType,
                          String startDate, String endDate, Integer pageNum, Integer pageSize);

    /**
     * 获取用户最近的日志
     * @param userId 用户ID
     * @param limit 查询数量
     * @return 日志列表
     */
    java.util.List<?> getRecentLogs(Long userId, Integer limit);

    /**
     * 清理日志
     * @param days 保留天数
     * @return 清理数量
     */
    Integer cleanLogs(Integer days);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
