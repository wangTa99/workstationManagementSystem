<template>
  <div class="category-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="分类" fixed placeholder />

    <!-- 分类内容区 -->
    <div class="category-content">
      <!-- 左侧分类列表 -->
      <div class="category-left">
        <van-sidebar v-model="activeCategory" @change="handleCategoryChange">
          <van-sidebar-item
            v-for="category in categories"
            :key="category.id"
            :title="category.categoryName"
          />
        </van-sidebar>
      </div>

      <!-- 右侧子分类和商品 -->
      <div class="category-right">
        <!-- 子分类 -->
        <div class="sub-category" v-if="subCategories.length > 0">
          <div
            class="sub-category-item"
            v-for="sub in subCategories"
            :key="sub.id"
            @click="goToSubCategory(sub.id)"
          >
            <van-image width="60" height="60" :src="sub.icon" />
            <span>{{ sub.categoryName }}</span>
          </div>
        </div>

        <!-- 商品列表 -->
        <div class="product-list">
          <div
            class="product-item"
            v-for="product in products"
            :key="product.id"
            @click="goToProductDetail(product.id)"
          >
            <van-image width="80" height="80" :src="product.mainImage" />
            <div class="product-info">
              <span class="product-name">{{ product.productName }}</span>
              <div class="product-price">
                <span class="current-price">¥{{ product.price }}</span>
                <span class="original-price" v-if="product.originalPrice">
                  ¥{{ product.originalPrice }}
                </span>
              </div>
            </div>
            <van-button
              type="primary"
              size="small"
              @click.stop="addToCart(product)"
            >
              加入购物车
            </van-button>
          </div>
        </div>

        <!-- 空状态 -->
        <van-empty
          v-if="products.length === 0 && !loading"
          description="暂无商品"
        />
      </div>
    </div>

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
 * 分类页面组件
 * 展示商品分类列表和分类下的商品
 */
export default {
  name: 'Category',

  data() {
    return {
      // 当前选中的标签
      activeTab: 1,
      // 当前选中的分类索引
      activeCategory: 0,
      // 一级分类列表
      categories: [],
      // 子分类列表
      subCategories: [],
      // 商品列表
      products: [],
      // 购物车数量
      cartCount: 0,
      // 加载状态
      loading: false,
      // 分页参数
      pageNum: 1,
      pageSize: 10,
      // 是否有更多数据
      hasMore: true
    }
  },

  created() {
    // 获取分类数据
    this.getCategories()
    // 获取购物车数量
    this.getCartCount()
  },

  mounted() {
    // 监听滚动事件，实现下拉加载更多
    this.$nextTick(() => {
      this.bindScrollListener()
    })
  },

  beforeDestroy() {
    // 移除滚动监听
    this.removeScrollListener()
  },

  methods: {
    /**
     * 获取一级分类列表
     */
    getCategories() {
      this.$api.getCategoryTree().then(res => {
        if (res.code === 200) {
          this.categories = res.data
          // 获取第一个分类的子分类和商品
          if (this.categories.length > 0) {
            this.getSubCategories(this.categories[0].id)
          }
        }
      })
    },

    /**
     * 获取子分类
     */
    getSubCategories(parentId) {
      this.$api.getChildCategories(parentId).then(res => {
        if (res.code === 200) {
          this.subCategories = res.data
          // 获取子分类下的商品
          if (this.subCategories.length > 0) {
            this.getProducts(this.subCategories[0].id)
          }
        }
      })
    },

    /**
     * 获取商品列表
     */
    getProducts(categoryId) {
      if (this.loading) return
      this.loading = true

      this.$api
        .getProductList({
          categoryId: categoryId,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        })
        .then(res => {
          if (res.code === 200) {
            if (this.pageNum === 1) {
              this.products = res.data.list
            } else {
              this.products.push(...res.data.list)
            }
            this.hasMore = res.data.hasMore
          }
        })
        .finally(() => {
          this.loading = false
        })
    },

    /**
     * 处理分类切换
     */
    handleCategoryChange(index) {
      this.activeCategory = index
      this.pageNum = 1
      this.products = []
      this.hasMore = true

      const category = this.categories[index]
      if (category) {
        this.getSubCategories(category.id)
      }
    },

    /**
     * 跳转到子分类页面
     */
    goToSubCategory(subCategoryId) {
      this.$router.push(`/category/${subCategoryId}`)
    },

    /**
     * 跳转到商品详情页
     */
    goToProductDetail(productId) {
      this.$router.push(`/product/${productId}`)
    },

    /**
     * 添加商品到购物车
     */
    addToCart(product) {
      this.$api
        .addToCart({
          productId: product.id,
          quantity: 1
        })
        .then(res => {
          if (res.code === 200) {
            this.$toast.success('已加入购物车')
            this.getCartCount()
          } else {
            this.$toast.fail(res.message)
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
     * 绑定滚动监听
     */
    bindScrollListener() {
      const scrollElement = document.querySelector('.category-right')
      if (scrollElement) {
        scrollElement.addEventListener('scroll', this.handleScroll)
      }
    },

    /**
     * 移除滚动监听
     */
    removeScrollListener() {
      const scrollElement = document.querySelector('.category-right')
      if (scrollElement) {
        scrollElement.removeEventListener('scroll', this.handleScroll)
      }
    },

    /**
     * 处理滚动事件
     */
    handleScroll() {
      const scrollElement = document.querySelector('.category-right')
      if (!scrollElement) return

      const { scrollTop, scrollHeight, clientHeight } = scrollElement
      if (scrollTop + clientHeight >= scrollHeight - 50 && this.hasMore && !this.loading) {
        this.pageNum++
        this.getProducts(this.subCategories[0]?.id)
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
.category-container {
  height: 100vh;
  background-color: #f5f5f5;
}

.category-content {
  display: flex;
  height: calc(100vh - 96px);
  padding-top: 46px;
}

.category-left {
  width: 80px;
  flex-shrink: 0;
  background-color: #f5f5f5;
}

.category-right {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  background-color: #fff;
}

.sub-category {
  display: flex;
  flex-wrap: wrap;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.sub-category-item {
  width: 33.33%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 15px;
}

.sub-category-item span {
  margin-top: 5px;
  font-size: 12px;
  color: #333;
}

.product-list {
  padding-top: 10px;
}

.product-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #f5f5f5;
}

.product-info {
  flex: 1;
  margin-left: 10px;
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

.original-price {
  color: #999;
  font-size: 12px;
  text-decoration: line-through;
  margin-left: 5px;
}
</style>
