package com.notmaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 意见反馈实体类
 * 存储用户提交的意见反馈信息
 */
@Data
@TableName("tb_feedback")
public class Feedback implements Serializable {

    /**
     * 反馈ID，主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

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

    /**
     * 反馈状态：0-待处理，1-处理中，2-已处理，3-已关闭
     */
    private Integer status;

    /**
     * 处理备注
     */
    private String handleRemark;

    /**
     * 处理人ID
     */
    private Long handlerId;

    /**
     * 处理人名称
     */
    private String handlerName;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    private Integer deleted;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
