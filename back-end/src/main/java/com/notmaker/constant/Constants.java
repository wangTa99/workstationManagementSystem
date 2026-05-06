package com.notmaker.constant;

/**
 * 系统常量类
 * 定义系统中使用的各种常量值
 */
public class Constants {

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    public static final Integer ERROR_CODE = 500;

    /**
     * 参数错误状态码
     */
    public static final Integer PARAM_ERROR_CODE = 400;

    /**
     * 未授权状态码
     */
    public static final Integer UNAUTHORIZED_CODE = 401;

    /**
     * 禁止访问状态码
     */
    public static final Integer FORBIDDEN_CODE = 403;

    /**
     * 未找到资源状态码
     */
    public static final Integer NOT_FOUND_CODE = 404;

    /**
     * 成功消息
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 失败消息
     */
    public static final String ERROR_MESSAGE = "操作失败";

    /**
     * 参数错误消息
     */
    public static final String PARAM_ERROR_MESSAGE = "参数错误";

    /**
     * 未授权消息
     */
    public static final String UNAUTHORIZED_MESSAGE = "未授权访问";

    /**
     * 禁止访问消息
     */
    public static final String FORBIDDEN_MESSAGE = "禁止访问";

    /**
     * 默认分页页码
     */
    public static final Integer DEFAULT_PAGE = 1;

    /**
     * 默认每页条数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 最大分页条数
     */
    public static final Integer MAX_PAGE_SIZE = 100;

    /**
     * JWT令牌前缀
     */
    public static final String JWT_PREFIX = "Bearer ";

    /**
     * JWT令牌密钥
     */
    public static final String JWT_SECRET = "NotMakerSecretKey2024";

    /**
     * JWT令牌过期时间（毫秒）
     */
    public static final Long JWT_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    /**
     * 超级管理员角色编码
     */
    public static final String SUPER_ADMIN = "SUPER_ADMIN";

    /**
     * 普通管理员角色编码
     */
    public static final String ADMIN = "ADMIN";

    /**
     * 普通用户角色编码
     */
    public static final String USER = "USER";

    /**
     * 验证码前缀
     */
    public static final String CAPTCHA_PREFIX = "captcha:";

    /**
     * 验证码过期时间（秒）
     */
    public static final Integer CAPTCHA_EXPIRE_TIME = 300;

    /**
     * 默认头像URL
     */
    public static final String DEFAULT_AVATAR = "/uploads/default/avatar.png";

    /**
     * 文件上传路径
     */
    public static final String UPLOAD_PATH = "/uploads/";

    /**
     * 图片文件类型
     */
    public static final String IMAGE_TYPE = "image";

    /**
     * 视频文件类型
     */
    public static final String VIDEO_TYPE = "video";

    /**
     * 音频文件类型
     */
    public static final String AUDIO_TYPE = "audio";

    /**
     * 文档文件类型
     */
    public static final String DOC_TYPE = "doc";
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
