<template>
  <div class="coupon-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="我的优惠券" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 标签栏 -->
    <van-tabs v-model="activeTab" sticky @change="handleTabChange">
      <van-tab title="可用" name="available" />
      <van-tab title="已使用" name="used" />
      <van-tab title="已过期" name="expired" />
    </van-tabs>

    <!-- 优惠券列表 -->
    <div class="coupon-list">
      <div
        class="coupon-item"
        v-for="coupon in coupons"
        :key="coupon.id"
        :class="{ disabled: activeTab !== 'available' }"
      >
        <div class="coupon-left">
          <div class="coupon-value">
            <span class="unit">¥</span>
            <span class="amount">{{ coupon.value }}</span>
          </div>
          <div class="coupon-condition" v-if="coupon.minAmount > 0">
            满{{ coupon.minAmount }}可用
          </div>
        </div>
        <div class="coupon-right">
          <div class="coupon-name">{{ coupon.couponName }}</div>
          <div class="coupon-time">
            {{ coupon.startTime }} - {{ coupon.endTime }}
          </div>
          <van-button
            v-if="activeTab === 'available'"
            size="small"
            type="danger"
            @click="handleUse(coupon)"
          >
            立即使用
          </van-button>
          <div v-else class="coupon-status">
            {{ activeTab === 'used' ? '已使用' : '已过期' }}
          </div>
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && coupons.length === 0"
      :description="emptyText"
      :showButton="activeTab === 'available'"
      buttonText="去领取"
      @click="goToCouponCenter"
    />

    <!-- 加载状态 -->
    <van-loading v-if="loading" class="loading" />
  </div>
</template>

<script>
/**
 * 我的优惠券页面组件
 */
export default {
  name: 'Coupon',

  components: {
    EmptyState
  },

  data() {
    return {
      // 当前标签
      activeTab: 'available',
      // 优惠券列表
      coupons: [],
      // 加载状态
      loading: false,
      // 分页参数
      pageNum: 1,
      pageSize: 10
    }
  },

  computed: {
    emptyText() {
      const textMap = {
        available: '暂无可用优惠券',
        used: '暂无已使用优惠券',
        expired: '暂无已过期优惠券'
      }
      return textMap[this.activeTab]
    }
  },

  created() {
    this.getCouponList()
  },

  methods: {
    /**
     * 获取优惠券列表
     */
    getCouponList() {
      if (this.loading) return
      this.loading = true

      const statusMap = {
        available: 1,
        used: 2,
        expired: 3
      }

      this.$api.getMyCouponList({
        status: statusMap[this.activeTab],
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }).then(res => {
        if (res.code === 200) {
          this.coupons = res.data.list
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 处理标签切换
     */
    handleTabChange() {
      this.pageNum = 1
      this.getCouponList()
    },

    /**
     * 使用优惠券
     */
    handleUse(coupon) {
      this.$router.push('/')
    },

    /**
     * 跳转到领券中心
     */
    goToCouponCenter() {
      this.$router.push('/coupon/center')
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
.coupon-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.coupon-list {
  padding: 10px;
}

.coupon-item {
  display: flex;
  background-color: #fff;
  border-radius: 8px;
  margin-bottom: 10px;
  overflow: hidden;
}

.coupon-item.disabled {
  opacity: 0.6;
}

.coupon-left {
  width: 100px;
  padding: 15px;
  background: linear-gradient(135deg, #f00, #ff4500);
  color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.coupon-value {
  display: flex;
  align-items: baseline;
}

.unit {
  font-size: 14px;
}

.amount {
  font-size: 28px;
  font-weight: bold;
}

.coupon-condition {
  font-size: 10px;
  margin-top: 5px;
}

.coupon-right {
  flex: 1;
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.coupon-name {
  font-size: 14px;
  font-weight: bold;
  color: #333;
}

.coupon-time {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
}

.coupon-status {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
}

.loading {
  text-align: center;
  padding: 20px;
}
</style>
