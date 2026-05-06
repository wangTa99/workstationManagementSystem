/**
 * 发票相关API
 */
import request from './request'

/**
 * 获取发票列表
 * @param {Object} params - 查询参数
 * @returns {Promise}
 */
export function getInvoiceList(params) {
  return request.get('/api/invoice/list', { params })
}

/**
 * 获取发票详情
 * @param {string|number} id - 发票ID
 * @returns {Promise}
 */
export function getInvoiceDetail(id) {
  return request.get(`/api/invoice/detail/${id}`)
}

/**
 * 申请发票
 * @param {Object} data - 发票数据
 * @returns {Promise}
 */
export function applyInvoice(data) {
  return request.post('/api/invoice/apply', data)
}

/**
 * 删除发票
 * @param {string|number} id - 发票ID
 * @returns {Promise}
 */
export function deleteInvoice(id) {
  return request.delete(`/api/invoice/${id}`)
}
