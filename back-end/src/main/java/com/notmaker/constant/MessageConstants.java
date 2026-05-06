package com.notmaker.constant;

/**
 * 消息常量类
 * 定义系统中各种操作的成功和错误消息
 */
public class MessageConstants {

    // ==================== 通用消息 ====================

    /**
     * 操作成功
     */
    public static final String OPERATION_SUCCESS = "操作成功";

    /**
     * 操作失败
     */
    public static final String OPERATION_FAILED = "操作失败";

    /**
     * 保存成功
     */
    public static final String SAVE_SUCCESS = "保存成功";

    /**
     * 保存失败
     */
    public static final String SAVE_FAILED = "保存失败";

    /**
     * 更新成功
     */
    public static final String UPDATE_SUCCESS = "更新成功";

    /**
     * 更新失败
     */
    public static final String UPDATE_FAILED = "更新失败";

    /**
     * 删除成功
     */
    public static final String DELETE_SUCCESS = "删除成功";

    /**
     * 删除失败
     */
    public static final String DELETE_FAILED = "删除失败";

    /**
     * 查询成功
     */
    public static final String QUERY_SUCCESS = "查询成功";

    /**
     * 查询失败
     */
    public static final String QUERY_FAILED = "查询失败";

    /**
     * 操作过于频繁
     */
    public static final String OPERATION_TOO_FREQUENT = "操作过于频繁，请稍后再试";

    // ==================== 用户模块消息 ====================

    /**
     * 用户未登录
     */
    public static final String USER_NOT_LOGIN = "用户未登录";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "登录成功";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAILED = "登录失败，用户名或密码错误";

    /**
     * 账号已被禁用
     */
    public static final String ACCOUNT_DISABLED = "账号已被禁用，请联系管理员";

    /**
     * 账号不存在
     */
    public static final String ACCOUNT_NOT_EXIST = "账号不存在";

    /**
     * 密码错误
     */
    public static final String PASSWORD_ERROR = "密码错误";

    /**
     * 原密码错误
     */
    public static final String ORIGINAL_PASSWORD_ERROR = "原密码错误";

    /**
     * 两次密码输入不一致
     */
    public static final String PASSWORD_INCONSISTENT = "两次密码输入不一致";

    /**
     * 用户名已存在
     */
    public static final String USERNAME_EXIST = "用户名已存在";

    /**
     * 手机号已存在
     */
    public static final String PHONE_EXIST = "手机号已存在";

    /**
     * 邮箱已存在
     */
    public static final String EMAIL_EXIST = "邮箱已存在";

    /**
     * 注册成功
     */
    public static final String REGISTER_SUCCESS = "注册成功";

    /**
     * 注册失败
     */
    public static final String REGISTER_FAILED = "注册失败，请稍后再试";

    /**
     * 退出登录成功
     */
    public static final String LOGOUT_SUCCESS = "退出登录成功";

    // ==================== 商品模块消息 ====================

    /**
     * 商品不存在
     */
    public static final String PRODUCT_NOT_EXIST = "商品不存在";

    /**
     * 商品已下架
     */
    public static final String PRODUCT_OFF_SHELF = "商品已下架";

    /**
     * 商品库存不足
     */
    public static final String PRODUCT_STOCK_INSUFFICIENT = "商品库存不足";

    /**
     * 商品价格已变动
     */
    public static final String PRODUCT_PRICE_CHANGED = "商品价格已变动，请重新下单";

    // ==================== 订单模块消息 ====================

    /**
     * 订单不存在
     */
    public static final String ORDER_NOT_EXIST = "订单不存在";

    /**
     * 订单已取消
     */
    public static final String ORDER_CANCELLED = "订单已取消";

    /**
     * 订单已完成
     */
    public static final String ORDER_COMPLETED = "订单已完成";

    /**
     * 订单状态异常
     */
    public static final String ORDER_STATUS_ERROR = "订单状态异常";

    /**
     * 订单创建成功
     */
    public static final String ORDER_CREATE_SUCCESS = "订单创建成功";

    /**
     * 订单支付成功
     */
    public static final String ORDER_PAY_SUCCESS = "订单支付成功";

    /**
     * 订单支付失败
     */
    public static final String ORDER_PAY_FAILED = "订单支付失败";

    /**
     * 订单取消成功
     */
    public static final String ORDER_CANCEL_SUCCESS = "订单取消成功";

    // ==================== 支付模块消息 ====================

    /**
     * 支付方式不支持
     */
    public static final String PAYMENT_METHOD_NOT_SUPPORT = "支付方式不支持";

    /**
     * 支付签名验证失败
     */
    public static final String PAYMENT_SIGN_VERIFY_FAILED = "支付签名验证失败";

    /**
     * 支付超时
     */
    public static final String PAYMENT_TIMEOUT = "支付超时";

    // ==================== 优惠券模块消息 ====================

    /**
     * 优惠券不存在
     */
    public static final String COUPON_NOT_EXIST = "优惠券不存在";

    /**
     * 优惠券已过期
     */
    public static final String COUPON_EXPIRED = "优惠券已过期";

    /**
     * 优惠券已领完
     */
    public static final String COUPON_OUT_OF_STOCK = "优惠券已领完";

    /**
     * 优惠券已使用
     */
    public static final String COUPON_USED = "优惠券已使用";

    /**
     * 不满足优惠券使用条件
     */
    public static final String COUPON_NOT_APPLICABLE = "不满足优惠券使用条件";

    /**
     * 领取优惠券成功
     */
    public static final String COUPON_RECEIVE_SUCCESS = "领取成功";

    // ==================== 验证码模块消息 ====================

    /**
     * 验证码发送成功
     */
    public static final String CAPTCHA_SEND_SUCCESS = "验证码发送成功";

    /**
     * 验证码发送失败
     */
    public static final String CAPTCHA_SEND_FAILED = "验证码发送失败，请稍后再试";

    /**
     * 验证码错误
     */
    public static final String CAPTCHA_ERROR = "验证码错误";

    /**
     * 验证码过期
     */
    public static final String CAPTCHA_EXPIRED = "验证码已过期";

    /**
     * 验证码类型错误
     */
    public static final String CAPTCHA_TYPE_ERROR = "验证码类型错误";

    // ==================== 参数校验消息 ====================

    /**
     * 参数不能为空
     */
    public static final String PARAM_NOT_EMPTY = "参数不能为空";

    /**
     * 参数格式错误
     */
    public static final String PARAM_FORMAT_ERROR = "参数格式错误";

    /**
     * 参数超出范围
     */
    public static final String PARAM_OUT_OF_RANGE = "参数超出范围";

    /**
     * 手机号格式错误
     */
    public static final String PHONE_FORMAT_ERROR = "手机号格式错误";

    /**
     * 邮箱格式错误
     */
    public static final String EMAIL_FORMAT_ERROR = "邮箱格式错误";
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
