<template>
  <div class="order-list-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="我的订单" fixed placeholder left-arrow @click-left="goBack">
      <template #right>
        <van-icon name="search" size="20" @click="showSearch = true" />
      </template>
    </van-nav-bar>

    <!-- 标签栏 -->
    <van-tabs v-model="activeTab" sticky @change="handleTabChange">
      <van-tab title="全部" name="" />
      <van-tab title="待付款" name="0" />
      <van-tab title="待发货" name="1" />
      <van-tab title="待收货" name="2" />
      <van-tab title="已完成" name="3" />
    </van-tabs>

    <!-- 订单列表 -->
    <div class="order-list">
      <OrderListItem
        v-for="order in orders"
        :key="order.id"
        :order="order"
        @click="goToDetail"
        @cancel="handleCancel"
        @pay="handlePay"
        @confirm="handleConfirm"
      />
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && orders.length === 0"
      description="暂无订单"
      :showButton="true"
      buttonText="去逛逛"
      @click="goToHome"
    />

    <!-- 加载状态 -->
    <van-loading v-if="loading" class="loading" />

    <!-- 分页加载 -->
    <div v-if="hasMore && !loading" class="load-more" @click="loadMore">
      加载更多
    </div>

    <!-- 搜索弹窗 -->
    <van-popup v-model="showSearch" position="top" class="search-popup">
      <van-search
        v-model="searchKeyword"
        placeholder="搜索订单号"
        @search="handleSearch"
        @cancel="showSearch = false"
      />
    </van-popup>
  </div>
</template>

<script>
/**
 * 订单列表页面组件
 */
export default {
  name: 'OrderList',

  components: {
    OrderListItem,
    EmptyState
  },

  data() {
    return {
      // 当前标签
      activeTab: '',
      // 订单列表
      orders: [],
      // 加载状态
      loading: false,
      // 是否有更多数据
      hasMore: true,
      // 分页参数
      pageNum: 1,
      pageSize: 10,
      // 是否显示搜索
      showSearch: false,
      // 搜索关键词
      searchKeyword: ''
    }
  },

  created() {
    // 获取订单状态参数
    const status = this.$route.query.status
    if (status !== undefined && status !== '') {
      this.activeTab = String(status)
    }
    // 获取订单列表
    this.getOrderList()
  },

  methods: {
    /**
     * 获取订单列表
     */
    getOrderList() {
      if (this.loading) return
      this.loading = true

      this.$api.getOrderList({
        orderStatus: this.activeTab || undefined,
        keyword: this.searchKeyword || undefined,
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }).then(res => {
        if (res.code === 200) {
          if (this.pageNum === 1) {
            this.orders = res.data.list
          } else {
            this.orders.push(...res.data.list)
          }
          this.hasMore = res.data.hasMore
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
      this.hasMore = true
      this.getOrderList()
    },

    /**
     * 加载更多
     */
    loadMore() {
      this.pageNum++
      this.getOrderList()
    },

    /**
     * 处理搜索
     */
    handleSearch() {
      this.showSearch = false
      this.pageNum = 1
      this.getOrderList()
    },

    /**
     * 跳转到订单详情
     */
    goToDetail(order) {
      this.$router.push(`/order/${order.id}`)
    },

    /**
     * 取消订单
     */
    handleCancel(order) {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要取消该订单吗？'
      }).then(() => {
        this.$api.cancelOrder(order.id).then(res => {
          if (res.code === 200) {
            this.$toast.success('取消成功')
            this.getOrderList()
          }
        })
      })
    },

    /**
     * 去支付
     */
    handlePay(order) {
      this.$router.push(`/pay/${order.id}`)
    },

    /**
     * 确认收货
     */
    handleConfirm(order) {
      this.$dialog.confirm({
        title: '提示',
        message: '确认已收到货物吗？'
      }).then(() => {
        this.$api.confirmReceipt(order.id).then(res => {
          if (res.code === 200) {
            this.$toast.success('确认收货成功')
            this.getOrderList()
          }
        })
      })
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1)
    },

    /**
     * 跳转到首页
     */
    goToHome() {
      this.$router.push('/')
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
.order-list-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 50px;
}

.order-list {
  padding: 10px;
}

.loading {
  text-align: center;
  padding: 20px;
}

.load-more {
  text-align: center;
  padding: 15px;
  color: #666;
  font-size: 14px;
}

.search-popup {
  height: auto;
}
</style>
