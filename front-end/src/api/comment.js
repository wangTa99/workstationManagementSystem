import request from '@/utils/request'

/**
 * 获取商品评论列表
 */
export function getProductComments(params) {
  return request.get('/comment/product', params)
}

/**
 * 获取我的评论列表
 */
export function getMyCommentList(params) {
  return request.get('/comment/my', params)
}

/**
 * 添加评论
 */
export function addComment(data) {
  return request.post('/comment', data)
}

/**
 * 删除评论
 */
export function deleteComment(id) {
  return request.delete(`/comment/${id}`)
}

/**
 * 点赞评论
 */
export function likeComment(id) {
  return request.post(`/comment/${id}/like`)
}
