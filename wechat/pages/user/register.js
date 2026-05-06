/**
 * 注册页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    phone: '',
    password: '',
    confirmPassword: '',
    smsCode: '',
    nickname: '',
    countdown: 0,
    sendDisabled: false,
    agreementChecked: false
  },

  // 输入手机号
  onPhoneInput(e) {
    this.setData({ phone: e.detail.value })
  },

  // 输入密码
  onPasswordInput(e) {
    this.setData({ password: e.detail.value })
  },

  // 确认密码
  onConfirmPasswordInput(e) {
    this.setData({ confirmPassword: e.detail.value })
  },

  // 输入验证码
  onSmsCodeInput(e) {
    this.setData({ smsCode: e.detail.value })
  },

  // 输入昵称
  onNicknameInput(e) {
    this.setData({ nickname: e.detail.value })
  },

  // 同意协议
  onAgreementChange() {
    this.setData({ agreementChecked: !this.data.agreementChecked })
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

  // 注册
  onRegister() {
    if (!this.data.phone) {
      wx.showToast({ title: '请输入手机号', icon: 'none' })
      return
    }
    if (!this.data.nickname) {
      wx.showToast({ title: '请输入昵称', icon: 'none' })
      return
    }
    if (!this.data.password) {
      wx.showToast({ title: '请输入密码', icon: 'none' })
      return
    }
    if (this.data.password !== this.data.confirmPassword) {
      wx.showToast({ title: '两次密码输入不一致', icon: 'none' })
      return
    }
    if (!this.data.smsCode) {
      wx.showToast({ title: '请输入验证码', icon: 'none' })
      return
    }
    if (!this.data.agreementChecked) {
      wx.showToast({ title: '请同意用户协议', icon: 'none' })
      return
    }

    wx.showLoading({ title: '注册中...' })

    http.post('/auth/register', {
      phone: this.data.phone,
      password: this.data.password,
      smsCode: this.data.smsCode,
      nickname: this.data.nickname
    }).then(() => {
      wx.hideLoading()
      wx.showToast({ title: '注册成功', icon: 'success' })
      setTimeout(() => {
        wx.navigateBack()
      }, 1500)
    }).catch(() => {
      wx.hideLoading()
    })
  }
})
