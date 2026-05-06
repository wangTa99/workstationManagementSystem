/**
 * 登录页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    phone: '',
    password: '',
    smsCode: '',
    loginType: 'password',
    countdown: 0,
    sendDisabled: false
  },

  onLoad() {
    // 检查是否已登录
    if (auth.isLogin()) {
      wx.navigateBack()
    }
  },

  // 切换登录方式
  onToggleLoginType() {
    this.setData({
      loginType: this.data.loginType === 'password' ? 'sms' : 'password'
    })
  },

  // 输入手机号
  onPhoneInput(e) {
    this.setData({ phone: e.detail.value })
  },

  // 输入密码
  onPasswordInput(e) {
    this.setData({ password: e.detail.value })
  },

  // 输入验证码
  onSmsCodeInput(e) {
    this.setData({ smsCode: e.detail.value })
  },

  // 发送验证码
  onSendSms() {
    if (!this.data.phone) {
      wx.showToast({ title: '请输入手机号', icon: 'none' })
      return
    }
    if (!/^1[3-9]\d{9}$/.test(this.data.phone)) {
      wx.showToast({ title: '手机号格式不正确', icon: 'none' })
      return
    }

    http.post('/auth/sms/send', { phone: this.data.phone }).then(() => {
      wx.showToast({ title: '发送成功', icon: 'success' })
      this.setData({ countdown: 60, sendDisabled: true })
      this.startCountdown()
    })
  },

  // 倒计时
  startCountdown() {
    const timer = setInterval(() => {
      if (this.data.countdown > 0) {
        this.setData({ countdown: this.data.countdown - 1 })
      } else {
        clearInterval(timer)
        this.setData({ sendDisabled: false })
      }
    }, 1000)
  },

  // 登录
  onLogin() {
    if (!this.data.phone) {
      wx.showToast({ title: '请输入手机号', icon: 'none' })
      return
    }

    if (this.data.loginType === 'password' && !this.data.password) {
      wx.showToast({ title: '请输入密码', icon: 'none' })
      return
    }

    if (this.data.loginType === 'sms' && !this.data.smsCode) {
      wx.showToast({ title: '请输入验证码', icon: 'none' })
      return
    }

    wx.showLoading({ title: '登录中...' })

    const data = this.data.loginType === 'password'
      ? { phone: this.data.phone, password: this.data.password }
      : { phone: this.data.phone, smsCode: this.data.smsCode }

    http.post('/auth/login', data).then(res => {
      wx.hideLoading()
      wx.setStorageSync('token', res.token)
      wx.showToast({ title: '登录成功', icon: 'success' })
      setTimeout(() => {
        wx.navigateBack()
      }, 1500)
    }).catch(() => {
      wx.hideLoading()
    })
  },

  // 跳转到注册
  onRegister() {
    wx.navigateTo({ url: '/pages/user/register/main' })
  }
})
