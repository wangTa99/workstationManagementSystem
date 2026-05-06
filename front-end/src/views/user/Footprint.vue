<template>
  <div class="footprint-page">
    <!-- 导航栏 -->
    <NavBar title="我的足迹" :left-arrow="true" @click-left="handleBack" />

    <!-- 足迹列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="loadMore"
    >
      <!-- 按日期分组 -->
      <div v-for="(group, date) in groupedFootprints" :key="date" class="footprint-group">
        <div class="group-header">
          <span class="date">{{ formatDate(date) }}</span>
          <span class="weekday">{{ getWeekday(date) }}</span>
        </div>
        <van-card
          v-for="(item, index) in group"
          :key="index"
          :price="item.price"
          :title="item.title"
          :thumb="item.thumb"
          :origin-price="item.originPrice"
          @click="handleProductClick(item)"
        />
      </div>
    </van-list>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && footprints.length === 0"
      description="暂无足迹记录"
      :show-button="true"
      button-text="去逛逛"
      @click="handleGoShopping"
    />
  </div>
</template>

<script>
/**
 * 足迹页面
 */
import NavBar from '@/components/NavBar.vue'
import EmptyState from '@/components/EmptyState.vue'

export default {
  name: 'FootprintPage',

  components: {
    NavBar,
    EmptyState
  },

  data() {
    return {
      // 足迹列表
      footprints: [],
      // 加载状态
      loading: false,
      // 是否加载完成
      finished: false,
      // 分页页码
      page: 1
    }
  },

  computed: {
    // 按日期分组的足迹
    groupedFootprints() {
      const grouped = {}
      this.footprints.forEach(item => {
        const date = item.time.split(' ')[0]
        if (!grouped[date]) {
          grouped[date] = []
        }
        grouped[date].push(item)
      })
      return grouped
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
      const now = new Date()
      this.footprints = [
        {
          id: 1,
          title: 'iPhone 15 Pro Max 256GB 银色',
          price: '9999.00',
          originPrice: '10999.00',
          thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg',
          time: this.formatDate(now)
        },
        {
          id: 2,
          title: '华为 Mate 60 Pro 512GB',
          price: '7999.00',
          originPrice: '8999.00',
          thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg',
          time: this.formatDate(now)
        }
      ]
      this.finished = true
    },

    // 加载更多
    loadMore() {
      // 模拟加载
      this.loading = false
      this.finished = true
    },

    // 格式化日期
    formatDate(date) {
      if (typeof date === 'string') {
        return date
      }
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      return `${year}-${month}-${day}`
    },

    // 获取星期几
    getWeekday(dateStr) {
      const date = new Date(dateStr)
      const weekdays = ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
      return weekdays[date.getDay()]
    },

    // 点击商品
    handleProductClick(item) {
      this.$router.push(`/product/${item.id}`)
    },

    // 去逛逛
    handleGoShopping() {
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
.footprint-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.footprint-group {
  margin-bottom: 10px;
  background-color: #fff;
}

.group-header {
  display: flex;
  align-items: center;
  padding: 12px 16px;
  background-color: #f5f5f5;
  font-size: 14px;
}

.group-header .date {
  color: #333;
  font-weight: 500;
}

.group-header .weekday {
  margin-left: 10px;
  color: #999;
}
</style>
