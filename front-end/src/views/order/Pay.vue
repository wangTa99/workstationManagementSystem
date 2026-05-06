<template>
  <div class="pay-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="订单支付" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 订单金额 -->
    <div class="amount-section">
      <div class="amount-label">应付金额</div>
      <div class="amount-value">¥{{ order.payAmount }}</div>
      <div class="order-no">订单号：{{ order.orderNo }}</div>
    </div>

    <!-- 支付方式 -->
    <div class="pay-methods">
      <div class="section-title">选择支付方式</div>
      <van-radio-group v-model="selectedPayType">
        <van-cell-group>
          <van-cell
            v-for="method in payMethods"
            :key="method.type"
            clickable
            @click="selectedPayType = method.type"
          >
            <template #icon>
              <van-icon :name="method.icon" size="24" :color="method.color" />
            </template>
            <template #title>
              <span class="method-name">{{ method.name }}</span>
            </template>
            <template #right-icon>
              <van-radio :name="method.type" />
            </template>
          </van-cell>
        </van-cell-group>
      </van-radio-group>
    </div>

    <!-- 确认支付按钮 -->
    <div class="pay-button">
      <van-button type="danger" block @click="handlePay" :loading="loading">
        确认支付
      </van-button>
    </div>

    <!-- 倒计时提示 -->
    <div class="countdown-tip" v-if="countdown > 0">
      请在 <van-count-down :time="countdown" format="mm分ss秒" /> 内完成支付，超时订单将自动取消
    </div>
  </div>
</template>

<script>
/**
 * 订单支付页面组件
 */
export default {
  name: 'Pay',

  data() {
    return {
      // 订单ID
      orderId: null,
      // 订单信息
      order: {},
      // 选择的支付方式
      selectedPayType: 1,
      // 支付方式列表
      payMethods: [
        { type: 1, name: '微信支付', icon: 'wechat', color: '#07c160' },
        { type: 2, name: '支付宝', icon: 'alipay', color: '#1890ff' },
        { type: 3, name: '银行卡支付', icon: 'card', color: '#666' }
      ],
      // 加载状态
      loading: false,
      // 支付倒计时（30分钟）
      countdown: 30 * 60 * 1000
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
     * 处理支付
     */
    handlePay() {
      this.loading = true

      this.$api.payOrder(this.orderId, this.selectedPayType).then(res => {
        if (res.code === 200) {
          // 支付成功
          this.$toast.success('支付成功')
          // 跳转到支付成功页面
          this.$router.replace(`/pay/success?orderId=${this.orderId}`)
        } else {
          this.$toast.fail(res.message || '支付失败')
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要取消支付吗？'
      }).then(() => {
        this.$router.replace(`/order/${this.orderId}`)
      })
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
.pay-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.amount-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 20px;
  background-color: #fff;
  margin-bottom: 10px;
}

.amount-label {
  font-size: 14px;
  color: #666;
}

.amount-value {
  font-size: 32px;
  font-weight: bold;
  color: #f00;
  margin: 15px 0;
}

.order-no {
  font-size: 12px;
  color: #999;
}

.pay-methods {
  background-color: #fff;
  padding: 15px;
}

.section-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 15px;
}

.method-name {
  margin-left: 10px;
  font-size: 14px;
}

.pay-button {
  padding: 20px 15px;
}

.countdown-tip {
  text-align: center;
  padding: 10px;
  color: #999;
  font-size: 12px;
}
</style>
