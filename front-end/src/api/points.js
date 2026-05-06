/**
 * 积分相关API
 */
import request from './request'

/**
 * 获取积分余额
 * @returns {Promise}
 */
export function getPointsBalance() {
  return request.get('/api/points/balance')
}

/**
 * 获取积分记录
 * @param {Object} params - 查询参数
 * @returns {Promise}
 */
export function getPointsRecords(params) {
  return request.get('/api/points/records', { params })
}

/**
 * 签到获取积分
 * @returns {Promise}
 */
export function signIn() {
  return request.post('/api/points/sign')
}

/**
 * 获取签到状态
 * @returns {Promise}
 */
export function getSignStatus() {
  return request.get('/api/points/sign/status')
}

/**
 * 积分兑换优惠券
 * @param {Object} data - 兑换数据
 * @returns {Promise}
 */
export function exchangeCoupon(data) {
  return request.post('/api/points/exchange', data)
}
