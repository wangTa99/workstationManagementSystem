import request from '@/utils/request'

/**
 * 获取分类树
 */
export function getCategoryTree() {
  return request.get('/category/tree')
}

/**
 * 获取顶级分类列表
 */
export function getTopLevelCategories() {
  return request.get('/category/topLevel')
}

/**
 * 获取子分类列表
 */
export function getChildCategories(parentId) {
  return request.get(`/category/children/${parentId}`)
}

/**
 * 获取分类详情
 */
export function getCategoryDetail(id) {
  return request.get(`/category/${id}`)
}

/**
 * 获取分类列表
 */
export function getCategoryList(params) {
  return request.get('/category/list', params)
}
