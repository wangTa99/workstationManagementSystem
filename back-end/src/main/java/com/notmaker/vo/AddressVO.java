package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址视图对象
 * 用于返回收货地址信息的视图
 */
@Data
public class AddressVO implements Serializable {

    /**
     * 地址ID
     */
    private Long id;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人电话
     */
    private String consigneePhone;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 完整地址
     */
    private String fullAddress;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 地址标签
     */
    private Integer addressTag;

    /**
     * 地址标签描述
     */
    private String addressTagDesc;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
