import request from '@/utils/request'

/**
 * 获取我的优惠券列表
 */
export function getMyCouponList(params) {
  return request.get('/coupon/my', params)
}

/**
 * 获取可用优惠券列表
 */
export function getAvailableCouponList(params) {
  return request.get('/coupon/available', params)
}

/**
 * 领取优惠券
 */
export function receiveCoupon(couponId) {
  return request.post('/coupon/receive', { couponId })
}

/**
 * 计算优惠券优惠
 */
export function calculateDiscount(couponId, orderAmount) {
  return request.get('/coupon/calculate', { couponId, orderAmount })
}
