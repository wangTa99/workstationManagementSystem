<template>
  <div class="refund-page">
    <!-- 导航栏 -->
    <NavBar title="退款售后" :left-arrow="true" @click-left="handleBack" />

    <!-- 状态标签 -->
    <van-tabs v-model="activeTab" sticky @change="handleTabChange">
      <van-tab title="全部" name="all" />
      <van-tab title="处理中" name="pending" />
      <van-tab title="已完成" name="completed" />
      <van-tab title="已拒绝" name="rejected" />
    </van-tabs>

    <!-- 退款列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="loadData"
    >
      <div v-for="(item, index) in refundList" :key="index" class="refund-item">
        <van-card
          :title="item.productTitle"
          :desc="item.productDesc"
          :thumb="item.thumb"
          :num="item.num"
        >
          <template #tags>
            <van-tag :type="getStatusType(item.status)">{{ item.statusText }}</van-tag>
          </template>
        </van-card>
        <div class="refund-info">
          <div class="refund-reason">
            <span>退款原因：</span>
            <span>{{ item.reason }}</span>
          </div>
          <div class="refund-amount">
            <span>退款金额：</span>
            <span class="amount">¥{{ item.amount }}</span>
          </div>
          <div class="refund-time">
            <span>申请时间：</span>
            <span>{{ item.createTime }}</span>
          </div>
        </div>
        <div v-if="item.status === 'pending'" class="refund-actions">
          <van-button size="small" plain @click="handleCancel(item)">取消申请</van-button>
          <van-button size="small" type="primary" plain @click="handleTrace(item)">查看进度</van-button>
        </div>
      </div>
    </van-list>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && refundList.length === 0"
      description="暂无退款记录"
      :show-button="false"
    />
  </div>
</template>

<script>
/**
 * 退款页面
 */
import NavBar from '@/components/NavBar.vue'
import EmptyState from '@/components/EmptyState.vue'

export default {
  name: 'RefundPage',

  components: {
    NavBar,
    EmptyState
  },

  data() {
    return {
      // 当前标签
      activeTab: 'all',
      // 退款列表
      refundList: [],
      // 加载状态
      loading: false,
      // 是否加载完成
      finished: false
    }
  },

  mounted() {
    this.loadData()
  },

  methods: {
    // 返回
    handleBack() {
      this.$router.go(-1)
    },

    // 标签切换
    handleTabChange(name) {
      this.activeTab = name
      this.loadData()
    },

    // 加载数据
    loadData() {
      this.loading = true
      // 模拟数据
      setTimeout(() => {
        this.refundList = [
          {
            id: 1,
            productTitle: 'iPhone 15 Pro Max',
            productDesc: '256GB 银色',
            thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg',
            num: 1,
            status: 'pending',
            statusText: '处理中',
            reason: '商品损坏',
            amount: '9999.00',
            createTime: '2024-01-15 10:30'
          },
          {
            id: 2,
            productTitle: 'AirPods Pro',
            productDesc: '第二代',
            thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg',
            num: 1,
            status: 'completed',
            statusText: '已完成',
            reason: '拍多了',
            amount: '1899.00',
            createTime: '2024-01-10 14:20'
          }
        ]
        this.loading = false
        this.finished = true
      }, 500)
    },

    // 获取状态类型
    getStatusType(status) {
      const types = {
        pending: 'warning',
        completed: 'success',
        rejected: 'danger'
      }
      return types[status] || 'default'
    },

    // 取消申请
    handleCancel(item) {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要取消该退款申请吗？'
      }).then(() => {
        this.$toast('取消成功')
        this.loadData()
      })
    },

    // 查看进度
    handleTrace(item) {
      this.$router.push(`/refund/trace/${item.id}`)
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
.refund-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.refund-item {
  background-color: #fff;
  margin-bottom: 10px;
  padding: 12px;
}

.refund-info {
  padding: 12px;
  font-size: 14px;
  color: #666;
}

.refund-reason,
.refund-amount,
.refund-time {
  margin-bottom: 8px;
}

.refund-amount .amount {
  color: #ee0a24;
  font-weight: bold;
}

.refund-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding-top: 10px;
  border-top: 1px solid #f5f5f5;
}
</style>
