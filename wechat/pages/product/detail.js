/**
 * 商品详情页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    productId: '',
    product: null,
    selectedSpec: [],
    num: 1,
    collected: false,
    specs: []
  },

  onLoad(options) {
    this.setData({ productId: options.id })
    this.loadProductDetail()
  },

  // 加载商品详情
  loadProductDetail() {
    http.get(`/product/detail/${this.data.productId}`).then(res => {
      this.setData({
        product: res.data,
        specs: res.data.specs || []
      })
      // 默认选中第一个规格
      if (res.data.specs && res.data.specs.length > 0) {
        const selectedSpec = res.data.specs.map(spec => spec.values[0])
        this.setData({ selectedSpec })
      }
    })
  },

  // 选择规格
  onSpecChange(e) {
    const specIndex = e.currentTarget.dataset.specIndex
    const valueIndex = e.currentTarget.dataset.valueIndex
    this.data.selectedSpec[specIndex] = this.data.specs[specIndex].values[valueIndex]
    this.setData({ selectedSpec: this.data.selectedSpec })
  },

  // 增加数量
  onIncrease() {
    this.setData({ num: this.data.num + 1 })
  },

  // 减少数量
  onDecrease() {
    if (this.data.num > 1) {
      this.setData({ num: this.data.num - 1 })
    }
  },

  // 收藏/取消收藏
  onToggleCollect() {
    if (!auth.checkLogin()) return
    const api = this.data.collected ? '/collect/delete' : '/collect/add'
    http.post(api, { productId: this.data.productId }).then(() => {
      this.setData({ collected: !this.data.collected })
      wx.showToast({
        title: this.data.collected ? '已取消收藏' : '收藏成功',
        icon: 'success'
      })
    })
  },

  // 加入购物车
  onAddCart() {
    if (!auth.checkLogin()) return
    http.post('/cart/add', {
      productId: this.data.productId,
      num: this.data.num,
      spec: this.data.selectedSpec.join(',')
    }).then(() => {
      wx.showToast({ title: '加入购物车成功', icon: 'success' })
    })
  },

  // 立即购买
  onBuyNow() {
    if (!auth.checkLogin()) return
    wx.navigateTo({
      url: `/pages/order/checkout/main?productId=${this.data.productId}&num=${this.data.num}`
    })
  }
})
