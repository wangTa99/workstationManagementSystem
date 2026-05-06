package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 收藏Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    /**
     * 根据用户ID查询收藏列表
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @return 收藏列表
     */
    List<Collect> selectByUserId(@Param("userId") Long userId, @Param("collectType") Integer collectType);

    /**
     * 根据用户ID和对象ID查询收藏
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @param objectId 对象ID
     * @return 收藏信息
     */
    Collect selectByUserIdAndObjectId(@Param("userId") Long userId,
                                       @Param("collectType") Integer collectType,
                                       @Param("objectId") Long objectId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
