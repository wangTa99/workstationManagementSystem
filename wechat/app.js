/**
 * 小程序入口文件
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('./utils/http.js')
const auth = require('./utils/auth.js')

App({
  // 全局数据
  globalData: {
    userInfo: null,
    token: null,
    baseUrl: 'http://localhost:8080/api'
  },

  // 小程序初始化
  onLaunch(options) {
    // 检查登录状态
    this.checkLogin()
    // 获取系统信息
    this.getSystemInfo()
  },

  // 检查登录状态
  checkLogin() {
    const token = wx.getStorageSync('token')
    if (token) {
      this.globalData.token = token
      this.getUserInfo()
    }
  },

  // 获取用户信息
  getUserInfo() {
    http.request({
      url: '/user/info',
      method: 'GET'
    }).then(res => {
      this.globalData.userInfo = res.data
      this.updateUserInfo(res.data)
    }).catch(() => {
      this.logout()
    })
  },

  // 更新本地用户信息
  updateUserInfo(userInfo) {
    wx.setStorageSync('userInfo', userInfo)
  },

  // 登录
  login(callback) {
    wx.login({
      success: res => {
        if (res.code) {
          http.request({
            url: '/auth/login',
            method: 'POST',
            data: { code: res.code }
          }).then(result => {
            this.globalData.token = result.token
            wx.setStorageSync('token', result.token)
            this.getUserInfo()
            callback && callback(true)
          }).catch(() => {
            callback && callback(false)
          })
        }
      }
    })
  },

  // 登出
  logout() {
    this.globalData.userInfo = null
    this.globalData.token = null
    wx.removeStorageSync('token')
    wx.removeStorageSync('userInfo')
  },

  // 获取系统信息
  getSystemInfo() {
    const systemInfo = wx.getSystemInfoSync()
    this.globalData.statusBarHeight = systemInfo.statusBarHeight
    this.globalData.systemInfo = systemInfo
  }
})
