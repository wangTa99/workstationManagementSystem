package com.notmaker.enums;

/**
 * 通用状态枚举
 * 定义系统中常用的启用/禁用状态
 */
public enum StatusEnum {

    /**
     * 禁用状态
     */
    DISABLE(0, "禁用"),

    /**
     * 启用状态
     */
    ENABLE(1, "启用");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String description;

    StatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据状态码获取枚举
     * @param code 状态码
     * @return 对应的枚举值
     */
    public static StatusEnum getByCode(Integer code) {
        for (StatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
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
