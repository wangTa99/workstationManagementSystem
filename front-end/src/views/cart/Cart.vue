<template>
  <div class="cart-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="购物车" fixed placeholder>
      <template #right>
        <span v-if="!isEdit" @click="toggleEdit">{{ isAllSelected ? '完成' : '编辑' }}</span>
        <span v-else @click="toggleEdit">完成</span>
      </template>
    </van-nav-bar>

    <!-- 购物车列表 -->
    <div class="cart-content">
      <div v-if="cartList.length > 0">
        <!-- 购物车商品 -->
        <div class="cart-item" v-for="item in cartList" :key="item.id">
          <van-checkbox
            :value="item.selected"
            @change="handleSelectChange(item.id, $event)"
          />
          <van-image width="80" height="80" :src="item.productImage" />
          <div class="item-info">
            <span class="item-name">{{ item.productName }}</span>
            <span class="item-spec" v-if="item.specifications">{{ item.specifications }}</span>
            <div class="item-bottom">
              <span class="item-price">¥{{ item.price }}</span>
              <van-stepper
                :value="item.quantity"
                @change="handleQuantityChange(item.id, $event)"
                min="1"
                :max="item.stock"
              />
            </div>
          </div>
        </div>
      </div>

      <!-- 空购物车 -->
      <van-empty v-else description="购物车是空的">
        <van-button round type="primary" @click="goToHome">去逛逛</van-button>
      </van-empty>
    </div>

    <!-- 底部结算栏 -->
    <div class="cart-footer" v-if="cartList.length > 0">
      <div class="footer-left">
        <van-checkbox
          :value="isAllSelected"
          @change="handleSelectAllChange"
        >
          全选
        </van-checkbox>
        <span class="total-text">
          合计：<span class="total-price">¥{{ totalPrice }}</span>
        </span>
      </div>
      <van-button
        v-if="!isEdit"
        type="primary"
        size="large"
        :disabled="selectedCount === 0"
        @click="goToCheckout"
      >
        结算 ({{ selectedCount }})
      </van-button>
      <van-button
        v-else
        type="danger"
        size="large"
        :disabled="selectedCount === 0"
        @click="handleDelete"
      >
        删除
      </van-button>
    </div>
  </div>
</template>

<script>
/**
 * 购物车页面组件
 * 展示购物车商品列表，支持选择、修改数量、删除等操作
 */
export default {
  name: 'Cart',

  data() {
    return {
      // 购物车列表
      cartList: [],
      // 是否处于编辑状态
      isEdit: false,
      // 加载状态
      loading: false
    }
  },

  computed: {
    /**
     * 计算是否全选
     */
    isAllSelected() {
      return this.cartList.length > 0 && this.cartList.every(item => item.selected)
    },

    /**
     * 计算已选商品数量
     */
    selectedCount() {
      return this.cartList.filter(item => item.selected).length
    },

    /**
     * 计算商品总价
     */
    totalPrice() {
      const total = this.cartList
        .filter(item => item.selected)
        .reduce((sum, item) => sum + item.price * item.quantity, 0)
      return total.toFixed(2)
    }
  },

  created() {
    // 获取购物车列表
    this.getCartList()
  },

  methods: {
    /**
     * 获取购物车列表
     */
    getCartList() {
      this.loading = true
      this.$api.getCartList().then(res => {
        if (res.code === 200) {
          this.cartList = res.data
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 切换编辑状态
     */
    toggleEdit() {
      this.isEdit = !this.isEdit
    },

    /**
     * 处理单选框变化
     */
    handleSelectChange(cartId, selected) {
      this.$api.updateCartSelected(cartId, selected ? 1 : 0).then(res => {
        if (res.code === 200) {
          const item = this.cartList.find(item => item.id === cartId)
          if (item) {
            item.selected = selected
          }
        }
      })
    },

    /**
     * 处理全选变化
     */
    handleSelectAllChange(selected) {
      this.$api.selectAllCart(selected ? 1 : 0).then(res => {
        if (res.code === 200) {
          this.cartList.forEach(item => {
            item.selected = selected
          })
        }
      })
    },

    /**
     * 处理商品数量变化
     */
    handleQuantityChange(cartId, quantity) {
      this.$api.updateCartQuantity(cartId, quantity).then(res => {
        if (res.code === 200) {
          const item = this.cartList.find(item => item.id === cartId)
          if (item) {
            item.quantity = quantity
          }
        }
      })
    },

    /**
     * 删除选中商品
     */
    handleDelete() {
      const selectedIds = this.cartList
        .filter(item => item.selected)
        .map(item => item.id)

      if (selectedIds.length === 0) {
        this.$toast('请选择要删除的商品')
        return
      }

      this.$dialog.confirm({
        title: '提示',
        message: '确定要删除选中的商品吗？'
      }).then(() => {
        this.$api.deleteCartItems(selectedIds).then(res => {
          if (res.code === 200) {
            this.$toast.success('删除成功')
            this.getCartList()
          }
        })
      })
    },

    /**
     * 跳转到结算页面
     */
    goToCheckout() {
      const selectedItems = this.cartList.filter(item => item.selected)
      if (selectedItems.length === 0) {
        this.$toast('请选择要结算的商品')
        return
      }
      this.$router.push({
        path: '/checkout',
        query: { items: JSON.stringify(selectedItems) }
      })
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
.cart-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 60px;
}

.cart-content {
  padding: 10px;
  margin-top: 46px;
}

.cart-item {
  display: flex;
  align-items: center;
  padding: 15px;
  margin-bottom: 10px;
  background-color: #fff;
  border-radius: 8px;
}

.item-info {
  flex: 1;
  margin-left: 10px;
}

.item-name {
  font-size: 14px;
  color: #333;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.item-spec {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
  display: block;
}

.item-bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.item-price {
  color: #f00;
  font-weight: bold;
}

.cart-footer {
  position: fixed;
  bottom: 50px;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  background-color: #fff;
  border-top: 1px solid #eee;
}

.footer-left {
  display: flex;
  align-items: center;
}

.total-text {
  margin-left: 15px;
  font-size: 14px;
}

.total-price {
  color: #f00;
  font-weight: bold;
  font-size: 16px;
}
</style>
