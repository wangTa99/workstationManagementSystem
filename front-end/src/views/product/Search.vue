<template>
  <div class="search-container">
    <!-- 搜索框 -->
    <div class="search-header">
      <van-search
        v-model="keyword"
        placeholder="搜索商品"
        show-action
        @search="handleSearch"
        @cancel="handleCancel"
      />
    </div>

    <!-- 搜索历史 -->
    <div class="search-history" v-if="historyList.length > 0 && !keyword">
      <div class="history-header">
        <span class="title">搜索历史</span>
        <van-icon name="delete" @click="clearHistory" />
      </div>
      <div class="history-tags">
        <van-tag
          v-for="(item, index) in historyList"
          :key="index"
          size="large"
          @click="handleHistoryClick(item)"
        >
          {{ item }}
        </van-tag>
      </div>
    </div>

    <!-- 热门搜索 -->
    <div class="hot-search" v-if="!keyword">
      <div class="hot-header">
        <span class="title">热门搜索</span>
      </div>
      <div class="hot-list">
        <div
          class="hot-item"
          v-for="(item, index) in hotList"
          :key="index"
          @click="handleHistoryClick(item.keyword)"
        >
          <span class="rank" :class="{ top: index < 3 }">{{ index + 1 }}</span>
          <span class="keyword">{{ item.keyword }}</span>
          <span class="count">{{ item.count }}人搜索</span>
        </div>
      </div>
    </div>

    <!-- 搜索结果 -->
    <div class="search-result" v-if="keyword && products.length > 0">
      <div
        class="product-item"
        v-for="product in products"
        :key="product.id"
        @click="goToProduct(product.id)"
      >
        <van-image width="100" height="100" :src="product.mainImage" />
        <div class="product-info">
          <span class="product-name">{{ product.productName }}</span>
          <div class="product-price">
            <span class="current-price">¥{{ product.price }}</span>
            <span class="original-price" v-if="product.originalPrice">
              ¥{{ product.originalPrice }}
            </span>
          </div>
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="keyword && products.length === 0 && !loading"
      description="未找到相关商品"
    />

    <!-- 加载状态 -->
    <van-loading v-if="loading" class="loading" />
  </div>
</template>

<script>
/**
 * 搜索页面组件
 */
export default {
  name: 'Search',

  components: {
    EmptyState
  },

  data() {
    return {
      // 搜索关键词
      keyword: '',
      // 搜索历史
      historyList: [],
      // 热门搜索列表
      hotList: [],
      // 搜索结果
      products: [],
      // 加载状态
      loading: false
    }
  },

  created() {
    // 获取搜索历史
    this.getHistory()
    // 获取热门搜索
    this.getHotList()
  },

  methods: {
    /**
     * 获取搜索历史
     */
    getHistory() {
      const history = localStorage.getItem('searchHistory')
      if (history) {
        this.historyList = JSON.parse(history)
      }
    },

    /**
     * 保存搜索历史
     */
    saveHistory(keyword) {
      if (!keyword) return

      // 获取已有历史
      let history = localStorage.getItem('searchHistory')
      history = history ? JSON.parse(history) : []

      // 添加新历史
      history = history.filter(item => item !== keyword)
      history.unshift(keyword)

      // 只保留10条
      history = history.slice(0, 10)

      // 保存
      localStorage.setItem('searchHistory', JSON.stringify(history))
      this.historyList = history
    },

    /**
     * 清除搜索历史
     */
    clearHistory() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要清除搜索历史吗？'
      }).then(() => {
        localStorage.removeItem('searchHistory')
        this.historyList = []
      })
    },

    /**
     * 获取热门搜索列表
     */
    getHotList() {
      this.$api.getHotSearchList().then(res => {
        if (res.code === 200) {
          this.hotList = res.data
        }
      })
    },

    /**
     * 搜索
     */
    handleSearch() {
      if (!this.keyword) return

      this.loading = true
      this.saveHistory(this.keyword)

      this.$api.search({
        keyword: this.keyword
      }).then(res => {
        if (res.code === 200) {
          this.products = res.data
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 处理历史点击
     */
    handleHistoryClick(keyword) {
      this.keyword = keyword
      this.handleSearch()
    },

    /**
     * 取消搜索
     */
    handleCancel() {
      this.$router.back()
    },

    /**
     * 跳转到商品详情
     */
    goToProduct(productId) {
      this.$router.push(`/product/${productId}`)
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
.search-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.search-header {
  position: sticky;
  top: 0;
  z-index: 1;
  background-color: #fff;
}

.search-history {
  padding: 15px;
  background-color: #fff;
}

.history-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.title {
  font-size: 14px;
  font-weight: bold;
  color: #333;
}

.history-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.hot-search {
  padding: 15px;
  background-color: #fff;
  margin-top: 10px;
}

.hot-header {
  margin-bottom: 10px;
}

.hot-list {
  display: flex;
  flex-direction: column;
}

.hot-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
}

.rank {
  width: 20px;
  font-size: 14px;
  color: #999;
}

.rank.top {
  color: #f00;
  font-weight: bold;
}

.keyword {
  flex: 1;
  font-size: 14px;
  color: #333;
}

.count {
  font-size: 12px;
  color: #999;
}

.search-result {
  padding: 10px;
}

.product-item {
  display: flex;
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  margin-bottom: 10px;
}

.product-info {
  flex: 1;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.product-name {
  font-size: 14px;
  color: #333;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.product-price {
  display: flex;
  align-items: center;
}

.current-price {
  color: #f00;
  font-weight: bold;
}

.original-price {
  color: #999;
  font-size: 12px;
  text-decoration: line-through;
  margin-left: 10px;
}

.loading {
  text-align: center;
  padding: 20px;
}
</style>
