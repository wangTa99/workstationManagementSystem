package com.notmaker.utils;

import java.util.regex.Pattern;

/**
 * 数据校验工具类
 * 提供常用数据的格式校验方法
 */
public class ValidateUtil {

    /**
     * 手机号码正则表达式
     */
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    /**
     * 电子邮箱正则表达式
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 用户名正则表达式
     */
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{4,16}$";

    /**
     * 密码正则表达式
     */
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";

    /**
     * 身份证号码正则表达式
     */
    private static final String ID_CARD_REGEX = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";

    /**
     * URL正则表达式
     */
    private static final String URL_REGEX = "^https?://[a-zA-Z0-9\\-\\.]+(\\.[a-zA-Z]{2,})?(/[^\\s]*)?$";

    /**
     * IP地址正则表达式
     */
    private static final String IP_REGEX = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * 邮政编码正则表达式
     */
    private static final String ZIP_CODE_REGEX = "^\\d{6}$";

    /**
     * 验证码正则表达式
     */
    private static final String CAPTCHA_REGEX = "^\\d{4,6}$";

    /**
     * 金额正则表达式
     */
    private static final String AMOUNT_REGEX = "^([1-9]\\d*(\\.\\d{1,2})?|0\\.\\d{1,2})$";

    /**
     * 校验手机号码格式
     * @param phone 手机号码
     * @return 是否合法
     */
    public static boolean isPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return Pattern.matches(PHONE_REGEX, phone);
    }

    /**
     * 校验电子邮箱格式
     * @param email 电子邮箱
     * @return 是否合法
     */
    public static boolean isEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * 校验用户名格式
     * @param username 用户名
     * @return 是否合法
     */
    public static boolean isUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return Pattern.matches(USERNAME_REGEX, username);
    }

    /**
     * 校验密码格式
     * @param password 密码
     * @return 是否合法
     */
    public static boolean isPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    /**
     * 校验身份证号码格式
     * @param idCard 身份证号码
     * @return 是否合法
     */
    public static boolean isIdCard(String idCard) {
        if (idCard == null || idCard.isEmpty()) {
            return false;
        }
        return Pattern.matches(ID_CARD_REGEX, idCard);
    }

    /**
     * 校验URL格式
     * @param url URL
     * @return 是否合法
     */
    public static boolean isUrl(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        return Pattern.matches(URL_REGEX, url);
    }

    /**
     * 校验IP地址格式
     * @param ip IP地址
     * @return 是否合法
     */
    public static boolean isIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return Pattern.matches(IP_REGEX, ip);
    }

    /**
     * 校验邮政编码格式
     * @param zipCode 邮政编码
     * @return 是否合法
     */
    public static boolean isZipCode(String zipCode) {
        if (zipCode == null || zipCode.isEmpty()) {
            return false;
        }
        return Pattern.matches(ZIP_CODE_REGEX, zipCode);
    }

    /**
     * 校验验证码格式
     * @param captcha 验证码
     * @return 是否合法
     */
    public static boolean isCaptcha(String captcha) {
        if (captcha == null || captcha.isEmpty()) {
            return false;
        }
        return Pattern.matches(CAPTCHA_REGEX, captcha);
    }

    /**
     * 校验金额格式
     * @param amount 金额
     * @return 是否合法
     */
    public static boolean isAmount(String amount) {
        if (amount == null || amount.isEmpty()) {
            return false;
        }
        return Pattern.matches(AMOUNT_REGEX, amount);
    }

    /**
     * 校验对象是否为空
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 校验字符串是否为空
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 校验字符串长度是否在指定范围内
     * @param str 字符串
     * @param minLen 最小长度
     * @param maxLen 最大长度
     * @return 是否合法
     */
    public static boolean isLengthValid(String str, int minLen, int maxLen) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        return len >= minLen && len <= maxLen;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
