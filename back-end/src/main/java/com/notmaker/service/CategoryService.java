package com.notmaker.service;

import com.notmaker.entity.Category;
import com.notmaker.vo.CategoryVO;
import com.notmaker.vo.PageVo;

import java.util.List;

/**
 * 分类服务接口
 * 定义分类相关的业务操作
 */
public interface CategoryService {

    /**
     * 获取分类树
     * @return 分类树列表
     */
    List<CategoryVO> getCategoryTree();

    /**
     * 获取顶级分类列表
     * @return 顶级分类列表
     */
    List<CategoryVO> getTopLevelCategories();

    /**
     * 获取子分类列表
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<CategoryVO> getChildCategories(Long parentId);

    /**
     * 分页查询分类列表
     * @param keyword 关键词
     * @param parentId 父分类ID
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageVo<CategoryVO> getCategoryList(String keyword, Long parentId, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取分类信息
     * @param categoryId 分类ID
     * @return 分类信息
     */
    CategoryVO getCategoryById(Long categoryId);

    /**
     * 新增分类
     * @param category 分类信息
     * @return 是否成功
     */
    Boolean addCategory(Category category);

    /**
     * 更新分类
     * @param category 分类信息
     * @return 是否成功
     */
    Boolean updateCategory(Category category);

    /**
     * 删除分类
     * @param categoryId 分类ID
     * @return 是否成功
     */
    Boolean deleteCategory(Long categoryId);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
