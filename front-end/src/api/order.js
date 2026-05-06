import request from '@/utils/request'

/**
 * 获取订单列表
 */
export function getOrderList(params) {
  return request.get('/order/list', params)
}

/**
 * 获取订单详情
 */
export function getOrderDetail(id) {
  return request.get(`/order/${id}`)
}

/**
 * 创建订单
 */
export function createOrder(data) {
  return request.post('/order', data)
}

/**
 * 取消订单
 */
export function cancelOrder(id, reason) {
  return request.post(`/order/${id}/cancel`, { reason })
}

/**
 * 确认收货
 */
export function confirmReceipt(id) {
  return request.post(`/order/${id}/confirm`)
}

/**
 * 支付订单
 */
export function payOrder(id, payType) {
  return request.post(`/order/${id}/pay`, { payType })
}

/**
 * 删除订单
 */
export function deleteOrder(id) {
  return request.delete(`/order/${id}`)
}
