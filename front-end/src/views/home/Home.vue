<template>
  <div class="home-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="首页" fixed placeholder />

    <!-- 搜索框 -->
    <div class="search-bar" @click="goToSearch">
      <van-icon name="search" />
      <span>搜索商品</span>
    </div>

    <!-- 轮播图 -->
    <van-swipe class="banner-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item v-for="(banner, index) in banners" :key="index">
        <img :src="banner.imageUrl" @click="handleBannerClick(banner)" />
      </van-swipe-item>
    </van-swipe>

    <!-- 分类导航 -->
    <div class="category-nav">
      <div
        class="category-item"
        v-for="category in categories"
        :key="category.id"
        @click="goToCategory(category.id)"
      >
        <van-image width="40" height="40" :src="category.icon" round />
        <span class="category-name">{{ category.categoryName }}</span>
      </div>
    </div>

    <!-- 活动区域 -->
    <div class="activity-area" v-if="activities.length > 0">
      <div class="activity-item" v-for="activity in activities" :key="activity.id">
        <img :src="activity.imageUrl" @click="handleActivityClick(activity)" />
      </div>
    </div>

    <!-- 秒杀专区 -->
    <div class="seckill-section" v-if="seckillProducts.length > 0">
      <div class="section-header">
        <span class="section-title">限时秒杀</span>
        <van-count-down :time="seckillEndTime" format="HH:mm:ss" />
        <span class="more" @click="goToSeckill">更多</span>
      </div>
      <div class="seckill-products">
        <div
          class="seckill-product"
          v-for="product in seckillProducts"
          :key="product.id"
          @click="goToProductDetail(product.id)"
        >
          <van-image width="100" height="100" :src="product.mainImage" />
          <div class="product-info">
            <span class="seckill-price">¥{{ product.seckillPrice }}</span>
            <span class="original-price">¥{{ product.originalPrice }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 推荐商品 -->
    <div class="recommend-section">
      <div class="section-header">
        <span class="section-title">热门推荐</span>
      </div>
      <div class="product-list">
        <div
          class="product-item"
          v-for="product in recommendProducts"
          :key="product.id"
          @click="goToProductDetail(product.id)"
        >
          <van-image width="160" height="160" :src="product.mainImage" />
          <div class="product-detail">
            <span class="product-name">{{ product.productName }}</span>
            <div class="product-price">
              <span class="current-price">¥{{ product.price }}</span>
              <span class="original-price" v-if="product.originalPrice">
                ¥{{ product.originalPrice }}
              </span>
            </div>
            <div class="product-sales">已售{{ product.salesCount }}件</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 底部导航 -->
    <van-tabbar v-model="activeTab" @change="handleTabChange">
      <van-tabbar-item icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item icon="search">分类</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" :badge="cartCount">购物车</van-tabbar-item>
      <van-tabbar-item icon="user-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
/**
 * 首页组件
 * 展示首页的各个功能模块
 */
export default {
  name: 'Home',

  data() {
    return {
      // 当前选中的底部标签
      activeTab: 0,
      // 轮播图列表
      banners: [],
      // 分类列表
      categories: [],
      // 活动列表
      activities: [],
      // 秒杀商品列表
      seckillProducts: [],
      // 秒杀结束时间
      seckillEndTime: 0,
      // 推荐商品列表
      recommendProducts: [],
      // 购物车数量
      cartCount: 0
    }
  },

  created() {
    // 初始化页面数据
    this.initData()
    // 获取轮播图
    this.getBanners()
    // 获取分类
    this.getCategories()
    // 获取活动
    this.getActivities()
    // 获取秒杀商品
    this.getSeckillProducts()
    // 获取推荐商品
    this.getRecommendProducts()
    // 获取购物车数量
    this.getCartCount()
  },

  methods: {
    /**
     * 初始化数据
     */
    initData() {
      // 初始化页面所需的默认数据
    },

    /**
     * 获取轮播图数据
     */
    getBanners() {
      // 调用API获取轮播图
      this.$api.getBannerList().then(res => {
        if (res.code === 200) {
          this.banners = res.data
        }
      })
    },

    /**
     * 获取分类数据
     */
    getCategories() {
      // 调用API获取分类
      this.$api.getCategoryTree().then(res => {
        if (res.code === 200) {
          this.categories = res.data
        }
      })
    },

    /**
     * 获取活动数据
     */
    getActivities() {
      // 调用API获取活动
      this.$api.getActivityList().then(res => {
        if (res.code === 200) {
          this.activities = res.data
        }
      })
    },

    /**
     * 获取秒杀商品数据
     */
    getSeckillProducts() {
      // 调用API获取秒杀商品
      this.$api.getSeckillProducts().then(res => {
        if (res.code === 200) {
          this.seckillProducts = res.data
        }
      })
      // 设置秒杀结束时间（示例：2小时后）
      this.seckillEndTime = 2 * 60 * 60 * 1000
    },

    /**
     * 获取推荐商品数据
     */
    getRecommendProducts() {
      // 调用API获取推荐商品
      this.$api.getRecommendProducts({ limit: 10 }).then(res => {
        if (res.code === 200) {
          this.recommendProducts = res.data
        }
      })
    },

    /**
     * 获取购物车数量
     */
    getCartCount() {
      // 调用API获取购物车数量
      this.$api.getCartCount().then(res => {
        if (res.code === 200) {
          this.cartCount = res.data
        }
      })
    },

    /**
     * 跳转到搜索页面
     */
    goToSearch() {
      this.$router.push('/search')
    },

    /**
     * 跳转到分类页面
     */
    goToCategory(categoryId) {
      this.$router.push(`/category/${categoryId}`)
    },

    /**
     * 跳转到秒杀专区
     */
    goToSeckill() {
      this.$router.push('/seckill')
    },

    /**
     * 跳转到商品详情页
     */
    goToProductDetail(productId) {
      this.$router.push(`/product/${productId}`)
    },

    /**
     * 处理轮播图点击事件
     */
    handleBannerClick(banner) {
      if (banner.linkUrl) {
        this.$router.push(banner.linkUrl)
      }
    },

    /**
     * 处理活动点击事件
     */
    handleActivityClick(activity) {
      if (activity.linkUrl) {
        this.$router.push(activity.linkUrl)
      }
    },

    /**
     * 处理底部标签切换事件
     */
    handleTabChange(index) {
      switch (index) {
        case 0:
          this.$router.push('/')
          break
        case 1:
          this.$router.push('/category')
          break
        case 2:
          this.$router.push('/cart')
          break
        case 3:
          this.$router.push('/user')
          break
      }
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
.home-container {
  background-color: #f5f5f5;
  padding-bottom: 50px;
}

.search-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
  margin: 10px 15px;
  background-color: #fff;
  border-radius: 20px;
  color: #999;
}

.search-bar span {
  margin-left: 5px;
}

.banner-swipe {
  height: 150px;
}

.banner-swipe img {
  width: 100%;
  height: 100%;
}

.category-nav {
  display: flex;
  flex-wrap: wrap;
  padding: 15px 0;
  background-color: #fff;
}

.category-item {
  width: 20%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 10px;
}

.category-name {
  font-size: 12px;
  margin-top: 5px;
  color: #333;
}

.activity-area {
  display: flex;
  padding: 10px 15px;
}

.activity-item {
  flex: 1;
}

.activity-item img {
  width: 100%;
  height: 80px;
  border-radius: 8px;
}

.seckill-section {
  margin-top: 10px;
  background-color: #fff;
  padding: 10px 15px;
}

.section-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.section-title {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.more {
  margin-left: auto;
  font-size: 12px;
  color: #999;
}

.seckill-products {
  display: flex;
  overflow-x: auto;
}

.seckill-product {
  margin-right: 10px;
  text-align: center;
}

.seckill-price {
  display: block;
  color: #f00;
  font-weight: bold;
}

.original-price {
  display: block;
  color: #999;
  font-size: 12px;
  text-decoration: line-through;
}

.recommend-section {
  margin-top: 10px;
  background-color: #fff;
  padding: 10px 15px;
}

.product-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.product-item {
  width: 49%;
  margin-bottom: 10px;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.product-detail {
  padding: 10px;
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
  margin-top: 5px;
}

.current-price {
  color: #f00;
  font-weight: bold;
}

.product-sales {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
}
</style>
