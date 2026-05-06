/**
 * 商品搜索页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    keyword: '',
    searchHistory: [],
    hotKeywords: ['iPhone', '华为', 'MacBook', 'AirPods', 'iPad'],
    products: [],
    loading: false,
    finished: false,
    page: 1
  },

  onLoad() {
    this.loadSearchHistory()
  },

  // 加载搜索历史
  loadSearchHistory() {
    const history = wx.getStorageSync('searchHistory') || []
    this.setData({ searchHistory: history })
  },

  // 搜索输入
  onSearchInput(e) {
    this.setData({ keyword: e.detail.value })
  },

  // 搜索确认
  onSearch() {
    if (!this.data.keyword) return
    this.saveSearchHistory(this.data.keyword)
    this.setData({ products: [], page: 1, finished: false })
    this.searchProducts()
  },

  // 保存搜索历史
  saveSearchHistory(keyword) {
    let history = this.data.searchHistory
    history = history.filter(item => item !== keyword)
    history.unshift(keyword)
    history = history.slice(0, 10)
    this.setData({ searchHistory: history })
    wx.setStorageSync('searchHistory', history)
  },

  // 点击历史记录或热门关键词
  onKeywordClick(e) {
    const keyword = e.currentTarget.dataset.keyword
    this.setData({ keyword })
    this.onSearch()
  },

  // 搜索商品
  searchProducts() {
    if (this.data.loading || this.data.finished) return

    this.setData({ loading: true })
    http.get('/product/search', {
      keyword: this.data.keyword,
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

  // 清空搜索历史
  onClearHistory() {
    wx.removeStorageSync('searchHistory')
    this.setData({ searchHistory: [] })
  },

  // 跳转到商品详情
  onGoodsClick(e) {
    const productId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/product/detail/main?id=${productId}`
    })
  },

  // 触底加载更多
  onReachBottom() {
    this.searchProducts()
  }
})
