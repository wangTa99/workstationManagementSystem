/**
 * 结算页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')
const auth = require('../../utils/auth.js')

Page({
  data: {
    address: null,
    goodsList: [],
    coupon: null,
    couponList: [],
    showCouponPicker: false,
    remark: '',
    totalAmount: 0,
    freight: 0,
    couponAmount: 0,
    goodsAmount: 0
  },

  onLoad(options) {
    if (!auth.checkLogin()) return
    this.loadAddress()
    if (options.productId) {
      this.loadDirectGoods(options.productId, options.num)
    } else {
      this.loadCartGoods()
    }
  },

  // 加载收货地址
  loadAddress() {
    http.get('/address/default').then(res => {
      this.setData({ address: res.data })
    }).catch(() => {
      wx.navigateTo({ url: '/pages/user/address/main?from=checkout' })
    })
  },

  // 加载购物车商品
  loadCartGoods() {
    http.get('/cart/checked').then(res => {
      const list = res.data || []
      this.setData({ goodsList: list })
      this.calculateAmount()
    })
  },

  // 直接购买商品
  loadDirectGoods(productId, num) {
    http.get(`/product/detail/${productId}`).then(res => {
      const item = res.data
      this.setData({
        goodsList: [{
          productId: item.id,
          name: item.name,
          thumb: item.thumb,
          price: item.price,
          num: parseInt(num) || 1,
          spec: item.spec
        }]
      })
      this.calculateAmount()
    })
  },

  // 计算金额
  calculateAmount() {
    let goodsAmount = 0
    this.data.goodsList.forEach(item => {
      goodsAmount += item.price * item.num
    })
    const freight = goodsAmount >= 99 ? 0 : 10
    const couponAmount = this.data.coupon ? this.data.coupon.amount : 0
    const totalAmount = goodsAmount + freight - couponAmount

    this.setData({
      goodsAmount,
      freight,
      couponAmount,
      totalAmount
    })
  },

  // 选择收货地址
  onSelectAddress() {
    wx.navigateTo({ url: '/pages/user/address/main?from=checkout' })
  },

  // 选择优惠券
  onSelectCoupon() {
    http.get('/coupon/available').then(res => {
      this.setData({ couponList: res.data || [] })
      this.setData({ showCouponPicker: true })
    })
  },

  // 确认选择优惠券
  onCouponConfirm(e) {
    const index = e.detail.index
    if (index === -1) {
      this.setData({ coupon: null })
    } else {
      this.setData({ coupon: this.data.couponList[index] })
    }
    this.setData({ showCouponPicker: false })
    this.calculateAmount()
  },

  // 输入备注
  onRemarkInput(e) {
    this.setData({ remark: e.detail.value })
  },

  // 提交订单
  onSubmit() {
    if (!this.data.address) {
      wx.showToast({ title: '请选择收货地址', icon: 'none' })
      return
    }

    wx.showLoading({ title: '提交中...' })

    http.post('/order/create', {
      addressId: this.data.address.id,
      couponId: this.data.coupon ? this.data.coupon.id : null,
      remark: this.data.remark,
      items: this.data.goodsList.map(item => ({
        productId: item.productId,
        num: item.num,
        spec: item.spec
      }))
    }).then(res => {
      wx.hideLoading()
      wx.navigateTo({
        url: `/pages/order/pay/main?id=${res.data.orderId}`
      })
    }).catch(() => {
      wx.hideLoading()
    })
  }
})
