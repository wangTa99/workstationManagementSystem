/**
 * 优惠券页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    tabs: [
      { key: 'available', text: '可用' },
      { key: 'used', text: '已使用' },
      { key: 'expired', text: '已过期' }
    ],
    activeTab: 'available',
    couponList: [],
    loading: false
  },

  onLoad() {
    if (!auth.checkLogin()) return
    this.loadCouponList()
  },

  onTabChange(e) {
    const key = e.currentTarget.dataset.key
    this.setData({ activeTab: key, couponList: [] })
    this.loadCouponList()
  },

  // 加载优惠券列表
  loadCouponList() {
    this.setData({ loading: true })
    http.get('/coupon/list', { status: this.data.activeTab }).then(res => {
      this.setData({
        couponList: res.data || [],
        loading: false
      })
    }).catch(() => {
      this.setData({ loading: false })
    })
  },

  // 使用优惠券
  onUseCoupon(e) {
    const coupon = e.currentTarget.dataset.coupon
    wx.navigateBack()
  },

  // 格式化日期
  formatDate(dateStr) {
    if (!dateStr) return ''
    const date = new Date(dateStr)
    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')
    return `${year}.${month}.${day}`
  }
})
