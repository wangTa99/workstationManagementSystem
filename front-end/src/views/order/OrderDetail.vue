<template>
  <div class="order-detail-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="订单详情" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 订单状态 -->
    <div class="order-status" :class="statusClass">
      <van-icon :name="statusIcon" size="40" />
      <span class="status-text">{{ statusText }}</span>
    </div>

    <!-- 收货信息 -->
    <div class="address-section">
      <van-icon name="location-o" size="20" />
      <div class="address-info">
        <div class="consignee">
          <span>{{ order.receiverName }}</span>
          <span>{{ order.receiverPhone }}</span>
        </div>
        <div class="address-detail">
          {{ order.receiverAddress }}
        </div>
      </div>
    </div>

    <!-- 商品列表 -->
    <div class="product-section">
      <div
        class="product-item"
        v-for="item in order.items"
        :key="item.id"
        @click="goToProduct(item.productId)"
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

    <!-- 订单信息 -->
    <div class="order-info-section">
      <van-cell-group>
        <van-cell title="订单编号" :value="order.orderNo" />
        <van-cell title="下单时间" :value="order.createTime" />
        <van-cell title="支付方式" :value="payTypeText" v-if="order.orderStatus > 0" />
        <van-cell title="支付时间" :value="order.payTime" v-if="order.payStatus === 1" />
        <van-cell title="发货时间" :value="order.shipTime" v-if="order.orderStatus >= 2" />
        <van-cell title="收货时间" :value="order.receiveTime" v-if="order.orderStatus === 3" />
      </van-cell-group>
    </div>

    <!-- 金额信息 -->
    <div class="amount-section">
      <van-cell-group>
        <van-cell title="商品金额">
          <span>¥{{ order.totalAmount }}</span>
        </van-cell>
        <van-cell title="运费">
          <span>¥{{ order.freightAmount }}</span>
        </van-cell>
        <van-cell title="优惠">
          <span class="discount">-¥{{ order.discountAmount }}</span>
        </van-cell>
        <van-cell title="积分抵扣">
          <span>-¥{{ order.pointAmount }}</span>
        </van-cell>
        <van-cell title="实付金额">
          <span class="pay-amount">¥{{ order.payAmount }}</span>
        </van-cell>
      </van-cell-group>
    </div>

    <!-- 物流信息 -->
    <div class="express-section" v-if="order.expressNo">
      <van-cell-group title="物流信息">
        <van-cell title="物流公司" :value="order.expressCompany" />
        <van-cell title="运单号" :value="order.expressNo" />
      </van-cell-group>
    </div>

    <!-- 底部操作栏 -->
    <div class="bottom-action" v-if="showActions">
      <van-button v-if="order.orderStatus === 0" @click="handleCancel">取消订单</van-button>
      <van-button v-if="order.orderStatus === 0" type="danger" @click="handlePay">去支付</van-button>
      <van-button v-if="order.orderStatus === 2" type="primary" @click="handleConfirm">确认收货</van-button>
      <van-button v-if="order.orderStatus === 3" type="primary" @click="handleComment">去评价</van-button>
    </div>
  </div>
</template>

<script>
/**
 * 订单详情页面组件
 */
export default {
  name: 'OrderDetail',

  data() {
    return {
      // 订单ID
      orderId: null,
      // 订单信息
      order: {}
    }
  },

  computed: {
    // 状态文本
    statusText() {
      const statusMap = {
        0: '待付款',
        1: '待发货',
        2: '待收货',
        3: '已完成',
        4: '已取消',
        5: '已退款'
      }
      return statusMap[this.order.orderStatus] || '未知状态'
    },
    // 状态样式类
    statusClass() {
      const classMap = {
        0: 'status-pending',
        1: 'status-waiting',
        2: 'status-shipping',
        3: 'status-completed',
        4: 'status-cancelled',
        5: 'status-refunded'
      }
      return classMap[this.order.orderStatus] || ''
    },
    // 状态图标
    statusIcon() {
      const iconMap = {
        0: 'pending-payment',
        1: 'logistics',
        2: 'todo-list-o',
        3: 'completed',
        4: 'close',
        5: 'refund'
      }
      return iconMap[this.order.orderStatus] || 'info'
    },
    // 支付方式文本
    payTypeText() {
      const payTypeMap = {
        1: '微信支付',
        2: '支付宝',
        3: '银行卡',
        4: '余额'
      }
      return payTypeMap[this.order.payType] || '未支付'
    },
    // 是否显示底部操作栏
    showActions() {
      return this.order.orderStatus !== 4 && this.order.orderStatus !== 5
    }
  },

  created() {
    this.orderId = this.$route.params.id
    this.getOrderDetail()
  },

  methods: {
    /**
     * 获取订单详情
     */
    getOrderDetail() {
      this.$api.getOrderDetail(this.orderId).then(res => {
        if (res.code === 200) {
          this.order = res.data
        }
      })
    },

    /**
     * 取消订单
     */
    handleCancel() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要取消该订单吗？'
      }).then(() => {
        this.$api.cancelOrder(this.orderId).then(res => {
          if (res.code === 200) {
            this.$toast.success('取消成功')
            this.getOrderDetail()
          }
        })
      })
    },

    /**
     * 去支付
     */
    handlePay() {
      this.$router.push(`/pay/${this.orderId}`)
    },

    /**
     * 确认收货
     */
    handleConfirm() {
      this.$dialog.confirm({
        title: '提示',
        message: '确认已收到货物吗？'
      }).then(() => {
        this.$api.confirmReceipt(this.orderId).then(res => {
          if (res.code === 200) {
            this.$toast.success('确认收货成功')
            this.getOrderDetail()
          }
        })
      })
    },

    /**
     * 去评价
     */
    handleComment() {
      this.$router.push(`/comment/create/${this.orderId}`)
    },

    /**
     * 跳转到商品详情
     */
    goToProduct(productId) {
      this.$router.push(`/product/${productId}`)
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
.order-detail-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 70px;
}

.order-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  color: #fff;
}

.status-pending {
  background: linear-gradient(135deg, #ff976a, #ff6034);
}

.status-waiting {
  background: linear-gradient(135deg, #1989fa, #007aff);
}

.status-shipping {
  background: linear-gradient(135deg, #07c160, #00c853);
}

.status-completed {
  background: linear-gradient(135deg, #999, #666);
}

.address-section {
  display: flex;
  padding: 15px;
  background-color: #fff;
  margin-bottom: 10px;
}

.address-info {
  flex: 1;
  margin-left: 10px;
}

.consignee {
  display: flex;
  gap: 15px;
  font-weight: bold;
}

.address-detail {
  margin-top: 5px;
  color: #666;
  font-size: 14px;
}

.product-section {
  background-color: #fff;
  padding: 15px;
  margin-bottom: 10px;
}

.product-item {
  display: flex;
  padding: 10px 0;
  border-bottom: 1px solid #f5f5f5;
}

.product-item:last-child {
  border-bottom: none;
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

.order-info-section,
.amount-section,
.express-section {
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

.bottom-action {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding: 10px 15px;
  background-color: #fff;
  border-top: 1px solid #eee;
}
</style>
