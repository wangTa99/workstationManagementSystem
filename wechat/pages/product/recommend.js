/**
 * 为你推荐页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    products: [],
    loading: false,
    page: 1,
    finished: false
  },

  onLoad() {
    this.loadRecommend()
  },

  onReachBottom() {
    if (!this.data.finished && !this.data.loading) {
      this.loadRecommend()
    }
  },

  // 下拉刷新
  onPullDownRefresh() {
    this.setData({
      products: [],
      page: 1,
      finished: false
    })
    this.loadRecommend()
    wx.stopPullDownRefresh()
  },

  // 加载推荐商品
  loadRecommend() {
    if (this.data.loading || this.data.finished) return

    this.setData({ loading: true })
    http.get('/product/recommend', {
      page: this.data.page,
      pageSize: 10
    }).then(res => {
      const list = res.data.list || []
      this.setData({
        products: [...this.data.products, ...list],
        page: this.data.page + 1,
        loading: false,
        finished: this.data.page >= res.data.pages
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
  }
})
