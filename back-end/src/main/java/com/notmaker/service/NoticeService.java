package com.notmaker.service;

import com.notmaker.entity.Notice;
import com.notmaker.dto.NoticeQueryDTO;
import com.notmaker.vo.NoticeVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 通知服务接口
 * 定义通知公告相关的业务操作
 */
public interface NoticeService {

    /**
     * 分页查询通知列表
     * @param queryDTO 查询参数
     * @return 通知列表
     */
    PageVo<NoticeVO> getNoticeList(NoticeQueryDTO queryDTO);

    /**
     * 获取通知详情
     * @param noticeId 通知ID
     * @return 通知详情
     */
    NoticeVO getNoticeDetail(Long noticeId);

    /**
     * 获取最新通知列表
     * @param limit 查询数量
     * @return 通知列表
     */
    List<NoticeVO> getLatestNotices(Integer limit);

    /**
     * 新增通知
     * @param notice 通知信息
     * @return 是否成功
     */
    Boolean addNotice(Notice notice);

    /**
     * 更新通知
     * @param notice 通知信息
     * @return 是否成功
     */
    Boolean updateNotice(Notice notice);

    /**
     * 删除通知
     * @param noticeId 通知ID
     * @return 是否成功
     */
    Boolean deleteNotice(Long noticeId);

    /**
     * 发布通知
     * @param noticeId 通知ID
     * @return 是否成功
     */
    Boolean publishNotice(Long noticeId);

    /**
     * 下架通知
     * @param noticeId 通知ID
     * @return 是否成功
     */
    Boolean unpublishNotice(Long noticeId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
