import request from '@/utils/request'

/**
 * 获取购物车列表
 */
export function getCartList() {
  return request.get('/cart/list')
}

/**
 * 获取购物车数量
 */
export function getCartCount() {
  return request.get('/cart/count')
}

/**
 * 添加到购物车
 */
export function addToCart(data) {
  return request.post('/cart', data)
}

/**
 * 更新购物车商品数量
 */
export function updateCartQuantity(cartId, quantity) {
  return request.put(`/cart/${cartId}/quantity`, { quantity })
}

/**
 * 更新购物车商品选中状态
 */
export function updateCartSelected(cartId, selected) {
  return request.put(`/cart/${cartId}/selected`, { selected })
}

/**
 * 删除购物车商品
 */
export function deleteCartItem(cartId) {
  return request.delete(`/cart/${cartId}`)
}

/**
 * 清空购物车
 */
export function clearCart() {
  return request.delete('/cart/clear')
}

/**
 * 全选/取消全选
 */
export function selectAllCart(selected) {
  return request.put('/cart/selectAll', { selected })
}
