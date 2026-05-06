<template>
  <div class="recommend-page">
    <!-- 导航栏 -->
    <NavBar title="为你推荐" :left-arrow="true" @click-left="handleBack" />

    <!-- 推荐商品列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      :error.sync="error"
      error-text="加载失败，请重试"
      @load="loadData"
    >
      <div class="product-grid">
        <div
          v-for="(item, index) in products"
          :key="index"
          class="product-item"
          @click="handleProductClick(item)"
        >
          <div class="product-image">
            <img v-lazy="item.thumb" :alt="item.title" />
            <van-tag v-if="item.tag" class="product-tag" type="danger">{{ item.tag }}</van-tag>
          </div>
          <div class="product-info">
            <h4 class="product-title">{{ item.title }}</h4>
            <p class="product-desc">{{ item.desc }}</p>
            <div class="product-bottom">
              <span class="product-price">¥{{ item.price }}</span>
              <span v-if="item.originalPrice" class="product-original">¥{{ item.originalPrice }}</span>
            </div>
          </div>
        </div>
      </div>
    </van-list>
  </div>
</template>

<script>
/**
 * 为你推荐页面
 */
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'RecommendPage',

  components: {
    NavBar
  },

  data() {
    return {
      // 商品列表
      products: [],
      // 加载状态
      loading: false,
      // 是否加载完成
      finished: false,
      // 是否出错
      error: false,
      // 分页页码
      page: 1
    }
  },

  methods: {
    // 返回
    handleBack() {
      this.$router.go(-1)
    },

    // 加载数据
    loadData() {
      // 模拟推荐商品数据
      const mockProducts = [
        { id: 1, title: 'iPhone 15 Pro Max', desc: '256GB 银色钛金属', price: '9999', originalPrice: '10999', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg', tag: '热卖' },
        { id: 2, title: 'MacBook Pro 14', desc: 'M3 Pro芯片 18+512', price: '16999', originalPrice: '18999', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg', tag: '新品' },
        { id: 3, title: 'AirPods Pro', desc: '第二代 主动降噪', price: '1899', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg' },
        { id: 4, title: 'iPad Pro 12.9', desc: 'M2芯片 256GB', price: '9299', originalPrice: '9999', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg', tag: '热卖' },
        { id: 5, title: 'Apple Watch S9', desc: '45mm 蜂窝版', price: '3999', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg' },
        { id: 6, title: 'Magic Keyboard', desc: '妙控键盘 白色', price: '999', originalPrice: '1199', thumb: 'https://img.yzcdn.cn/vant/ipad.jpeg' }
      ]

      setTimeout(() => {
        this.products = [...this.products, ...mockProducts]
        this.loading = false
        this.page++

        if (this.page > 3) {
          this.finished = true
        }
      }, 500)
    },

    // 点击商品
    handleProductClick(item) {
      this.$router.push(`/product/${item.id}`)
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
.recommend-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.product-grid {
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
  gap: 10px;
}

.product-item {
  width: calc(50% - 5px);
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.product-image {
  position: relative;
  width: 100%;
  aspect-ratio: 1;
  background-color: #f5f5f5;
}

.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-tag {
  position: absolute;
  top: 8px;
  left: 8px;
}

.product-info {
  padding: 10px;
}

.product-title {
  font-size: 14px;
  font-weight: 500;
  color: #333;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 4px;
}

.product-desc {
  font-size: 12px;
  color: #999;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 8px;
}

.product-bottom {
  display: flex;
  align-items: center;
}

.product-price {
  font-size: 16px;
  color: #ee0a24;
  font-weight: bold;
}

.product-original {
  font-size: 12px;
  color: #999;
  text-decoration: line-through;
  margin-left: 6px;
}
</style>
