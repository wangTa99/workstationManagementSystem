<template>
  <div class="user-container">
    <!-- 顶部用户信息区 -->
    <div class="user-header">
      <div class="user-info" v-if="isLoggedIn">
        <van-image round width="60" height="60" :src="userInfo.avatar" />
        <div class="info-detail">
          <span class="username">{{ userInfo.username }}</span>
          <span class="user-level">Lv.{{ userInfo.level }} {{ userInfo.levelName }}</span>
        </div>
      </div>
      <div class="user-info" v-else @click="goToLogin">
        <van-image round width="60" height="60" :src="defaultAvatar" />
        <div class="info-detail">
          <span class="username">点击登录</span>
          <span class="user-level">登录后享受更多权益</span>
        </div>
      </div>
    </div>

    <!-- 订单入口 -->
    <div class="order-section">
      <div class="section-header">
        <span class="section-title">我的订单</span>
        <span class="more" @click="goToOrderList()">
          全部订单
          <van-icon name="arrow" />
        </span>
      </div>
      <div class="order-status">
        <div class="status-item" @click="goToOrderList(0)">
          <van-icon name="pending-payment" :info="orderCount.pendingPayment || ''" />
          <span>待付款</span>
        </div>
        <div class="status-item" @click="goToOrderList(1)">
          <van-icon name="logistics" :info="orderCount.pendingShipment || ''" />
          <span>待发货</span>
        </div>
        <div class="status-item" @click="goToOrderList(2)">
          <van-icon name="todo-list-o" :info="orderCount.pendingReceipt || ''" />
          <span>待收货</span>
        </div>
        <div class="status-item" @click="goToOrderList(3)">
          <van-icon name="completed" :info="orderCount.completed || ''" />
          <span>已完成</span>
        </div>
      </div>
    </div>

    <!-- 功能列表 -->
    <div class="menu-section">
      <van-cell-group>
        <van-cell title="我的收藏" is-link @click="goToCollect">
          <template #icon>
            <van-icon name="star" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="我的优惠券" is-link @click="goToCoupon">
          <template #icon>
            <van-icon name="coupon" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="收货地址" is-link @click="goToAddress">
          <template #icon>
            <van-icon name="location" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="浏览历史" is-link @click="goToFootprint">
          <template #icon>
            <van-icon name="clock" class="menu-icon" />
          </template>
        </van-cell>
      </van-cell-group>
    </div>

    <div class="menu-section">
      <van-cell-group>
        <van-cell title="账号设置" is-link @click="goToAccount">
          <template #icon>
            <van-icon name="setting" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="意见反馈" is-link @click="goToFeedback">
          <template #icon>
            <van-icon name="comment" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="关于我们" is-link @click="goToAbout">
          <template #icon>
            <van-icon name="info" class="menu-icon" />
          </template>
        </van-cell>
        <van-cell title="联系客服" is-link @click="callService">
          <template #icon>
            <van-icon name="service" class="menu-icon" />
          </template>
        </van-cell>
      </van-cell-group>
    </div>

    <!-- 退出登录按钮 -->
    <van-button
      v-if="isLoggedIn"
      type="danger"
      size="large"
      class="logout-btn"
      @click="handleLogout"
    >
      退出登录
    </van-button>

    <!-- 底部导航 -->
    <van-tabbar v-model="activeTab">
      <van-tabbar-item icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item icon="search">分类</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" :badge="cartCount">购物车</van-tabbar-item>
      <van-tabbar-item icon="user-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
/**
 * 个人中心页面组件
 * 展示用户信息和提供各种功能入口
 */
export default {
  name: 'User',

  data() {
    return {
      // 当前选中的标签
      activeTab: 3,
      // 是否登录
      isLoggedIn: false,
      // 用户信息
      userInfo: {},
      // 默认头像
      defaultAvatar: 'https://img.yzcdn.cn/vant/cat.jpeg',
      // 订单数量统计
      orderCount: {
        pendingPayment: 0,
        pendingShipment: 0,
        pendingReceipt: 0,
        completed: 0
      },
      // 购物车数量
      cartCount: 0
    }
  },

  created() {
    // 检查登录状态
    this.checkLoginStatus()
    // 获取用户信息
    this.getUserInfo()
    // 获取订单数量
    this.getOrderCount()
    // 获取购物车数量
    this.getCartCount()
  },

  methods: {
    /**
     * 检查登录状态
     */
    checkLoginStatus() {
      const token = localStorage.getItem('token')
      this.isLoggedIn = !!token
    },

    /**
     * 获取用户信息
     */
    getUserInfo() {
      if (!this.isLoggedIn) return

      this.$api.getUserInfo().then(res => {
        if (res.code === 200) {
          this.userInfo = res.data
        }
      })
    },

    /**
     * 获取订单数量统计
     */
    getOrderCount() {
      if (!this.isLoggedIn) return

      this.$api.getOrderCount().then(res => {
        if (res.code === 200) {
          this.orderCount = res.data
        }
      })
    },

    /**
     * 获取购物车数量
     */
    getCartCount() {
      this.$api.getCartCount().then(res => {
        if (res.code === 200) {
          this.cartCount = res.data
        }
      })
    },

    /**
     * 跳转到登录页面
     */
    goToLogin() {
      this.$router.push('/login')
    },

    /**
     * 跳转到订单列表
     */
    goToOrderList(status) {
      this.$router.push({ path: '/order', query: { status: status || '' } })
    },

    /**
     * 跳转到收藏页面
     */
    goToCollect() {
      if (!this.checkLogin()) return
      this.$router.push('/collect')
    },

    /**
     * 跳转到优惠券页面
     */
    goToCoupon() {
      if (!this.checkLogin()) return
      this.$router.push('/coupon')
    },

    /**
     * 跳转到收货地址页面
     */
    goToAddress() {
      if (!this.checkLogin()) return
      this.$router.push('/address')
    },

    /**
     * 跳转到浏览历史页面
     */
    goToFootprint() {
      if (!this.checkLogin()) return
      this.$router.push('/footprint')
    },

    /**
     * 跳转到账号设置页面
     */
    goToAccount() {
      if (!this.checkLogin()) return
      this.$router.push('/account')
    },

    /**
     * 跳转到意见反馈页面
     */
    goToFeedback() {
      if (!this.checkLogin()) return
      this.$router.push('/feedback')
    },

    /**
     * 跳转到关于我们页面
     */
    goToAbout() {
      this.$router.push('/about')
    },

    /**
     * 联系客服
     */
    callService() {
      this.$dialog.confirm({
        title: '联系客服',
        message: '客服电话：400-xxx-xxxx',
        confirmButtonText: '拨打'
      }).then(() => {
        // 实际项目中调用拨打电话
      })
    },

    /**
     * 退出登录
     */
    handleLogout() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要退出登录吗？'
      }).then(() => {
        this.$api.logout().then(res => {
          if (res.code === 200) {
            localStorage.removeItem('token')
            localStorage.removeItem('userInfo')
            this.isLoggedIn = false
            this.userInfo = {}
            this.$toast.success('退出成功')
          }
        })
      })
    },

    /**
     * 检查登录状态并提示
     */
    checkLogin() {
      if (!this.isLoggedIn) {
        this.$toast('请先登录')
        setTimeout(() => {
          this.goToLogin()
        }, 500)
        return false
      }
      return true
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
.user-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 50px;
}

.user-header {
  display: flex;
  align-items: center;
  height: 120px;
  padding: 20px;
  background: linear-gradient(135deg, #ff6b6b, #ff4757);
  color: #fff;
}

.user-info {
  display: flex;
  align-items: center;
}

.info-detail {
  margin-left: 15px;
  display: flex;
  flex-direction: column;
}

.username {
  font-size: 18px;
  font-weight: bold;
}

.user-level {
  font-size: 12px;
  margin-top: 5px;
  opacity: 0.8;
}

.order-section {
  background-color: #fff;
  margin-bottom: 10px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #f5f5f5;
}

.section-title {
  font-size: 16px;
  font-weight: bold;
}

.more {
  font-size: 12px;
  color: #999;
}

.order-status {
  display: flex;
  padding: 15px 0;
}

.status-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.status-item span {
  font-size: 12px;
  margin-top: 5px;
  color: #333;
}

.menu-section {
  margin-bottom: 10px;
}

.menu-icon {
  margin-right: 10px;
  font-size: 18px;
}

.logout-btn {
  margin: 20px 15px;
}
</style>
