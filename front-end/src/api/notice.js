import request from '@/utils/request'

/**
 * 获取通知列表
 */
export function getNoticeList(params) {
  return request.get('/notice/list', params)
}

/**
 * 获取通知详情
 */
export function getNoticeDetail(id) {
  return request.get(`/notice/${id}`)
}

/**
 * 获取最新通知
 */
export function getLatestNotices(limit) {
  return request.get('/notice/latest', { limit })
}
