/**
 * 地址编辑页面
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

const http = require('../../utils/http.js')

Page({
  data: {
    addressId: '',
    consignee: '',
    phone: '',
    province: '',
    city: '',
    district: '',
    detail: '',
    isDefault: false,
    region: [],
    showRegionPicker: false,
    regions: []
  },

  onLoad(options) {
    if (options.id) {
      this.setData({ addressId: options.id })
      wx.setNavigationBarTitle({ title: '编辑地址' })
      this.loadAddressDetail(options.id)
    }
    this.loadRegions()
  },

  // 加载地址详情
  loadAddressDetail(id) {
    http.get(`/address/detail/${id}`).then(res => {
      const addr = res.data
      this.setData({
        consignee: addr.consignee,
        phone: addr.phone,
        province: addr.province,
        city: addr.city,
        district: addr.district,
        detail: addr.detail,
        isDefault: addr.isDefault,
        region: [addr.province, addr.city, addr.district]
      })
    })
  },

  // 加载省市区数据
  loadRegions() {
    // 模拟省市区数据
    const regions = [
      { name: '广东省', city: [
        { name: '深圳市', district: ['福田区', '南山区', '罗湖区', '宝安区'] },
        { name: '广州市', district: ['天河区', '越秀区', '海珠区', '白云区'] }
      ]},
      { name: '北京市', city: [
        { name: '北京市', district: ['东城区', '西城区', '朝阳区', '海淀区'] }
      ]},
      { name: '上海市', city: [
        { name: '上海市', district: ['黄浦区', '徐汇区', '静安区', '浦东新区'] }
      ]}
    ]
    this.setData({ regions })
  },

  // 选择地区
  onRegionChange(e) {
    const values = e.detail.value
    const regions = this.data.regions
    this.setData({
      province: regions[values[0]].name,
      city: regions[values[0]].city[values[1]].name,
      district: regions[values[0]].city[values[1]].district[values[2]],
      region: [
        regions[values[0]].name,
        regions[values[0]].city[values[1]].name,
        regions[values[0]].city[values[1]].district[values[2]]
      ]
    })
  },

  // 输入联系人
  onConsigneeInput(e) {
    this.setData({ consignee: e.detail.value })
  },

  // 输入手机号
  onPhoneInput(e) {
    this.setData({ phone: e.detail.value })
  },

  // 输入详细地址
  onDetailInput(e) {
    this.setData({ detail: e.detail.value })
  },

  // 切换默认地址
  onDefaultChange() {
    this.setData({ isDefault: !this.data.isDefault })
  },

  // 保存地址
  onSave() {
    if (!this.data.consignee) {
      wx.showToast({ title: '请输入联系人', icon: 'none' })
      return
    }
    if (!this.data.phone) {
      wx.showToast({ title: '请输入手机号', icon: 'none' })
      return
    }
    if (!this.data.province || !this.data.city || !this.data.district) {
      wx.showToast({ title: '请选择省市区', icon: 'none' })
      return
    }
    if (!this.data.detail) {
      wx.showToast({ title: '请输入详细地址', icon: 'none' })
      return
    }

    wx.showLoading({ title: '保存中...' })

    const data = {
      consignee: this.data.consignee,
      phone: this.data.phone,
      province: this.data.province,
      city: this.data.city,
      district: this.data.district,
      detail: this.data.detail,
      isDefault: this.data.isDefault
    }

    const api = this.data.addressId ? '/address/update' : '/address/add'
    const method = this.data.addressId ? 'post' : 'post'

    http[method](api, this.data.addressId ? { id: this.data.addressId, ...data } : data).then(() => {
      wx.hideLoading()
      wx.showToast({ title: '保存成功', icon: 'success' })
      setTimeout(() => {
        wx.navigateBack()
      }, 1500)
    }).catch(() => {
      wx.hideLoading()
    })
  }
})
