import request from '@/utils/request'

/**
 * 获取轮播图列表
 */
export function getBannerList() {
  return request.get('/banner/list')
}

/**
 * 获取活动列表
 */
export function getActivityList() {
  return request.get('/activity/list')
}

/**
 * 获取秒杀商品列表
 */
export function getSeckillProducts() {
  return request.get('/seckill/products')
}

/**
 * 获取热门搜索列表
 */
export function getHotSearchList() {
  return request.get('/search/hot')
}

/**
 * 搜索商品
 */
export function search(params) {
  return request.get('/search/product', params)
}
