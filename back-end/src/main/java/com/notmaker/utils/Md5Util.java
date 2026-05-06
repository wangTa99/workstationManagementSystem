package com.notmaker.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 提供MD5加密和验证的常用方法
 */
public class Md5Util {

    /**
     * 私有构造函数，防止实例化
     */
    private Md5Util() {
    }

    /**
     * 对字符串进行MD5加密
     * @param input 待加密的字符串
     * @return 加密后的十六进制字符串
     */
    public static String encrypt(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        try {
            // 获取MD5算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将字符串转换为字节数组
            byte[] inputBytes = input.getBytes();
            // 计算哈希值
            byte[] hashBytes = md.digest(inputBytes);
            // 将字节数组转换为十六进制字符串
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

    /**
     * 对字符串进行MD5加密并转换为大写
     * @param input 待加密的字符串
     * @return 加密后的大写十六进制字符串
     */
    public static String encryptUpperCase(String input) {
        return encrypt(input).toUpperCase();
    }

    /**
     * 对字符串进行多次MD5加密
     * @param input 待加密的字符串
     * @param times 加密次数
     * @return 多次加密后的十六进制字符串
     */
    public static String encryptMultiple(String input, int times) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String result = input;
        for (int i = 0; i < times; i++) {
            result = encrypt(result);
        }
        return result;
    }

    /**
     * 对字符串加盐后进行MD5加密
     * @param input 待加密的字符串
     * @param salt 盐值
     * @return 加盐加密后的十六进制字符串
     */
    public static String encryptWithSalt(String input, String salt) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        // 将盐值加在字符串两端
        String saltedInput = salt + input + salt;
        return encrypt(saltedInput);
    }

    /**
     * 验证字符串是否与MD5加密后的字符串匹配
     * @param input 待验证的字符串
     * @param encrypted 加密后的字符串
     * @return 是否匹配
     */
    public static boolean verify(String input, String encrypted) {
        if (input == null || encrypted == null) {
            return false;
        }
        String inputEncrypted = encrypt(input);
        return inputEncrypted.equalsIgnoreCase(encrypted);
    }

    /**
     * 验证加盐字符串是否匹配
     * @param input 待验证的字符串
     * @param salt 盐值
     * @param encrypted 加密后的字符串
     * @return 是否匹配
     */
    public static boolean verifyWithSalt(String input, String salt, String encrypted) {
        if (input == null || encrypted == null) {
            return false;
        }
        String inputEncrypted = encryptWithSalt(input, salt);
        return inputEncrypted.equalsIgnoreCase(encrypted);
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 生成带时间戳的MD5值
     * @param input 待加密的字符串
     * @param timestamp 时间戳
     * @return 加密后的字符串
     */
    public static String encryptWithTimestamp(String input, long timestamp) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return encrypt(input + timestamp);
    }

    /**
     * 生成带随机盐的MD5值
     * @param input 待加密的字符串
     * @return 加密后的字符串
     */
    public static String encryptWithRandomSalt(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        // 生成随机盐
        String randomSalt = String.valueOf(System.currentTimeMillis()) + String.valueOf((int) (Math.random() * 1000));
        return encryptWithSalt(input, randomSalt);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
