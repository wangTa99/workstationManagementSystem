/**
 * 用户中心页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const auth = require('../../utils/auth.js')
const http = require('../../utils/http.js')

Page({
  data: {
    userInfo: null,
    isLogin: false,
    menuList: [
      [
        { id: 'order', text: '我的订单', icon: 'orders-o', url: '/pages/order/list/main' },
        { id: 'coupon', text: '优惠券', icon: 'coupon-o', url: '/pages/user/coupon/main' },
        { id: 'points', text: '我的积分', icon: 'points-o', url: '/pages/user/points/main' }
      ],
      [
        { id: 'collect', text: '我的收藏', icon: 'star-o', url: '/pages/user/collect/main' },
        { id: 'address', text: '收货地址', icon: 'location-o', url: '/pages/user/address/main' },
        { id: 'feedback', text: '意见反馈', icon: 'edit-o', url: '/pages/user/feedback/main' }
      ],
      [
        { id: 'setting', text: '设置', icon: 'setting-o', url: '/pages/user/setting/main' }
      ]
    ],
    orderStats: {
      pending: 0,
      paid: 0,
      shipped: 0,
      completed: 0
    }
  },

  onShow() {
    const isLogin = auth.isLogin()
    this.setData({ isLogin })
    if (isLogin) {
      this.loadUserInfo()
      this.loadOrderStats()
    }
  },

  // 加载用户信息
  loadUserInfo() {
    const userInfo = wx.getStorageSync('userInfo')
    this.setData({ userInfo })
  },

  // 加载订单统计
  loadOrderStats() {
    http.get('/order/stats').then(res => {
      this.setData({ orderStats: res.data || {} })
    })
  },

  // 去登录
  onLogin() {
    wx.navigateTo({ url: '/pages/user/login/main' })
  },

  // 点击菜单
  onMenuClick(e) {
    const url = e.currentTarget.dataset.url
    if (!auth.checkLogin()) return
    wx.navigateTo({ url })
  },

  // 跳转到订单列表
  onOrderClick(e) {
    if (!auth.checkLogin()) return
    const status = e.currentTarget.dataset.status
    wx.navigateTo({
      url: `/pages/order/list/main?status=${status || ''}`
    })
  }
})
