/**
 * 商品评论页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    productId: '',
    comments: [],
    loading: false,
    page: 1,
    finished: false
  },

  onLoad(options) {
    this.setData({ productId: options.productId || '' })
    this.loadComments()
  },

  onReachBottom() {
    if (!this.data.finished && !this.data.loading) {
      this.loadComments()
    }
  },

  // 加载评论列表
  loadComments() {
    if (this.data.loading || this.data.finished) return

    this.setData({ loading: true })
    http.get('/comment/list', {
      productId: this.data.productId,
      page: this.data.page,
      pageSize: 10
    }).then(res => {
      const list = res.data.list || []
      this.setData({
        comments: [...this.data.comments, ...list],
        page: this.data.page + 1,
        loading: false,
        finished: this.data.page >= res.data.pages
      })
    }).catch(() => {
      this.setData({ loading: false })
    })
  },

  // 预览图片
  onPreviewImage(e) {
    const urls = e.currentTarget.dataset.urls
    const current = e.currentTarget.dataset.url
    wx.previewImage({
      urls,
      current
    })
  },

  // 格式化时间
  formatTime(time) {
    const date = new Date(time)
    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')
    return `${year}-${month}-${day}`
  }
})
