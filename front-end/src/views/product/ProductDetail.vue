<template>
  <div class="product-detail-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar
      title="商品详情"
      fixed
      placeholder
      left-arrow
      @click-left="goBack"
    >
      <template #right>
        <van-icon name="share" size="20" @click="handleShare" />
      </template>
    </van-nav-bar>

    <!-- 商品图片轮播 -->
    <van-swipe class="product-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item v-for="(image, index) in productImages" :key="index">
        <img :src="image" />
      </van-swipe-item>
    </van-swipe>

    <!-- 商品信息 -->
    <div class="product-info">
      <div class="price-row">
        <span class="current-price">¥{{ product.price }}</span>
        <span class="original-price" v-if="product.originalPrice">
          ¥{{ product.originalPrice }}
        </span>
        <van-tag type="danger" v-if="product.discountRate">
          {{ product.discountRate }}折
        </van-tag>
      </div>
      <h1 class="product-name">{{ product.productName }}</h1>
      <div class="product-desc">{{ product.summary }}</div>
    </div>

    <!-- 选择规格 -->
    <div class="spec-section" @click="showSpecPopup = true">
      <span class="section-title">选择规格</span>
      <span class="selected-spec" v-if="selectedSpec">
        {{ selectedSpec }}
      </span>
      <van-icon name="arrow" />
    </div>

    <!-- 商品详情 -->
    <div class="detail-section">
      <div class="section-header">
        <span class="section-title">商品详情</span>
      </div>
      <div class="detail-content" v-html="product.description"></div>
    </div>

    <!-- 评价列表 -->
    <div class="comment-section">
      <div class="section-header">
        <span class="section-title">商品评价</span>
        <span class="more" @click="goToCommentList">
          更多
          <van-icon name="arrow" />
        </span>
      </div>
      <div class="comment-list" v-if="comments.length > 0">
        <div class="comment-item" v-for="comment in comments" :key="comment.id">
          <div class="comment-header">
            <van-image round width="30" height="30" :src="comment.userAvatar" />
            <span class="user-name">{{ comment.userName }}</span>
            <van-rate v-model="comment.rating" readonly size="12" />
          </div>
          <div class="comment-content">{{ comment.content }}</div>
          <div class="comment-images" v-if="comment.images">
            <van-image
              v-for="(img, idx) in comment.images.split(',')"
              :key="idx"
              width="60"
              height="60"
              :src="img"
            />
          </div>
          <div class="comment-time">{{ comment.createTime }}</div>
        </div>
      </div>
      <van-empty v-else description="暂无评价" />
    </div>

    <!-- 底部操作栏 -->
    <div class="bottom-action">
      <div class="action-icons">
        <div class="icon-item" @click="goToHome">
          <van-icon name="shop-o" />
          <span>首页</span>
        </div>
        <div class="icon-item" @click="handleCollect">
          <van-icon :name="isCollected ? 'star' : 'star-o'" :color="isCollected ? '#ff6b6b' : ''" />
          <span>{{ isCollected ? '已收藏' : '收藏' }}</span>
        </div>
        <div class="icon-item" @click="goToCart">
          <van-icon name="shopping-cart-o" :badge="cartCount" />
          <span>购物车</span>
        </div>
      </div>
      <div class="action-buttons">
        <van-button type="warning" @click="handleAddToCart">加入购物车</van-button>
        <van-button type="danger" @click="handleBuyNow">立即购买</van-button>
      </div>
    </div>

    <!-- 规格选择弹窗 -->
    <van-popup v-model="showSpecPopup" position="bottom" round>
      <div class="spec-popup">
        <div class="spec-header">
          <van-image width="80" height="80" :src="product.mainImage" />
          <div class="spec-info">
            <span class="spec-price">¥{{ product.price }}</span>
            <span class="spec-stock">库存：{{ product.stock }}</span>
          </div>
        </div>
        <div class="spec-content">
          <div class="spec-item" v-for="spec in specifications" :key="spec.specName">
            <div class="spec-name">{{ spec.specName }}</div>
            <div class="spec-values">
              <span
                v-for="value in spec.specValues"
                :key="value"
                :class="{ active: selectedSpecs[spec.specName] === value }"
                @click="selectSpec(spec.specName, value)"
              >
                {{ value }}
              </span>
            </div>
          </div>
        </div>
        <div class="spec-quantity">
          <span>数量</span>
          <van-stepper v-model="quantity" min="1" :max="product.stock" />
        </div>
        <van-button type="primary" block @click="confirmSpec">确定</van-button>
      </div>
    </van-popup>
  </div>
</template>

<script>
/**
 * 商品详情页面组件
 * 展示商品的详细信息，支持收藏、加购物车、购买等操作
 */
export default {
  name: 'ProductDetail',

  data() {
    return {
      // 商品ID
      productId: null,
      // 商品信息
      product: {},
      // 商品图片列表
      productImages: [],
      // 评价列表
      comments: [],
      // 是否已收藏
      isCollected: false,
      // 购物车数量
      cartCount: 0,
      // 是否显示规格选择弹窗
      showSpecPopup: false,
      // 规格列表
      specifications: [],
      // 已选规格
      selectedSpecs: {},
      // 选中的规格字符串
      selectedSpec: '',
      // 购买数量
      quantity: 1
    }
  },

  created() {
    // 获取商品ID
    this.productId = this.$route.params.id
    // 获取商品详情
    this.getProductDetail()
    // 获取商品评价
    this.getComments()
    // 获取购物车数量
    this.getCartCount()
    // 检查收藏状态
    this.checkCollectStatus()
  },

  methods: {
    /**
     * 获取商品详情
     */
    getProductDetail() {
      this.$api.getProductDetail(this.productId).then(res => {
        if (res.code === 200) {
          this.product = res.data
          // 处理商品图片
          if (this.product.images) {
            this.productImages = this.product.images.split(',')
          } else {
            this.productImages = [this.product.mainImage]
          }
          // 处理规格
          if (this.product.specifications) {
            try {
              this.specifications = JSON.parse(this.product.specifications)
            } catch (e) {
              this.specifications = []
            }
          }
        }
      })
    },

    /**
     * 获取商品评价列表
     */
    getComments() {
      this.$api.getProductComments({ productId: this.productId, limit: 3 }).then(res => {
        if (res.code === 200) {
          this.comments = res.data
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
     * 检查收藏状态
     */
    checkCollectStatus() {
      this.$api.checkCollect(this.productId).then(res => {
        if (res.code === 200) {
          this.isCollected = res.data
        }
      })
    },

    /**
     * 选择规格
     */
    selectSpec(specName, value) {
      this.$set(this.selectedSpecs, specName, value)
      this.updateSelectedSpec()
    },

    /**
     * 更新已选规格字符串
     */
    updateSelectedSpec() {
      const specs = Object.entries(this.selectedSpecs)
        .map(([key, value]) => `${key}: ${value}`)
        .join(', ')
      this.selectedSpec = specs
    },

    /**
     * 确认规格选择
     */
    confirmSpec() {
      this.showSpecPopup = false
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1)
    },

    /**
     * 跳转首页
     */
    goToHome() {
      this.$router.push('/')
    },

    /**
     * 跳转购物车
     */
    goToCart() {
      this.$router.push('/cart')
    },

    /**
     * 跳转评价列表
     */
    goToCommentList() {
      this.$router.push(`/comment/list/${this.productId}`)
    },

    /**
     * 处理收藏
     */
    handleCollect() {
      this.$api.toggleCollect(this.productId).then(res => {
        if (res.code === 200) {
          this.isCollected = !this.isCollected
          this.$toast.success(this.isCollected ? '收藏成功' : '取消收藏')
        }
      })
    },

    /**
     * 处理分享
     */
    handleShare() {
      // 实现分享功能
      this.$toast('分享功能开发中')
    },

    /**
     * 添加到购物车
     */
    handleAddToCart() {
      if (this.specifications.length > 0 && !this.selectedSpec) {
        this.showSpecPopup = true
        return
      }

      this.$api.addToCart({
        productId: this.productId,
        quantity: this.quantity,
        specifications: this.selectedSpec
      }).then(res => {
        if (res.code === 200) {
          this.$toast.success('添加成功')
          this.getCartCount()
        } else {
          this.$toast.fail(res.message)
        }
      })
    },

    /**
     * 立即购买
     */
    handleBuyNow() {
      if (this.specifications.length > 0 && !this.selectedSpec) {
        this.showSpecPopup = true
        return
      }

      // 跳转到结算页面
      this.$router.push({
        path: '/checkout',
        query: {
          items: JSON.stringify([{
            productId: this.productId,
            productName: this.product.productName,
            productImage: this.product.mainImage,
            price: this.product.price,
            quantity: this.quantity,
            specifications: this.selectedSpec
          }])
        }
      })
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
.product-detail-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 60px;
}

.product-swipe {
  height: 375px;
}

.product-swipe img {
  width: 100%;
  height: 100%;
}

.product-info {
  background-color: #fff;
  padding: 15px;
}

.price-row {
  display: flex;
  align-items: center;
}

.current-price {
  font-size: 24px;
  color: #f00;
  font-weight: bold;
}

.original-price {
  font-size: 14px;
  color: #999;
  text-decoration: line-through;
  margin-left: 10px;
}

.product-name {
  font-size: 16px;
  font-weight: bold;
  margin: 10px 0;
}

.product-desc {
  font-size: 14px;
  color: #666;
  line-height: 1.5;
}

.spec-section {
  display: flex;
  align-items: center;
  background-color: #fff;
  padding: 15px;
  margin-top: 10px;
}

.section-title {
  font-size: 14px;
  font-weight: bold;
}

.selected-spec {
  flex: 1;
  margin-left: 10px;
  color: #666;
  font-size: 14px;
}

.detail-section {
  background-color: #fff;
  margin-top: 10px;
  padding: 15px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.detail-content {
  font-size: 14px;
  line-height: 1.6;
  color: #333;
}

.comment-section {
  background-color: #fff;
  margin-top: 10px;
  padding: 15px;
}

.comment-list {
  margin-top: 10px;
}

.comment-item {
  padding: 10px 0;
  border-bottom: 1px solid #f5f5f5;
}

.comment-header {
  display: flex;
  align-items: center;
}

.user-name {
  margin-left: 10px;
  font-size: 14px;
}

.comment-content {
  margin-top: 10px;
  font-size: 14px;
  color: #333;
}

.comment-images {
  display: flex;
  gap: 5px;
  margin-top: 10px;
}

.comment-time {
  margin-top: 10px;
  font-size: 12px;
  color: #999;
}

.bottom-action {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  height: 50px;
  background-color: #fff;
  border-top: 1px solid #eee;
}

.action-icons {
  display: flex;
  width: 120px;
}

.icon-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 10px;
}

.action-buttons {
  flex: 1;
  display: flex;
}

.action-buttons .van-button {
  flex: 1;
  border-radius: 0;
}

.spec-popup {
  padding: 15px;
}

.spec-header {
  display: flex;
}

.spec-info {
  margin-left: 10px;
  display: flex;
  flex-direction: column;
}

.spec-price {
  color: #f00;
  font-weight: bold;
}

.spec-stock {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
}

.spec-content {
  margin-top: 20px;
}

.spec-item {
  margin-bottom: 15px;
}

.spec-name {
  font-size: 14px;
  margin-bottom: 10px;
}

.spec-values span {
  display: inline-block;
  padding: 5px 15px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 12px;
}

.spec-values span.active {
  border-color: #f00;
  color: #f00;
  background-color: #fff0f0;
}

.spec-quantity {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px 0;
}
</style>
