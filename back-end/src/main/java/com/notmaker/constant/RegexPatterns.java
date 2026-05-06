package com.notmaker.constant;

/**
 * 正则表达式常量类
 * 定义系统中常用的正则表达式模式
 */
public class RegexPatterns {

    /**
     * 手机号码正则表达式
     * 匹配格式：1开头的11位数字
     */
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    /**
     * 电子邮箱正则表达式
     * 匹配常见的邮箱格式
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 用户名正则表达式
     * 4-16位字母、数字、下划线
     */
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{4,16}$";

    /**
     * 密码正则表达式
     * 6-20位，至少包含字母和数字
     */
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";

    /**
     * 身份证号码正则表达式
     * 15位或18位身份证
     */
    public static final String ID_CARD_REGEX = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";

    /**
     * URL正则表达式
     * 匹配http/https开头的URL
     */
    public static final String URL_REGEX = "^https?://[a-zA-Z0-9\\-\\.]+(\\.[a-zA-Z]{2,})?(/[^\\s]*)?$";

    /**
     * IP地址正则表达式
     * 匹配IPv4地址
     */
    public static final String IP_REGEX = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * 邮政编码正则表达式
     * 6位数字
     */
    public static final String ZIP_CODE_REGEX = "^\\d{6}$";

    /**
     * 银行卡号正则表达式
     * 16-19位数字
     */
    public static final String BANK_CARD_REGEX = "^\\d{16,19}$";

    /**
     * 验证码正则表达式
     * 4-6位数字
     */
    public static final String CAPTCHA_REGEX = "^\\d{4,6}$";

    /**
     * 中文姓名正则表达式
     * 2-20位中文
     */
    public static final String CHINESE_NAME_REGEX = "^[\\u4e00-\\u9fa5]{2,20}$";

    /**
     * 英文姓名正则表达式
     * 2-50位字母和空格
     */
    public static final String ENGLISH_NAME_REGEX = "^[a-zA-Z\\s]{2,50}$";

    /**
     * 金额正则表达式
     * 正数，最多两位小数
     */
    public static final String AMOUNT_REGEX = "^([1-9]\\d*(\\.\\d{1,2})?|0\\.\\d{1,2})$";

    /**
     * 整数正则表达式
     */
    public static final String INTEGER_REGEX = "^(-?[1-9]\\d*|0)$";

    /**
     * 正整数正则表达式
     */
    public static final String POSITIVE_INTEGER_REGEX = "^[1-9]\\d*$";

    /**
     * 负整数正则表达式
     */
    public static final String NEGATIVE_INTEGER_REGEX = "^-[1-9]\\d*$";

    /**
     * 折扣正则表达式
     * 0.1-9.9折
     */
    public static final String DISCOUNT_REGEX = "^([1-9](\\.\\d)?|9\\.9)$";

    /**
     * 百分比正则表达式
     * 0-100%
     */
    public static final String PERCENTAGE_REGEX = "^(\\d{1,2}(\\.\\d{1,2})?|100)$";

    /**
     * 日期格式正则表达式
     * yyyy-MM-dd
     */
    public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    /**
     * 时间格式正则表达式
     * HH:mm:ss
     */
    public static final String TIME_REGEX = "^\\d{2}:\\d{2}:\\d{2}$";

    /**
     * 日期时间格式正则表达式
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_REGEX = "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
