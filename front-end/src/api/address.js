import request from '@/utils/request'

/**
 * 获取收货地址列表
 */
export function getAddressList() {
  return request.get('/address/list')
}

/**
 * 获取默认收货地址
 */
export function getDefaultAddress() {
  return request.get('/address/default')
}

/**
 * 获取收货地址详情
 */
export function getAddressDetail(id) {
  return request.get(`/address/${id}`)
}

/**
 * 新增收货地址
 */
export function addAddress(data) {
  return request.post('/address', data)
}

/**
 * 更新收货地址
 */
export function updateAddress(data) {
  return request.put('/address', data)
}

/**
 * 删除收货地址
 */
export function deleteAddress(id) {
  return request.delete(`/address/${id}`)
}

/**
 * 设置默认收货地址
 */
export function setDefaultAddress(id) {
  return request.post(`/address/${id}/default`)
}
