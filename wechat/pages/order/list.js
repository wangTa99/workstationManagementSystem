/**
 * 订单列表页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    status: '',
    tabs: [
      { status: '', text: '全部' },
      { status: 'pending', text: '待支付' },
      { status: 'paid', text: '待发货' },
      { status: 'shipped', text: '待收货' },
      { status: 'completed', text: '已完成' }
    ],
    activeTab: 0,
    orders: [],
    loading: false,
    page: 1,
    finished: false
  },

  onLoad(options) {
    if (options.status) {
      this.setData({ status: options.status })
      const index = this.data.tabs.findIndex(tab => tab.status === options.status)
      if (index !== -1) {
        this.setData({ activeTab: index })
      }
    }
    this.loadOrders()
  },

  onShow() {
    if (!auth.checkLogin()) return
    this.refreshOrders()
  },

  onReachBottom() {
    if (!this.data.finished && !this.data.loading) {
      this.loadOrders()
    }
  },

  onPullDownRefresh() {
    this.refreshOrders()
  },

  // 切换标签
  onTabChange(e) {
    const index = e.detail.index
    const tab = this.data.tabs[index]
    this.setData({
      status: tab.status,
      activeTab: index,
      orders: [],
      page: 1,
      finished: false
    })
    this.loadOrders()
  },

  // 加载订单列表
  loadOrders() {
    if (this.data.loading || this.data.finished) return

    this.setData({ loading: true })
    http.get('/order/list', {
      status: this.data.status,
      page: this.data.page,
      pageSize: 10
    }).then(res => {
      const list = res.data.list || []
      this.setData({
        orders: [...this.data.orders, ...list],
        page: this.data.page + 1,
        loading: false,
        finished: this.data.page >= res.data.pages
      })
      wx.stopPullDownRefresh()
    }).catch(() => {
      this.setData({ loading: false })
      wx.stopPullDownRefresh()
    })
  },

  // 刷新订单列表
  refreshOrders() {
    this.setData({
      orders: [],
      page: 1,
      finished: false
    })
    this.loadOrders()
  },

  // 跳转到订单详情
  onOrderClick(e) {
    const orderId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/order/detail/main?id=${orderId}`
    })
  },

  // 取消订单
  onCancel(e) {
    const orderId = e.currentTarget.dataset.id
    wx.showModal({
      title: '提示',
      content: '确定取消该订单吗？',
      success: res => {
        if (res.confirm) {
          http.post('/order/cancel', { id: orderId }).then(() => {
            wx.showToast({ title: '订单已取消', icon: 'success' })
            this.refreshOrders()
          })
        }
      }
    })
  },

  // 确认收货
  onConfirm(e) {
    const orderId = e.currentTarget.dataset.id
    wx.showModal({
      title: '提示',
      content: '确认已收到货物吗？',
      success: res => {
        if (res.confirm) {
          http.post('/order/confirm', { id: orderId }).then(() => {
            wx.showToast({ title: '确认收货成功', icon: 'success' })
            this.refreshOrders()
          })
        }
      }
    })
  },

  // 去支付
  onPay(e) {
    const orderId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/order/pay/main?id=${orderId}`
    })
  }
})
