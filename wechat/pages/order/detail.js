/**
 * 订单详情页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    orderId: '',
    order: null,
    logistics: null
  },

  onLoad(options) {
    this.setData({ orderId: options.id })
    this.loadOrderDetail()
  },

  // 加载订单详情
  loadOrderDetail() {
    http.get(`/order/detail/${this.data.orderId}`).then(res => {
      this.setData({ order: res.data })
      if (res.data.status === 'shipped') {
        this.loadLogistics()
      }
    })
  },

  // 加载物流信息
  loadLogistics() {
    http.get(`/order/logistics/${this.data.orderId}`).then(res => {
      this.setData({ logistics: res.data })
    })
  },

  // 确认收货
  onConfirm() {
    wx.showModal({
      title: '提示',
      content: '确认已收到货物吗？',
      success: res => {
        if (res.confirm) {
          http.post('/order/confirm', { id: this.data.orderId }).then(() => {
            wx.showToast({ title: '确认收货成功', icon: 'success' })
            this.loadOrderDetail()
          })
        }
      }
    })
  },

  // 去支付
  onPay() {
    wx.navigateTo({
      url: `/pages/order/pay/main?id=${this.data.orderId}`
    })
  },

  // 申请退款
  onRefund() {
    wx.navigateTo({
      url: `/pages/order/refund/main?orderId=${this.data.orderId}`
    })
  },

  // 复制订单号
  onCopy() {
    wx.setClipboardData({
      data: this.data.order.orderNo,
      success: () => {
        wx.showToast({ title: '复制成功', icon: 'success' })
      }
    })
  }
})
