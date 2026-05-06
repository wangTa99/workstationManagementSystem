package com.notmaker.enums;

/**
 * 性别枚举
 * 定义用户的性别选项
 */
public enum GenderEnum {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女");

    /**
     * 性别码
     */
    private final Integer code;

    /**
     * 性别描述
     */
    private final String description;

    GenderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据性别码获取枚举
     * @param code 性别码
     * @return 对应的枚举值
     */
    public static GenderEnum getByCode(Integer code) {
        for (GenderEnum gender : values()) {
            if (gender.getCode().equals(code)) {
                return gender;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
