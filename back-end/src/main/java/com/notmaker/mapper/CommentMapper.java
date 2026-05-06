package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评论Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据商品ID查询评论列表（分页）
     * @param productId 商品ID
     * @param rating 评分
     * @param hasImage 是否有图
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 评论列表
     */
    List<Comment> selectByProductId(@Param("productId") Long productId,
                                    @Param("rating") Integer rating,
                                    @Param("hasImage") Integer hasImage,
                                    @Param("startIndex") Long startIndex,
                                    @Param("pageSize") Integer pageSize);

    /**
     * 根据商品ID查询评论总数
     * @param productId 商品ID
     * @param rating 评分
     * @param hasImage 是否有图
     * @return 评论总数
     */
    Long selectCountByProductId(@Param("productId") Long productId,
                                 @Param("rating") Integer rating,
                                 @Param("hasImage") Integer hasImage);

    /**
     * 根据订单ID查询评论
     * @param orderId 订单ID
     * @return 评论信息
     */
    Comment selectByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据商品ID查询好评数量
     * @param productId 商品ID
     * @param minRating 最低评分
     * @return 好评数量
     */
    Long selectGoodCountByProductId(@Param("productId") Long productId, @Param("minRating") Integer minRating);

    /**
     * 查询评论列表（分页）
     * @param keyword 关键词
     * @param productId 商品ID
     * @param userId 用户ID
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 评论列表
     */
    List<Comment> selectCommentList(@Param("keyword") String keyword,
                                     @Param("productId") Long productId,
                                     @Param("userId") Long userId,
                                     @Param("startIndex") Long startIndex,
                                     @Param("pageSize") Integer pageSize);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
