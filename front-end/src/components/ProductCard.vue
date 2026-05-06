<template>
  <div class="product-card" @click="handleClick">
    <van-image width="100%" height="160" :src="product.mainImage" />
    <div class="product-info">
      <h3 class="product-name">{{ product.productName }}</h3>
      <div class="product-desc">{{ product.summary }}</div>
      <div class="product-price">
        <span class="current-price">¥{{ product.price }}</span>
        <span class="original-price" v-if="product.originalPrice">
          ¥{{ product.originalPrice }}
        </span>
      </div>
      <div class="product-footer">
        <span class="sales-count">已售{{ product.salesCount }}件</span>
        <van-icon name="shopping-cart-o" @click.stop="handleAddCart" />
      </div>
    </div>
    <van-tag v-if="product.isHot" type="danger" class="hot-tag">热卖</van-tag>
    <van-tag v-if="product.isRecommend" type="warning" class="recommend-tag">推荐</van-tag>
  </div>
</template>

<script>
/**
 * 商品卡片组件
 * 用于展示单个商品的基本信息
 */
export default {
  name: 'ProductCard',

  props: {
    // 商品信息
    product: {
      type: Object,
      required: true
    }
  },

  methods: {
    /**
     * 处理点击事件
     */
    handleClick() {
      this.$emit('click', this.product)
      this.$router.push(`/product/${this.product.id}`)
    },

    /**
     * 处理添加购物车
     */
    handleAddCart() {
      this.$emit('add-cart', this.product)
      this.$api.addToCart({
        productId: this.product.id,
        quantity: 1
      }).then(res => {
        if (res.code === 200) {
          this.$toast.success('已加入购物车')
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
.product-card {
  position: relative;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 10px;
}

.product-info {
  padding: 10px;
}

.product-name {
  font-size: 14px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product-desc {
  font-size: 12px;
  color: #999;
  margin-bottom: 8px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product-price {
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}

.current-price {
  font-size: 16px;
  color: #f00;
  font-weight: bold;
}

.original-price {
  font-size: 12px;
  color: #999;
  text-decoration: line-through;
  margin-left: 8px;
}

.product-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.sales-count {
  font-size: 12px;
  color: #999;
}

.hot-tag,
.recommend-tag {
  position: absolute;
  top: 10px;
  left: 0;
}
</style>
