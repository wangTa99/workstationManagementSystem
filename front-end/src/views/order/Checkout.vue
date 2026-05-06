<template>
  <div class="checkout-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="确认订单" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 收货地址 -->
    <div class="address-section" @click="selectAddress">
      <div v-if="selectedAddress" class="address-content">
        <div class="consignee">
          <span>{{ selectedAddress.consigneeName }}</span>
          <span>{{ selectedAddress.consigneePhone }}</span>
        </div>
        <div class="address-detail">
          {{ selectedAddress.province }}{{ selectedAddress.city }}{{ selectedAddress.district }}{{ selectedAddress.detailAddress }}
        </div>
      </div>
      <div v-else class="add-address">
        <van-icon name="plus" />
        <span>添加收货地址</span>
      </div>
      <van-icon name="arrow" class="arrow-icon" />
    </div>

    <!-- 商品列表 -->
    <div class="product-section">
      <div
        class="product-item"
        v-for="item in orderItems"
        :key="item.id"
      >
        <van-image width="80" height="80" :src="item.productImage" />
        <div class="product-info">
          <span class="product-name">{{ item.productName }}</span>
          <span class="product-spec" v-if="item.specifications">{{ item.specifications }}</span>
          <div class="product-price">
            <span class="price">¥{{ item.price }}</span>
            <span class="quantity">x{{ item.quantity }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 优惠券 -->
    <div class="coupon-section" @click="selectCoupon">
      <van-cell title="优惠券" is-link :value="selectedCoupon ? `-¥${selectedCoupon.value}` : '暂无可用'" />
    </div>

    <!-- 积分抵扣 -->
    <div class="points-section">
      <van-cell title="积分抵扣" :value="`-¥${pointsDiscount}`" />
    </div>

    <!-- 订单备注 -->
    <div class="remark-section">
      <van-field
        v-model="remark"
        label="订单备注"
        placeholder="选填，可备注特殊需求"
      />
    </div>

    <!-- 金额明细 -->
    <div class="amount-section">
      <van-cell-group>
        <van-cell title="商品金额">
          <span>¥{{ totalAmount }}</span>
        </van-cell>
        <van-cell title="运费">
          <span>¥{{ freightAmount }}</span>
        </van-cell>
        <van-cell title="优惠">
          <span class="discount">-¥{{ discountAmount }}</span>
        </van-cell>
        <van-cell title="积分抵扣">
          <span>-¥{{ pointsDiscount }}</span>
        </van-cell>
        <van-cell title="实付金额">
          <span class="pay-amount">¥{{ payAmount }}</span>
        </van-cell>
      </van-cell-group>
    </div>

    <!-- 底部结算栏 -->
    <div class="bottom-bar">
      <div class="total-info">
        <span class="label">合计：</span>
        <span class="amount">¥{{ payAmount }}</span>
      </div>
      <van-button type="danger" size="large" @click="handleSubmit" :loading="loading">
        提交订单
      </van-button>
    </div>
  </div>
</template>

<script>
/**
 * 订单结算页面组件
 */
export default {
  name: 'Checkout',

  data() {
    return {
      // 订单商品列表
      orderItems: [],
      // 收货地址
      selectedAddress: null,
      // 优惠券
      selectedCoupon: null,
      // 订单备注
      remark: '',
      // 加载状态
      loading: false,
      // 运费
      freightAmount: 0,
      // 优惠金额
      discountAmount: 0
    }
  },

  computed: {
    // 商品总金额
    totalAmount() {
      return this.orderItems.reduce((sum, item) => sum + item.price * item.quantity, 0).toFixed(2)
    },
    // 积分抵扣金额
    pointsDiscount() {
      // 假设100积分抵扣1元
      const userPoints = this.$store.state.userInfo?.score || 0
      const maxDiscount = Math.min(Math.floor(userPoints / 100), 10)
      return maxDiscount.toFixed(2)
    },
    // 实付金额
    payAmount() {
      const total = parseFloat(this.totalAmount) + parseFloat(this.freightAmount)
      const discount = parseFloat(this.discountAmount) + parseFloat(this.pointsDiscount)
      return Math.max(0, total - discount).toFixed(2)
    }
  },

  created() {
    // 解析订单商品
    const items = this.$route.query.items
    if (items) {
      try {
        this.orderItems = JSON.parse(items)
      } catch (e) {
        this.orderItems = []
      }
    }
    // 获取默认收货地址
    this.getDefaultAddress()
  },

  methods: {
    /**
     * 获取默认收货地址
     */
    getDefaultAddress() {
      this.$api.getDefaultAddress().then(res => {
        if (res.code === 200 && res.data) {
          this.selectedAddress = res.data
        }
      })
    },

    /**
     * 选择收货地址
     */
    selectAddress() {
      this.$router.push('/address?mode=select')
    },

    /**
     * 选择优惠券
     */
    selectCoupon() {
      this.$router.push({
        path: '/coupon/select',
        query: { amount: this.totalAmount }
      })
    },

    /**
     * 提交订单
     */
    handleSubmit() {
      if (!this.selectedAddress) {
        this.$toast('请选择收货地址')
        return
      }

      this.loading = true
      this.$api.createOrder({
        addressId: this.selectedAddress.id,
        items: this.orderItems,
        remark: this.remark,
        couponId: this.selectedCoupon?.id,
        points: Math.floor(this.pointsDiscount * 100)
      }).then(res => {
        if (res.code === 200) {
          this.$toast.success('订单提交成功')
          this.$router.replace(`/pay/${res.data.id}`)
        } else {
          this.$toast.fail(res.message)
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
.checkout-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 60px;
}

.address-section {
  display: flex;
  align-items: center;
  padding: 15px;
  background-color: #fff;
  margin-bottom: 10px;
}

.address-content {
  flex: 1;
}

.consignee {
  display: flex;
  gap: 15px;
  font-weight: bold;
  font-size: 16px;
}

.address-detail {
  margin-top: 8px;
  color: #666;
  font-size: 14px;
}

.add-address {
  flex: 1;
  display: flex;
  align-items: center;
  color: #999;
}

.add-address span {
  margin-left: 10px;
}

.arrow-icon {
  color: #999;
}

.product-section {
  background-color: #fff;
  padding: 15px;
  margin-bottom: 10px;
}

.product-item {
  display: flex;
  padding: 10px 0;
}

.product-info {
  flex: 1;
  margin-left: 10px;
}

.product-name {
  font-size: 14px;
  color: #333;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.product-spec {
  font-size: 12px;
  color: #999;
  display: block;
  margin-top: 5px;
}

.product-price {
  display: flex;
  justify-content: space-between;
  margin-top: 5px;
}

.price {
  color: #f00;
  font-weight: bold;
}

.coupon-section,
.points-section,
.remark-section,
.amount-section {
  background-color: #fff;
  margin-bottom: 10px;
}

.discount {
  color: #07c160;
}

.pay-amount {
  color: #f00;
  font-weight: bold;
  font-size: 16px;
}

.bottom-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  padding: 10px 15px;
  background-color: #fff;
  border-top: 1px solid #eee;
}

.total-info {
  flex: 1;
}

.total-info .label {
  color: #666;
}

.total-info .amount {
  color: #f00;
  font-weight: bold;
  font-size: 18px;
}
</style>
