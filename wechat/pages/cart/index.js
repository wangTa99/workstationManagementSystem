/**
 * 购物车页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    // 购物车列表
    cartList: [],
    // 是否全选
    checkedAll: false,
    // 总价格
    totalPrice: 0,
    // 是否编辑模式
    isEdit: false
  },

  onShow() {
    if (!auth.checkLogin()) return
    this.loadCart()
  },

  // 加载购物车
  loadCart() {
    http.get('/cart/list').then(res => {
      const list = res.data || []
      list.forEach(item => {
        item.checked = false
      })
      this.setData({ cartList: list })
      this.calculateTotal()
    })
  },

  // 选择商品
  onCheckedChange(e) {
    const index = e.currentTarget.dataset.index
    const checked = e.detail
    this.data.cartList[index].checked = checked
    this.setData({ cartList: this.data.cartList })
    this.calculateTotal()
    this.updateCheckedAll()
  },

  // 全选
  onCheckedAll() {
    const checked = !this.data.checkedAll
    this.data.cartList.forEach(item => {
      item.checked = checked
    })
    this.setData({
      cartList: this.data.cartList,
      checkedAll: checked
    })
    this.calculateTotal()
  },

  // 计算总价
  calculateTotal() {
    let total = 0
    this.data.cartList.forEach(item => {
      if (item.checked) {
        total += item.price * item.num
      }
    })
    this.setData({ totalPrice: total })
  },

  // 更新全选状态
  updateCheckedAll() {
    const checkedAll = this.data.cartList.every(item => item.checked)
    this.setData({ checkedAll })
  },

  // 增加数量
  onIncrease(e) {
    const index = e.currentTarget.dataset.index
    this.data.cartList[index].num++
    this.setData({ cartList: this.data.cartList })
    this.calculateTotal()
    this.updateCart(this.data.cartList[index])
  },

  // 减少数量
  onDecrease(e) {
    const index = e.currentTarget.dataset.index
    if (this.data.cartList[index].num > 1) {
      this.data.cartList[index].num--
      this.setData({ cartList: this.data.cartList })
      this.calculateTotal()
      this.updateCart(this.data.cartList[index])
    }
  },

  // 更新购物车
  updateCart(item) {
    http.post('/cart/update', {
      id: item.id,
      num: item.num
    })
  },

  // 删除商品
  onDelete(e) {
    const index = e.currentTarget.dataset.index
    const item = this.data.cartList[index]
    wx.showModal({
      title: '提示',
      content: '确定删除该商品？',
      success: res => {
        if (res.confirm) {
          http.post('/cart/delete', { id: item.id }).then(() => {
            this.data.cartList.splice(index, 1)
            this.setData({ cartList: this.data.cartList })
            this.calculateTotal()
          })
        }
      }
    })
  },

  // 去结算
  onCheckout() {
    const selectedItems = this.data.cartList.filter(item => item.checked)
    if (selectedItems.length === 0) {
      wx.showToast({ title: '请选择商品', icon: 'none' })
      return
    }
    wx.navigateTo({
      url: '/pages/order/checkout/main'
    })
  },

  // 跳转到商品详情
  onGoodsClick(e) {
    const productId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/product/detail/main?id=${productId}`
    })
  }
})
