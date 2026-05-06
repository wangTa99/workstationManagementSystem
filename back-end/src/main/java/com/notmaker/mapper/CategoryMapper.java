package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 分类Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据分类名称查询分类
     * @param categoryName 分类名称
     * @return 分类信息
     */
    Category selectByCategoryName(@Param("categoryName") String categoryName);

    /**
     * 根据父ID查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<Category> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 查询所有启用的顶级分类
     * @return 顶级分类列表
     */
    List<Category> selectTopLevelCategories();

    /**
     * 查询分类树
     * @return 分类树列表
     */
    List<Category> selectCategoryTree();

    /**
     * 查询分类列表（分页）
     * @param keyword 关键词
     * @param parentId 父分类ID
     * @param status 状态
     * @param startIndex 起始索引
     * @param pageSize 每页条数
     * @return 分类列表
     */
    List<Category> selectCategoryList(@Param("keyword") String keyword,
                                     @Param("parentId") Long parentId,
                                     @Param("status") Integer status,
                                     @Param("startIndex") Long startIndex,
                                     @Param("pageSize") Integer pageSize);

    /**
     * 查询分类总数
     * @param keyword 关键词
     * @param parentId 父分类ID
     * @param status 状态
     * @return 分类总数
     */
    Long selectCategoryCount(@Param("keyword") String keyword,
                             @Param("parentId") Long parentId,
                             @Param("status") Integer status);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
