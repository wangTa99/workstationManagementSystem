package com.notmaker.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 * 提供日期格式化和解析的常用方法
 */
public class DateUtil {

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 标准日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 紧凑日期格式：yyyyMMdd
     */
    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";

    /**
     * 紧凑日期时间格式：yyyyMMddHHmmss
     */
    public static final String COMPACT_DATETIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 时间戳格式：yyyyMMddHHmmssSSS
     */
    public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 将Date转换为指定格式的字符串
     * @param date 日期对象
     * @param pattern 格式模式
     * @return 格式化后的日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将Date转换为标准日期字符串
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * 将Date转换为标准日期时间字符串
     * @param date 日期对象
     * @return 格式化后的日期时间字符串
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT);
    }

    /**
     * 将LocalDateTime转换为指定格式的字符串
     * @param localDateTime LocalDateTime对象
     * @param pattern 格式模式
     * @return 格式化后的日期时间字符串
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 将LocalDateTime转换为标准日期时间字符串
     * @param localDateTime LocalDateTime对象
     * @return 格式化后的日期时间字符串
     */
    public static String formatDateTime(LocalDateTime localDateTime) {
        return format(localDateTime, DATETIME_FORMAT);
    }

    /**
     * 将LocalDate转换为指定格式的字符串
     * @param localDate LocalDate对象
     * @param pattern 格式模式
     * @return 格式化后的日期字符串
     */
    public static String format(LocalDate localDate, String pattern) {
        if (localDate == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * 将LocalDate转换为标准日期字符串
     * @param localDate LocalDate对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(LocalDate localDate) {
        return format(localDate, DATE_FORMAT);
    }

    /**
     * 将字符串解析为Date
     * @param dateStr 日期字符串
     * @param pattern 格式模式
     * @return Date对象
     */
    public static Date parse(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串解析为LocalDateTime
     * @param dateStr 日期时间字符串
     * @param pattern 格式模式
     * @return LocalDateTime对象
     */
    public static LocalDateTime parseLocalDateTime(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串解析为LocalDate
     * @param dateStr 日期字符串
     * @param pattern 格式模式
     * @return LocalDate对象
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前日期字符串
     * @return 当前日期
     */
    public static String getCurrentDate() {
        return formatDate(new Date());
    }

    /**
     * 获取当前日期时间字符串
     * @return 当前日期时间
     */
    public static String getCurrentDateTime() {
        return formatDateTime(new Date());
    }

    /**
     * 获取当前时间戳字符串
     * @return 当前时间戳
     */
    public static String getCurrentTimestamp() {
        return format(new Date(), TIMESTAMP_FORMAT);
    }

    /**
     * 计算两个日期之间的天数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 天数差
     */
    public static long daysBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 计算两个LocalDate之间的天数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 天数差
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
