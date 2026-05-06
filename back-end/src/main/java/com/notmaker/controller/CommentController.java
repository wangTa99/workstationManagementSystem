package com.notmaker.controller;

import com.notmaker.dto.CommentCreateDTO;
import com.notmaker.dto.CommentQueryDTO;
import com.notmaker.service.CommentService;
import com.notmaker.vo.CommentVO;
import com.notmaker.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 评论控制器
 * 处理评论相关的HTTP请求
 */
@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CommentController {

    /**
     * 评论服务
     */
    @Autowired
    private CommentService commentService;

    /**
     * 获取商品评论列表
     * @param queryDTO 查询参数
     * @return 评论列表
     */
    @GetMapping("/list")
    public Map<String, Object> getProductCommentList(CommentQueryDTO queryDTO) {
        PageVo<CommentVO> pageVo = commentService.getProductCommentList(queryDTO);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 获取用户评论列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param request HTTP请求
     * @return 评论列表
     */
    @GetMapping("/my")
    public Map<String, Object> getUserCommentList(@RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PageVo<CommentVO> pageVo = commentService.getUserCommentList(userId, pageNum, pageSize);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 添加评论
     * @param createDTO 评论参数
     * @param request HTTP请求
     * @return 添加结果
     */
    @PostMapping
    public Map<String, Object> addComment(@RequestBody CommentCreateDTO createDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = commentService.addComment(userId, createDTO);
        return Map.of("code", 200, "message", "评论成功", "data", result);
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     * @param request HTTP请求
     * @return 删除结果
     */
    @DeleteMapping("/{commentId}")
    public Map<String, Object> deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = commentService.deleteComment(commentId, userId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }

    /**
     * 回复评论
     * @param commentId 评论ID
     * @param replyContent 回复内容
     * @return 回复结果
     */
    @PostMapping("/{commentId}/reply")
    public Map<String, Object> replyComment(@PathVariable Long commentId,
                                             @RequestParam String replyContent) {
        Boolean result = commentService.replyComment(commentId, replyContent);
        return Map.of("code", 200, "message", "回复成功", "data", result);
    }

    /**
     * 点赞评论
     * @param commentId 评论ID
     * @param request HTTP请求
     * @return 点赞结果
     */
    @PostMapping("/{commentId}/like")
    public Map<String, Object> likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Boolean result = commentService.likeComment(commentId, userId);
        return Map.of("code", 200, "message", "点赞成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
