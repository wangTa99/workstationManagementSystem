package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 通知Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 查询通知列表（分页）
     * @param keyword 关键词
     * @param noticeType 通知类型
     * @param status 状态
     * @param isTop 是否置顶
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 通知列表
     */
    List<Notice> selectNoticeList(@Param("keyword") String keyword,
                                   @Param("noticeType") Integer noticeType,
                                   @Param("status") Integer status,
                                   @Param("isTop") Integer isTop,
                                   @Param("startIndex") Long startIndex,
                                   @Param("pageSize") Integer pageSize);

    /**
     * 查询通知总数
     * @param keyword 关键词
     * @param noticeType 通知类型
     * @param status 状态
     * @param isTop 是否置顶
     * @return 通知总数
     */
    Long selectNoticeCount(@Param("keyword") String keyword,
                            @Param("noticeType") Integer noticeType,
                            @Param("status") Integer status,
                            @Param("isTop") Integer isTop);

    /**
     * 查询已发布的通知列表
     * @param limit 查询数量
     * @return 已发布通知列表
     */
    List<Notice> selectPublishedList(@Param("limit") Integer limit);

    /**
     * 增加阅读量
     * @param id 通知ID
     * @return 影响行数
     */
    Integer incrementReadCount(@Param("id") Long id);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
