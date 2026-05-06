package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 数据字典Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    /**
     * 根据字典编码查询字典项列表
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<Dictionary> selectByDictCode(@Param("dictCode") String dictCode);

    /**
     * 根据父ID查询子字典项
     * @param parentId 父字典ID
     * @return 子字典项列表
     */
    List<Dictionary> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 根据字典编码和键名查询字典值
     * @param dictCode 字典编码
     * @param dictKey 字典键
     * @return 字典值
     */
    String selectValueByKey(@Param("dictCode") String dictCode, @Param("dictKey") String dictKey);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
