package com.notmaker.enums;

/**
 * 业务类型枚举
 * 定义系统中各种业务操作的类型
 */
public enum BusinessTypeEnum {

    /**
     * 登录
     */
    LOGIN(1, "登录"),

    /**
     * 登出
     */
    LOGOUT(2, "登出"),

    /**
     * 新增
     */
    INSERT(3, "新增"),

    /**
     * 修改
     */
    UPDATE(4, "修改"),

    /**
     * 删除
     */
    DELETE(5, "删除"),

    /**
     * 查询
     */
    SELECT(6, "查询"),

    /**
     * 导出
     */
    EXPORT(7, "导出"),

    /**
     * 导入
     */
    IMPORT(8, "导入"),

    /**
     * 上传
     */
    UPLOAD(9, "上传"),

    /**
     * 下载
     */
    DOWNLOAD(10, "下载"),

    /**
     * 审批
     */
    APPROVE(11, "审批"),

    /**
     * 提交
     */
    SUBMIT(12, "提交");

    /**
     * 业务类型码
     */
    private final Integer code;

    /**
     * 类型描述
     */
    private final String description;

    BusinessTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据类型码获取枚举
     * @param code 类型码
     * @return 对应的枚举值
     */
    public static BusinessTypeEnum getByCode(Integer code) {
        for (BusinessTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
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
