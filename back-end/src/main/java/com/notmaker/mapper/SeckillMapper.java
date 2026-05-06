package com.notmaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.notmaker.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 秒杀活动Mapper接口
 * 继承BaseMapper提供基本的CRUD操作
 */
@Mapper
public interface SeckillMapper extends BaseMapper<Seckill> {

    /**
     * 查询进行中的秒杀活动
     * @return 进行中的秒杀活动列表
     */
    List<Seckill> selectOngoingSeckills();

    /**
     * 查询即将开始的秒杀活动
     * @param limit 查询数量
     * @return 即将开始的秒杀活动列表
     */
    List<Seckill> selectUpcomingSeckills(@Param("limit") Integer limit);

    /**
     * 更新秒杀库存
     * @param seckillId 秒杀ID
     * @param stockChange 库存变化量
     * @return 影响行数
     */
    Integer updateSeckillStock(@Param("seckillId") Long seckillId, @Param("stockChange") Integer stockChange);

    /**
     * 更新已秒杀数量
     * @param seckillId 秒杀ID
     * @param count 数量
     * @return 影响行数
     */
    Integer updateSeckillCount(@Param("seckillId") Long seckillId, @Param("count") Integer count);
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
