<template>
  <div class="points-page">
    <!-- 导航栏 -->
    <NavBar title="我的积分" :left-arrow="true" @click-left="handleBack" />

    <!-- 积分卡片 -->
    <div class="points-card">
      <div class="points-balance">
        <span class="label">当前积分</span>
        <span class="value">{{ balance }}</span>
      </div>
      <van-button type="primary" size="small" plain @click="handleRules">积分规则</van-button>
    </div>

    <!-- 积分记录 -->
    <van-tabs v-model="activeTab" sticky @change="handleTabChange">
      <van-tab title="全部" name="all">
        <van-cell-group>
          <van-cell
            v-for="(item, index) in records"
            :key="index"
            :title="item.title"
            :label="item.time"
            :value="item.change > 0 ? '+' + item.change : item.change"
            :value-class="item.change > 0 ? 'points-add' : 'points-reduce'"
          />
        </van-cell-group>
      </van-tab>
      <van-tab title="收入" name="income">
        <van-cell-group>
          <van-cell
            v-for="(item, index) in incomeRecords"
            :key="index"
            :title="item.title"
            :label="item.time"
            :value="'+' + item.change"
            value-class="points-add"
          />
        </van-cell-group>
      </van-tab>
      <van-tab title="支出" name="expense">
        <van-cell-group>
          <van-cell
            v-for="(item, index) in expenseRecords"
            :key="index"
            :title="item.title"
            :label="item.time"
            :value="item.change"
            value-class="points-reduce"
          />
        </van-cell-group>
      </van-tab>
    </van-tabs>

    <!-- 空状态 -->
    <EmptyState
      v-if="records.length === 0"
      description="暂无积分记录"
      :show-button="false"
    />

    <!-- 规则弹窗 -->
    <van-popup v-model="showRules" position="bottom" round>
      <div class="rules-content">
        <h3>积分规则</h3>
        <p>1. 每日签到可获得积分</p>
        <p>2. 订单完成后可获得积分</p>
        <p>3. 积分可用于兑换优惠券</p>
        <p>4. 积分不可提现</p>
        <van-button type="primary" block @click="showRules = false">我知道了</van-button>
      </div>
    </van-popup>
  </div>
</template>

<script>
/**
 * 积分页面
 */
import NavBar from '@/components/NavBar.vue'
import EmptyState from '@/components/EmptyState.vue'

export default {
  name: 'PointsPage',

  components: {
    NavBar,
    EmptyState
  },

  data() {
    return {
      // 当前积分
      balance: 0,
      // 当前标签
      activeTab: 'all',
      // 显示规则弹窗
      showRules: false,
      // 积分记录
      records: []
    }
  },

  computed: {
    // 收入记录
    incomeRecords() {
      return this.records.filter(item => item.change > 0)
    },
    // 支出记录
    expenseRecords() {
      return this.records.filter(item => item.change < 0)
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

    // 加载数据
    loadData() {
      // 模拟数据
      this.balance = 1250
      this.records = [
        { title: '订单完成奖励', time: '2024-01-15 10:30', change: 50 },
        { title: '每日签到', time: '2024-01-14 09:00', change: 10 },
        { title: '积分兑换优惠券', time: '2024-01-10 15:20', change: -100 },
        { title: '订单完成奖励', time: '2024-01-08 14:00', change: 80 }
      ]
    },

    // 标签切换
    handleTabChange(name) {
      this.activeTab = name
    },

    // 查看规则
    handleRules() {
      this.showRules = true
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
.points-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.points-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background: linear-gradient(135deg, #1989fa, #409eff);
  color: #fff;
}

.points-balance {
  display: flex;
  flex-direction: column;
}

.points-balance .label {
  font-size: 14px;
  opacity: 0.9;
}

.points-balance .value {
  font-size: 32px;
  font-weight: bold;
  margin-top: 5px;
}

.points-add {
  color: #07c160 !important;
}

.points-reduce {
  color: #ee0a24 !important;
}

.rules-content {
  padding: 20px;
}

.rules-content h3 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 18px;
}

.rules-content p {
  line-height: 1.8;
  color: #666;
  margin-bottom: 10px;
}
</style>
