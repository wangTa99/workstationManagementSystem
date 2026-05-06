package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 意见反馈DTO
 * 用于接收用户提交意见反馈请求的参数
 */
@Data
public class FeedbackDTO implements Serializable {

    /**
     * 反馈类型：1-功能建议，2-bug反馈，3-体验问题，4-其他
     */
    private Integer feedbackType;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈图片，JSON数组格式
     */
    private String images;

    /**
     * 联系方式
     */
    private String contact;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
