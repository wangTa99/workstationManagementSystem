<template>
  <div class="comment-list-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="我的评价" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 评价列表 -->
    <div class="comment-list">
      <div
        class="comment-item"
        v-for="comment in comments"
        :key="comment.id"
      >
        <div class="comment-header">
          <van-image round width="40" height="40" :src="comment.userAvatar" />
          <div class="user-info">
            <span class="user-name">{{ comment.userName }}</span>
            <van-rate v-model="comment.rating" readonly size="12" />
          </div>
          <span class="comment-time">{{ comment.createTime }}</span>
        </div>
        <div class="comment-content">{{ comment.content }}</div>
        <div class="comment-images" v-if="comment.images">
          <van-image
            v-for="(img, idx) in comment.images.split(',')"
            :key="idx"
            width="80"
            height="80"
            :src="img"
            @click="previewImage(comment.images.split(','), idx)"
          />
        </div>
        <div class="comment-product" v-if="comment.productName">
          <span class="product-name">{{ comment.productName }}</span>
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && comments.length === 0"
      description="暂无评价"
      :showButton="true"
      buttonText="去评价"
      @click="goToComment"
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
 * 我的评价页面组件
 */
export default {
  name: 'CommentList',

  components: {
    EmptyState
  },

  data() {
    return {
      // 评价列表
      comments: [],
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
    this.getCommentList()
  },

  methods: {
    /**
     * 获取评价列表
     */
    getCommentList() {
      if (this.loading) return
      this.loading = true

      this.$api.getMyCommentList({
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }).then(res => {
        if (res.code === 200) {
          if (this.pageNum === 1) {
            this.comments = res.data.list
          } else {
            this.comments.push(...res.data.list)
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
      this.getCommentList()
    },

    /**
     * 预览图片
     */
    previewImage(images, index) {
      this.$imagePreview({
        images: images,
        startPosition: index
      })
    },

    /**
     * 去评价
     */
    goToComment() {
      this.$router.push('/order')
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
.comment-list-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.comment-list {
  padding: 10px;
}

.comment-item {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 10px;
}

.comment-header {
  display: flex;
  align-items: center;
}

.user-info {
  flex: 1;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
}

.user-name {
  font-size: 14px;
  color: #333;
  margin-bottom: 5px;
}

.comment-time {
  font-size: 12px;
  color: #999;
}

.comment-content {
  margin-top: 10px;
  font-size: 14px;
  color: #333;
  line-height: 1.5;
}

.comment-images {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.comment-product {
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid #f5f5f5;
}

.product-name {
  font-size: 12px;
  color: #999;
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
