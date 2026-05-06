/**
 * 支付页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    orderId: '',
    order: null,
    payMethods: [
      { id: 'wechat', name: '微信支付', icon: 'wechat', checked: true },
      { id: 'balance', name: '余额支付', icon: 'balance' }
    ],
    selectedPayMethod: 'wechat',
    countdown: 1800,
    timer: null
  },

  onLoad(options) {
    this.setData({ orderId: options.id })
    this.loadOrder()
    this.startCountdown()
  },

  onUnload() {
    if (this.data.timer) {
      clearInterval(this.data.timer)
    }
  },

  // 加载订单信息
  loadOrder() {
    http.get(`/order/detail/${this.data.orderId}`).then(res => {
      this.setData({ order: res.data })
    })
  },

  // 开始倒计时
  startCountdown() {
    this.data.timer = setInterval(() => {
      if (this.data.countdown > 0) {
        this.setData({ countdown: this.data.countdown - 1 })
      } else {
        clearInterval(this.data.timer)
        wx.showToast({ title: '订单已超时', icon: 'none' })
        setTimeout(() => {
          wx.navigateBack()
        }, 1500)
      }
    }, 1000)
  },

  // 格式化时间
  formatTime(seconds) {
    const min = Math.floor(seconds / 60)
    const sec = seconds % 60
    return `${String(min).padStart(2, '0')}:${String(sec).padStart(2, '0')}`
  },

  // 选择支付方式
  onPayMethodChange(e) {
    const index = e.currentTarget.dataset.index
    this.setData({ selectedPayMethod: this.data.payMethods[index].id })
  },

  // 确认支付
  onPay() {
    wx.showLoading({ title: '支付中...' })

    http.post('/order/pay', {
      orderId: this.data.orderId,
      payMethod: this.data.selectedPayMethod
    }).then(() => {
      wx.hideLoading()
      wx.showToast({ title: '支付成功', icon: 'success' })
      setTimeout(() => {
        wx.redirectTo({
          url: `/pages/order/detail/main?id=${this.data.orderId}`
        })
      }, 1500)
    }).catch(() => {
      wx.hideLoading()
    })
  },

  // 取消支付
  onCancel() {
    wx.showModal({
      title: '提示',
      content: '确定取消支付吗？',
      success: res => {
        if (res.confirm) {
          wx.navigateBack()
        }
      }
    })
  }
})
