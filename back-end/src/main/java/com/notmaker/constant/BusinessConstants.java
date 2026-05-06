package com.notmaker.constant;

/**
 * 业务常量类
 * 定义各业务模块使用的常量
 */
public class BusinessConstants {

    // ==================== 用户模块 ====================

    /**
     * 默认用户状态：启用
     */
    public static final Integer USER_STATUS_ENABLE = 1;

    /**
     * 默认用户状态：禁用
     */
    public static final Integer USER_STATUS_DISABLE = 0;

    /**
     * 用户默认积分
     */
    public static final Integer USER_DEFAULT_SCORE = 0;

    /**
     * 用户默认等级
     */
    public static final Integer USER_DEFAULT_LEVEL = 1;

    /**
     * 用户头像最大大小（字节）
     */
    public static final Long USER_AVATAR_MAX_SIZE = 2 * 1024 * 1024L;

    // ==================== 商品模块 ====================

    /**
     * 商品状态：上架
     */
    public static final Integer PRODUCT_STATUS_ON = 1;

    /**
     * 商品状态：下架
     */
    public static final Integer PRODUCT_STATUS_OFF = 0;

    /**
     * 商品状态：售罄
     */
    public static final Integer PRODUCT_STATUS_SOLD_OUT = 2;

    /**
     * 商品推荐：是
     */
    public static final Integer PRODUCT_RECOMMEND_YES = 1;

    /**
     * 商品推荐：否
     */
    public static final Integer PRODUCT_RECOMMEND_NO = 0;

    /**
     * 商品热卖：是
     */
    public static final Integer PRODUCT_HOT_YES = 1;

    /**
     * 商品热卖：否
     */
    public static final Integer PRODUCT_HOT_NO = 0;

    /**
     * 商品主图最大数量
     */
    public static final Integer PRODUCT_IMAGE_MAX_COUNT = 5;

    // ==================== 订单模块 ====================

    /**
     * 订单来源：PC网页
     */
    public static final Integer ORDER_SOURCE_PC = 1;

    /**
     * 订单来源：手机网页
     */
    public static final Integer ORDER_SOURCE_H5 = 2;

    /**
     * 订单来源：Android App
     */
    public static final Integer ORDER_SOURCE_ANDROID = 3;

    /**
     * 订单来源：iOS App
     */
    public static final Integer ORDER_SOURCE_IOS = 4;

    /**
     * 订单来源：微信小程序
     */
    public static final Integer ORDER_SOURCE_WECHAT = 5;

    /**
     * 支付方式：微信支付
     */
    public static final Integer PAY_TYPE_WECHAT = 1;

    /**
     * 支付方式：支付宝
     */
    public static final Integer PAY_TYPE_ALIPAY = 2;

    /**
     * 支付方式：银行卡
     */
    public static final Integer PAY_TYPE_BANK = 3;

    /**
     * 支付方式：余额
     */
    public static final Integer PAY_TYPE_BALANCE = 4;

    // ==================== 优惠券模块 ====================

    /**
     * 优惠券类型：满减券
     */
    public static final Integer COUPON_TYPE_FULL_REDUCE = 1;

    /**
     * 优惠券类型：折扣券
     */
    public static final Integer COUPON_TYPE_DISCOUNT = 2;

    /**
     * 优惠券类型：无门槛券
     */
    public static final Integer COUPON_TYPE_NO_THRESHOLD = 3;

    /**
     * 优惠券适用范围：全部商品
     */
    public static final Integer COUPON_SCOPE_ALL = 1;

    /**
     * 优惠券适用范围：指定分类
     */
    public static final Integer COUPON_SCOPE_CATEGORY = 2;

    /**
     * 优惠券适用范围：指定商品
     */
    public static final Integer COUPON_SCOPE_PRODUCT = 3;

    // ==================== 积分模块 ====================

    /**
     * 积分变动类型：订单奖励
     */
    public static final Integer POINTS_TYPE_ORDER_REWARD = 1;

    /**
     * 积分变动类型：签到奖励
     */
    public static final Integer POINTS_TYPE_SIGN_REWARD = 2;

    /**
     * 积分变动类型：活动奖励
     */
    public static final Integer POINTS_TYPE_ACTIVITY_REWARD = 3;

    /**
     * 积分变动类型：兑换商品
     */
    public static final Integer POINTS_TYPE_EXCHANGE = 4;

    /**
     * 积分变动类型：退款扣减
     */
    public static final Integer POINTS_TYPE_REFUND = 5;

    /**
     * 签到基础积分
     */
    public static final Integer SIGN_BASE_POINTS = 10;

    /**
     * 连续签到额外奖励积分数
     */
    public static final Integer SIGN_CONTINUOUS_EXTRA_POINTS = 5;

    /**
     * 最大连续签到天数
     */
    public static final Integer SIGN_MAX_CONTINUOUS_DAYS = 7;

    // ==================== 消息模块 ====================

    /**
     * 消息类型：系统消息
     */
    public static final Integer MESSAGE_TYPE_SYSTEM = 1;

    /**
     * 消息类型：用户消息
     */
    public static final Integer MESSAGE_TYPE_USER = 2;

    /**
     * 消息类型：订单消息
     */
    public static final Integer MESSAGE_TYPE_ORDER = 3;

    /**
     * 消息类型：活动消息
     */
    public static final Integer MESSAGE_TYPE_ACTIVITY = 4;

    // ==================== 评论模块 ====================

    /**
     * 评论审核状态：待审核
     */
    public static final Integer COMMENT_AUDIT_PENDING = 0;

    /**
     * 评论审核状态：已通过
     */
    public static final Integer COMMENT_AUDIT_PASSED = 1;

    /**
     * 评论审核状态：已拒绝
     */
    public static final Integer COMMENT_AUDIT_REJECTED = 2;

    /**
     * 评论显示：是
     */
    public static final Integer COMMENT_SHOW_YES = 1;

    /**
     * 评论显示：否
     */
    public static final Integer COMMENT_SHOW_NO = 0;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
