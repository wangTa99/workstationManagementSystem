/**
 * 退款相关API
 */
import request from './request'

/**
 * 获取退款列表
 * @param {Object} params - 查询参数
 * @returns {Promise}
 */
export function getRefundList(params) {
  return request.get('/api/refund/list', { params })
}

/**
 * 获取退款详情
 * @param {string|number} id - 退款ID
 * @returns {Promise}
 */
export function getRefundDetail(id) {
  return request.get(`/api/refund/detail/${id}`)
}

/**
 * 申请退款
 * @param {Object} data - 退款数据
 * @returns {Promise}
 */
export function applyRefund(data) {
  return request.post('/api/refund/apply', data)
}

/**
 * 取消退款
 * @param {string|number} id - 退款ID
 * @returns {Promise}
 */
export function cancelRefund(id) {
  return request.post(`/api/refund/cancel/${id}`)
}

/**
 * 获取退款进度
 * @param {string|number} id - 退款ID
 * @returns {Promise}
 */
export function getRefundProgress(id) {
  return request.get(`/api/refund/progress/${id}`)
}
