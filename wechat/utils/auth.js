/**
 * 认证工具
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const app = getApp()

/**
 * 检查是否已登录
 * @returns {boolean}
 */
function isLogin() {
  return !!wx.getStorageSync('token')
}

/**
 * 跳转到登录页
 */
function goLogin() {
  wx.navigateTo({
    url: '/pages/user/login/main'
  })
}

/**
 * 验证登录状态，未登录则跳转
 * @returns {boolean}
 */
function checkLogin() {
  if (!isLogin()) {
    goLogin()
    return false
  }
  return true
}

/**
 * 获取登录凭证
 * @returns {Promise}
 */
function getLoginCode() {
  return new Promise((resolve, reject) => {
    wx.login({
      success: res => {
        if (res.code) {
          resolve(res.code)
        } else {
          reject(new Error('获取登录凭证失败'))
        }
      },
      fail: reject
    })
  })
}

/**
 * 微信登录
 * @returns {Promise}
 */
function wxLogin() {
  return new Promise((resolve, reject) => {
    wx.login({
      success: async res => {
        if (res.code) {
          try {
            const result = await require('./http.js').post('/auth/wx-login', { code: res.code })
            wx.setStorageSync('token', result.token)
            resolve(result)
          } catch (error) {
            reject(error)
          }
        }
      },
      fail: reject
    })
  })
}

/**
 * 获取用户信息（需要用户授权）
 * @returns {Promise}
 */
function getUserProfile() {
  return new Promise((resolve, reject) => {
    wx.getUserProfile({
      desc: '用于完善用户资料',
      success: res => {
        resolve(res.userInfo)
      },
      fail: reject
    })
  })
}

module.exports = {
  isLogin,
  goLogin,
  checkLogin,
  getLoginCode,
  wxLogin,
  getUserProfile
}
