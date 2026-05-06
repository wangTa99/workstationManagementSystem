<template>
  <div class="collect-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="我的收藏" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 收藏列表 -->
    <div class="collect-list" v-if="collects.length > 0">
      <div
        class="collect-item"
        v-for="item in collects"
        :key="item.id"
        @click="goToProduct(item.objectId)"
      >
        <van-image width="80" height="80" :src="item.objectImage" />
        <div class="item-info">
          <span class="item-name">{{ item.objectName }}</span>
          <div class="item-bottom">
            <span class="item-price">¥{{ item.price }}</span>
            <van-button size="small" type="danger" @click.stop="handleCancel(item)">
              取消收藏
            </van-button>
          </div>
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && collects.length === 0"
      description="暂无收藏"
      :showButton="true"
      buttonText="去逛逛"
      @click="goToHome"
    />

    <!-- 加载状态 -->
    <van-loading v-if="loading" class="loading" />

    <!-- 分页加载 -->
    <div v-if="hasMore && !loading" class="load-more" @click="loadMore">
      加载更多
    </div>
  </div>
</template>

<script>
/**
 * 我的收藏页面组件
 */
export default {
  name: 'Collect',

  components: {
    EmptyState
  },

  data() {
    return {
      // 收藏列表
      collects: [],
      // 加载状态
      loading: false,
      // 是否有更多数据
      hasMore: true,
      // 分页参数
      pageNum: 1,
      pageSize: 10
    }
  },

  created() {
    this.getCollectList()
  },

  methods: {
    /**
     * 获取收藏列表
     */
    getCollectList() {
      if (this.loading) return
      this.loading = true

      this.$api.getCollectList({
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }).then(res => {
        if (res.code === 200) {
          if (this.pageNum === 1) {
            this.collects = res.data.list
          } else {
            this.collects.push(...res.data.list)
          }
          this.hasMore = res.data.hasMore
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 加载更多
     */
    loadMore() {
      this.pageNum++
      this.getCollectList()
    },

    /**
     * 取消收藏
     */
    handleCancel(item) {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要取消收藏吗？'
      }).then(() => {
        this.$api.cancelCollect(item.objectId).then(res => {
          if (res.code === 200) {
            this.$toast.success('已取消收藏')
            const index = this.collects.findIndex(c => c.id === item.id)
            if (index > -1) {
              this.collects.splice(index, 1)
            }
          }
        })
      })
    },

    /**
     * 跳转到商品详情
     */
    goToProduct(productId) {
      this.$router.push(`/product/${productId}`)
    },

    /**
     * 跳转到首页
     */
    goToHome() {
      this.$router.push('/')
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1)
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
.collect-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.collect-list {
  padding: 10px;
}

.collect-item {
  display: flex;
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  margin-bottom: 10px;
}

.item-info {
  flex: 1;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.item-name {
  font-size: 14px;
  color: #333;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
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

.loading {
  text-align: center;
  padding: 20px;
}

.load-more {
  text-align: center;
  padding: 15px;
  color: #666;
  font-size: 14px;
}
</style>
