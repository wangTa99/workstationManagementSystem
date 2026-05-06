package com.notmaker.controller;

import com.notmaker.entity.Category;
import com.notmaker.service.CategoryService;
import com.notmaker.vo.CategoryVO;
import com.notmaker.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 分类控制器
 * 处理分类相关的HTTP请求
 */
@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CategoryController {

    /**
     * 分类服务
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类树
     * @return 分类树
     */
    @GetMapping("/tree")
    public Map<String, Object> getCategoryTree() {
        List<CategoryVO> tree = categoryService.getCategoryTree();
        return Map.of("code", 200, "message", "获取成功", "data", tree);
    }

    /**
     * 获取顶级分类列表
     * @return 顶级分类列表
     */
    @GetMapping("/topLevel")
    public Map<String, Object> getTopLevelCategories() {
        List<CategoryVO> categories = categoryService.getTopLevelCategories();
        return Map.of("code", 200, "message", "获取成功", "data", categories);
    }

    /**
     * 获取子分类列表
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    @GetMapping("/children/{parentId}")
    public Map<String, Object> getChildCategories(@PathVariable Long parentId) {
        List<CategoryVO> categories = categoryService.getChildCategories(parentId);
        return Map.of("code", 200, "message", "获取成功", "data", categories);
    }

    /**
     * 获取分类列表（分页）
     * @param keyword 关键词
     * @param parentId 父分类ID
     * @param status 状态
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 分类列表
     */
    @GetMapping("/list")
    public Map<String, Object> getCategoryList(@RequestParam(required = false) String keyword,
                                                @RequestParam(required = false) Long parentId,
                                                @RequestParam(required = false) Integer status,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageVo<CategoryVO> pageVo = categoryService.getCategoryList(keyword, parentId, status, pageNum, pageSize);
        return Map.of("code", 200, "message", "获取成功", "data", pageVo);
    }

    /**
     * 获取分类详情
     * @param categoryId 分类ID
     * @return 分类详情
     */
    @GetMapping("/{categoryId}")
    public Map<String, Object> getCategoryById(@PathVariable Long categoryId) {
        CategoryVO category = categoryService.getCategoryById(categoryId);
        return Map.of("code", 200, "message", "获取成功", "data", category);
    }

    /**
     * 新增分类
     * @param category 分类信息
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> addCategory(@RequestBody Category category) {
        Boolean result = categoryService.addCategory(category);
        return Map.of("code", 200, "message", "新增成功", "data", result);
    }

    /**
     * 更新分类
     * @param category 分类信息
     * @return 更新结果
     */
    @PutMapping
    public Map<String, Object> updateCategory(@RequestBody Category category) {
        Boolean result = categoryService.updateCategory(category);
        return Map.of("code", 200, "message", "更新成功", "data", result);
    }

    /**
     * 删除分类
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{categoryId}")
    public Map<String, Object> deleteCategory(@PathVariable Long categoryId) {
        Boolean result = categoryService.deleteCategory(categoryId);
        return Map.of("code", 200, "message", "删除成功", "data", result);
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
