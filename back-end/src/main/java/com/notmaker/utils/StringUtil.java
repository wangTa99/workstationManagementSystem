package com.notmaker.utils;

import java.util.UUID;

/**
 * 字符串工具类
 * 提供字符串处理的常用方法
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     * @param str 字符串
     * @return 是否不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空或只包含空白字符
     * @param str 字符串
     * @return 是否为空或空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空且不只包含空白字符
     * @param str 字符串
     * @return 是否不为空且不只包含空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 字符串脱敏处理
     * @param str 字符串
     * @param startLen 开头保留长度
     * @param endLen 结尾保留长度
     * @return 脱敏后的字符串
     */
    public static String mask(String str, int startLen, int endLen) {
        if (isEmpty(str)) {
            return str;
        }
        int len = str.length();
        if (len <= startLen + endLen) {
            return str;
        }
        String start = str.substring(0, startLen);
        String end = str.substring(len - endLen);
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < len - startLen - endLen; i++) {
            mask.append("*");
        }
        return start + mask + end;
    }

    /**
     * 手机号脱敏
     * @param phone 手机号
     * @return 脱敏后的手机号
     */
    public static String maskPhone(String phone) {
        return mask(phone, 3, 4);
    }

    /**
     * 邮箱脱敏
     * @param email 邮箱
     * @return 脱敏后的邮箱
     */
    public static String maskEmail(String email) {
        if (isEmpty(email)) {
            return email;
        }
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) {
            return email;
        }
        return mask(email.substring(0, atIndex), 1, 1) + email.substring(atIndex);
    }

    /**
     * 生成UUID字符串
     * @return UUID字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成带前缀的UUID字符串
     * @param prefix 前缀
     * @return 带前缀的UUID字符串
     */
    public static String generateUUIDWithPrefix(String prefix) {
        return prefix + generateUUID();
    }

    /**
     * 左填充字符串到指定长度
     * @param str 原始字符串
     * @param length 目标长度
     * @param padChar 填充字符
     * @return 填充后的字符串
     */
    public static String leftPad(String str, int length, char padChar) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 右填充字符串到指定长度
     * @param str 原始字符串
     * @param length 目标长度
     * @param padChar 填充字符
     * @return 填充后的字符串
     */
    public static String rightPad(String str, int length, char padChar) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    /**
     * 截断字符串到指定长度
     * @param str 原始字符串
     * @param length 目标长度
     * @param suffix 后缀
     * @return 截断后的字符串
     */
    public static String truncate(String str, int length, String suffix) {
        if (str == null) {
            return null;
        }
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length) + suffix;
    }

    /**
     * 将驼峰命名转换为下划线命名
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String camelToUnderscore(String camelCase) {
        if (isEmpty(camelCase)) {
            return camelCase;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append("_");
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将下划线命名转换为驼峰命名
     * @param underscore 下划线命名字符串
     * @return 驼峰命名字符串
     */
    public static String underscoreToCamel(String underscore) {
        if (isEmpty(underscore)) {
            return underscore;
        }
        StringBuilder sb = new StringBuilder();
        boolean nextUpper = false;
        for (int i = 0; i < underscore.length(); i++) {
            char c = underscore.charAt(i);
            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    sb.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
