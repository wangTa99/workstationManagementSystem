package com.notmaker.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 分类视图对象
 * 用于返回商品分类信息的视图
 */
@Data
public class CategoryVO implements Serializable {

    /**
     * 分类ID
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 分类层级
     */
    private Integer level;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 子分类列表
     */
    private List<CategoryVO> children;

    /**
     * 商品数量
     */
    private Integer productCount;
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
