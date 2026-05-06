/**
 * 地址列表页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    addressList: [],
    from: ''
  },

  onLoad(options) {
    this.setData({ from: options.from || '' })
  },

  onShow() {
    if (!auth.checkLogin()) return
    this.loadAddressList()
  },

  // 加载地址列表
  loadAddressList() {
    http.get('/address/list').then(res => {
      this.setData({ addressList: res.data || [] })
    })
  },

  // 选择地址（从结算页面跳转过来）
  onSelectAddress(e) {
    const address = e.currentTarget.dataset.address
    if (this.data.from === 'checkout') {
      const pages = getCurrentPages()
      const prevPage = pages[pages.length - 2]
      prevPage.setData({ address })
      wx.navigateBack()
    }
  },

  // 添加地址
  onAddAddress() {
    wx.navigateTo({ url: '/pages/user/address-edit/main' })
  },

  // 编辑地址
  onEditAddress(e) {
    const addressId = e.currentTarget.dataset.id
    wx.navigateTo({ url: `/pages/user/address-edit/main?id=${addressId}` })
  },

  // 删除地址
  onDeleteAddress(e) {
    const addressId = e.currentTarget.dataset.id
    wx.showModal({
      title: '提示',
      content: '确定删除该地址吗？',
      success: res => {
        if (res.confirm) {
          http.post('/address/delete', { id: addressId }).then(() => {
            wx.showToast({ title: '删除成功', icon: 'success' })
            this.loadAddressList()
          })
        }
      }
    })
  },

  // 设置默认地址
  onSetDefault(e) {
    const addressId = e.currentTarget.dataset.id
    http.post('/address/setDefault', { id: addressId }).then(() => {
      wx.showToast({ title: '设置成功', icon: 'success' })
      this.loadAddressList()
    })
  }
})
