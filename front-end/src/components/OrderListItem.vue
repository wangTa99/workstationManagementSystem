<template>
  <div class="order-list-item" @click="handleClick">
    <div class="order-header">
      <span class="order-no">订单号：{{ order.orderNo }}</span>
      <span class="order-status" :class="statusClass">{{ statusText }}</span>
    </div>
    <div class="order-products">
      <div
        class="product-item"
        v-for="item in order.items"
        :key="item.id"
      >
        <van-image width="60" height="60" :src="item.productImage" />
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
    <div class="order-footer">
      <div class="order-amount">
        <span class="label">合计：</span>
        <span class="amount">¥{{ order.payAmount }}</span>
        <span class="freight" v-if="order.freightAmount > 0">（含运费¥{{ order.freightAmount }}）</span>
      </div>
      <div class="order-actions">
        <slot name="actions">
          <van-button
            v-if="order.orderStatus === 0"
            size="small"
            @click.stop="handleCancel"
          >
            取消订单
          </van-button>
          <van-button
            v-if="order.orderStatus === 0"
            type="danger"
            size="small"
            @click.stop="handlePay"
          >
            去支付
          </van-button>
          <van-button
            v-if="order.orderStatus === 2"
            type="primary"
            size="small"
            @click.stop="handleConfirm"
          >
            确认收货
          </van-button>
        </slot>
      </div>
    </div>
  </div>
</template>

<script>
/**
 * 订单列表项组件
 */
export default {
  name: 'OrderListItem',

  props: {
    // 订单信息
    order: {
      type: Object,
      required: true
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
    }
  },

  methods: {
    /**
     * 处理点击
     */
    handleClick() {
      this.$emit('click', this.order)
      this.$router.push(`/order/${this.order.id}`)
    },

    /**
     * 取消订单
     */
    handleCancel() {
      this.$emit('cancel', this.order)
    },

    /**
     * 去支付
     */
    handlePay() {
      this.$emit('pay', this.order)
    },

    /**
     * 确认收货
     */
    handleConfirm() {
      this.$emit('confirm', this.order)
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
.order-list-item {
  background-color: #fff;
  margin-bottom: 10px;
  padding: 15px;
}

.order-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.order-no {
  font-size: 12px;
  color: #666;
}

.order-status {
  font-size: 12px;
}

.status-pending {
  color: #ff976a;
}

.status-waiting {
  color: #1989fa;
}

.status-shipping {
  color: #07c160;
}

.status-completed {
  color: #999;
}

.status-cancelled,
.status-refunded {
  color: #999;
}

.order-products {
  border-bottom: 1px solid #f5f5f5;
  padding-bottom: 10px;
}

.product-item {
  display: flex;
  margin-bottom: 10px;
}

.product-item:last-child {
  margin-bottom: 0;
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

.quantity {
  color: #999;
}

.order-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.order-amount .label {
  font-size: 12px;
  color: #666;
}

.order-amount .amount {
  font-size: 16px;
  color: #f00;
  font-weight: bold;
}

.order-amount .freight {
  font-size: 12px;
  color: #999;
}

.order-actions {
  display: flex;
  gap: 10px;
}
</style>
