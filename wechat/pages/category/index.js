/**
 * 分类页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    // 分类列表
    categories: [],
    // 当前选中的分类
    currentCategory: 0,
    // 商品列表
    products: [],
    // 加载状态
    loading: false
  },

  onLoad() {
    this.loadCategories()
  },

  // 加载分类数据
  loadCategories() {
    http.get('/category/list').then(res => {
      this.setData({
        categories: res.data || []
      })
      if (res.data && res.data.length > 0) {
        this.loadProducts(res.data[0].id)
      }
    })
  },

  // 加载商品列表
  loadProducts(categoryId) {
    this.setData({ loading: true })
    http.get('/product/list', { categoryId }).then(res => {
      this.setData({
        products: res.data || [],
        loading: false
      })
    }).catch(() => {
      this.setData({ loading: false })
    })
  },

  // 切换分类
  onCategoryChange(e) {
    const index = e.currentTarget.dataset.index
    const category = this.data.categories[index]
    this.setData({ currentCategory: index })
    this.loadProducts(category.id)
  },

  // 点击商品
  onProductClick(e) {
    const productId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/product/detail/main?id=${productId}`
    })
  }
})
