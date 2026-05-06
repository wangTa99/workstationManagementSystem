/**
 * 反馈相关API
 */
import request from './request'

/**
 * 提交反馈
 * @param {Object} data - 反馈数据
 * @returns {Promise}
 */
export function submitFeedback(data) {
  return request.post('/api/feedback/submit', data)
}

/**
 * 获取反馈列表
 * @param {Object} params - 查询参数
 * @returns {Promise}
 */
export function getFeedbackList(params) {
  return request.get('/api/feedback/list', { params })
}

/**
 * 获取反馈详情
 * @param {string|number} id - 反馈ID
 * @returns {Promise}
 */
export function getFeedbackDetail(id) {
  return request.get(`/api/feedback/detail/${id}`)
}

/**
 * 删除反馈
 * @param {string|number} id - 反馈ID
 * @returns {Promise}
 */
export function deleteFeedback(id) {
  return request.delete(`/api/feedback/${id}`)
}
