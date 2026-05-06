package com.notmaker.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 地址DTO
 * 用于接收地址相关请求的参数
 */
@Data
public class AddressDTO implements Serializable {

    /**
     * 地址ID（更新时需要）
     */
    private Long id;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人手机号
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
     * 邮编
     */
    private String zipCode;

    /**
     * 是否为默认地址：0-否，1-是
     */
    private Integer isDefault;

    /**
     * 地址标签：1-家，2-公司，3-学校
     */
    private Integer addressTag;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
