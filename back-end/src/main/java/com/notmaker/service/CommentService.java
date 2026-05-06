package com.notmaker.service;

import com.notmaker.dto.CommentCreateDTO;
import com.notmaker.dto.CommentQueryDTO;
import com.notmaker.vo.CommentVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 评论服务接口
 * 定义评论相关的业务操作
 */
public interface CommentService {

    /**
     * 分页查询商品评论列表
     * @param queryDTO 查询参数
     * @return 评论列表
     */
    PageVo<CommentVO> getProductCommentList(CommentQueryDTO queryDTO);

    /**
     * 获取用户评论列表
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 评论列表
     */
    PageVo<CommentVO> getUserCommentList(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     * @param userId 用户ID
     * @param createDTO 评论参数
     * @return 是否成功
     */
    Boolean addComment(Long userId, CommentCreateDTO createDTO);

    /**
     * 删除评论
     * @param commentId 评论ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean deleteComment(Long commentId, Long userId);

    /**
     * 商家回复评论
     * @param commentId 评论ID
     * @param replyContent 回复内容
     * @return 是否成功
     */
    Boolean replyComment(Long commentId, String replyContent);

    /**
     * 点赞评论
     * @param commentId 评论ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean likeComment(Long commentId, Long userId);

    /**
     * 取消点赞评论
     * @param commentId 评论ID
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean unlikeComment(Long commentId, Long userId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
