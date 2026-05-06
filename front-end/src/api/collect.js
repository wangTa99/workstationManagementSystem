import request from '@/utils/request'

/**
 * 获取收藏列表
 */
export function getCollectList(params) {
  return request.get('/collect/list', params)
}

/**
 * 添加收藏
 */
export function addCollect(objectId, collectType) {
  return request.post('/collect', { objectId, collectType })
}

/**
 * 取消收藏
 */
export function cancelCollect(objectId) {
  return request.delete('/collect', { objectId })
}

/**
 * 检查是否已收藏
 */
export function checkCollect(objectId) {
  return request.get('/collect/check', { objectId })
}
