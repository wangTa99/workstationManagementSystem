package com.notmaker.controller;

import com.notmaker.dto.NoticeQueryDTO;
import com.notmaker.entity.Notice;
import com.notmaker.service.NoticeService;
import com.notmaker.vo.NoticeVO;
import com.notmaker.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 * 处理通知公告相关的HTTP请求
 */
@RestController
@RequestMapping("/api/notice")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class NoticeController {

    /**
     * 通知服务
     */
    @Autowired
    private NoticeService noticeService;

    /**
     * 获取通知列表
     * @param queryDTO 查询参数
     * @return 通知列表
     */
    @GetMapping("/list")
    public Map<String, Object> getNoticeList(NoticeQueryDTO queryDTO) {
        PageVo<NoticeVO> pageVo = noticeService.getNoticeList(queryDTO);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 获取通知详情
     * @param noticeId 通知ID
     * @return 通知详情
     */
    @GetMapping("/{noticeId}")
    public Map<String, Object> getNoticeDetail(@PathVariable Long noticeId) {
        NoticeVO notice = noticeService.getNoticeDetail(noticeId);
        return Map.of("code", 200, "message", "获取成功", "data", notice);
    }

    /**
     * 获取最新通知
     * @param limit 数量
     * @return 最新通知列表
     */
    @GetMapping("/latest")
    public Map<String, Object> getLatestNotices(@RequestParam(defaultValue = "5") Integer limit) {
        List<NoticeVO> notices = noticeService.getLatestNotices(limit);
        return Map.of("code", 200, "message", "获取成功", "data", notices);
    }

    /**
     * 新增通知
     * @param notice 通知信息
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> addNotice(@RequestBody Notice notice) {
        Boolean result = noticeService.addNotice(notice);
        return Map.of("code", 200, "message", "新增成功", "data", result);
    }

    /**
     * 更新通知
     * @param notice 通知信息
     * @return 更新结果
     */
    @PutMapping
    public Map<String, Object> updateNotice(@RequestBody Notice notice) {
        Boolean result = noticeService.updateNotice(notice);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除通知
     * @param noticeId 通知ID
     * @return 删除结果
     */
    @DeleteMapping("/{noticeId}")
    public Map<String, Object> deleteNotice(@PathVariable Long noticeId) {
        Boolean result = noticeService.deleteNotice(noticeId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }

    /**
     * 发布通知
     * @param noticeId 通知ID
     * @return 发布结果
     */
    @PostMapping("/{noticeId}/publish")
    public Map<String, Object> publishNotice(@PathVariable Long noticeId) {
        Boolean result = noticeService.publishNotice(noticeId);
        return Map.of("code", 200, "message", "发布成功", "data", result);
    }

    /**
     * 下架通知
     * @param noticeId 通知ID
     * @return 下架结果
     */
    @PostMapping("/{noticeId}/unpublish")
    public Map<String, Object> unpublishNotice(@PathVariable Long noticeId) {
        Boolean result = noticeService.unpublishNotice(noticeId);
        return Map.of("code", 200, "message", "下架成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
