import request from '@/utils/request'

/**
 * 获取用户信息
 */
export function getUserInfo() {
  return request.get('/user/info')
}

/**
 * 获取用户列表
 */
export function getUserList(params) {
  return request.get('/user/list', params)
}

/**
 * 根据ID获取用户信息
 */
export function getUserById(id) {
  return request.get(`/user/${id}`)
}

/**
 * 更新用户信息
 */
export function updateUser(data) {
  return request.put('/user/update', data)
}

/**
 * 修改密码
 */
export function changePassword(data) {
  return request.post('/user/changePassword', data)
}

/**
 * 退出登录
 */
export function logout() {
  return request.post('/user/logout')
}

/**
 * 发送注册验证码
 */
export function sendRegisterCaptcha(phone) {
  return request.get(`/user/sendRegisterCaptcha/${phone}`)
}

/**
 * 发送登录验证码
 */
export function sendLoginCaptcha(phone) {
  return request.get(`/user/sendLoginCaptcha/${phone}`)
}
