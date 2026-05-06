package com.notmaker.enums;

/**
 * 订单状态枚举
 * 定义订单的完整生命周期状态
 */
public enum OrderStatusEnum {

    /**
     * 待付款
     */
    PENDING_PAYMENT(0, "待付款"),

    /**
     * 待发货
     */
    PENDING_SHIPMENT(1, "待发货"),

    /**
     * 待收货
     */
    PENDING_RECEIPT(2, "待收货"),

    /**
     * 已完成
     */
    COMPLETED(3, "已完成"),

    /**
     * 已取消
     */
    CANCELLED(4, "已取消"),

    /**
     * 已退款
     */
    REFUNDED(5, "已退款");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String description;

    OrderStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据状态码获取枚举
     * @param code 状态码
     * @return 对应的枚举值
     */
    public static OrderStatusEnum getByCode(Integer code) {
        for (OrderStatusEnum status : values()) {
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
