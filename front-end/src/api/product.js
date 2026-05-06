import request from '@/utils/request'

/**
 * 获取商品列表
 */
export function getProductList(params) {
  return request.get('/product/list', params)
}

/**
 * 获取商品详情
 */
export function getProductDetail(id) {
  return request.get(`/product/${id}`)
}

/**
 * 获取推荐商品
 */
export function getRecommendProducts(params) {
  return request.get('/product/recommend', params)
}

/**
 * 获取热卖商品
 */
export function getHotProducts(params) {
  return request.get('/product/hot', params)
}

/**
 * 新增商品
 */
export function addProduct(data) {
  return request.post('/product', data)
}

/**
 * 更新商品
 */
export function updateProduct(data) {
  return request.put('/product', data)
}

/**
 * 删除商品
 */
export function deleteProduct(id) {
  return request.delete(`/product/${id}`)
}

/**
 * 更新商品状态
 */
export function updateProductStatus(id, status) {
  return request.put(`/product/${id}/status`, { status })
}
