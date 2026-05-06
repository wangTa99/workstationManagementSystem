/**
 * 我的收藏页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    collectList: [],
    loading: false
  },

  onShow() {
    if (!auth.checkLogin()) return
    this.loadCollectList()
  },

  // 加载收藏列表
  loadCollectList() {
    this.setData({ loading: true })
    http.get('/collect/list').then(res => {
      this.setData({
        collectList: res.data || [],
        loading: false
      })
    }).catch(() => {
      this.setData({ loading: false })
    })
  },

  // 点击商品
  onGoodsClick(e) {
    const productId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/product/detail/main?id=${productId}`
    })
  },

  // 取消收藏
  onCancelCollect(e) {
    const index = e.currentTarget.dataset.index
    const item = this.data.collectList[index]

    wx.showModal({
      title: '提示',
      content: '确定取消收藏该商品吗？',
      success: res => {
        if (res.confirm) {
          http.post('/collect/delete', { productId: item.productId }).then(() => {
            wx.showToast({ title: '已取消收藏', icon: 'success' })
            this.data.collectList.splice(index, 1)
            this.setData({ collectList: this.data.collectList })
          })
        }
      }
    })
  },

  // 添加到购物车
  onAddCart(e) {
    const item = e.currentTarget.dataset.item
    http.post('/cart/add', {
      productId: item.productId,
      num: 1
    }).then(() => {
      wx.showToast({ title: '已加入购物车', icon: 'success' })
    })
  }
})
