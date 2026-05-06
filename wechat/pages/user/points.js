/**
 * 积分页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')
const format = require('../../utils/format.js')

Page({
  data: {
    balance: 0,
    records: [],
    loading: false
  },

  onShow() {
    if (!auth.checkLogin()) return
    this.loadData()
  },

  // 加载数据
  loadData() {
    this.loadBalance()
    this.loadRecords()
  },

  // 加载积分余额
  loadBalance() {
    http.get('/points/balance').then(res => {
      this.setData({ balance: res.data })
    })
  },

  // 加载积分记录
  loadRecords() {
    this.setData({ loading: true })
    http.get('/points/records').then(res => {
      this.setData({
        records: res.data || [],
        loading: false
      })
    }).catch(() => {
      this.setData({ loading: false })
    })
  },

  // 签到
  onSignIn() {
    http.post('/points/sign').then(res => {
      wx.showToast({ title: '签到成功', icon: 'success' })
      this.loadData()
    })
  },

  // 格式化时间
  formatTime(time) {
    return format.formatDate(time, 'MM-DD HH:mm')
  },

  // 格式化金额
  formatAmount(amount) {
    return amount > 0 ? `+${amount}` : amount
  }
})
