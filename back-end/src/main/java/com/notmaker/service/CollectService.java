package com.notmaker.service;

import com.notmaker.vo.PageVo;
import java.util.List;

/**
 * 收藏服务接口
 * 定义用户收藏相关的业务操作
 */
public interface CollectService {

    /**
     * 获取用户收藏列表
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 收藏列表
     */
    PageVo<?> getCollectList(Long userId, Integer collectType, Integer pageNum, Integer pageSize);

    /**
     * 添加收藏
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @param objectId 对象ID
     * @return 是否成功
     */
    Boolean addCollect(Long userId, Integer collectType, Long objectId);

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @param objectId 对象ID
     * @return 是否成功
     */
    Boolean cancelCollect(Long userId, Integer collectType, Long objectId);

    /**
     * 检查是否已收藏
     * @param userId 用户ID
     * @param collectType 收藏类型
     * @param objectId 对象ID
     * @return 是否已收藏
     */
    Boolean isCollected(Long userId, Integer collectType, Long objectId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
