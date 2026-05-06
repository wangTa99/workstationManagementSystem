<template>
  <div class="swipe-card">
    <van-swipe class="swipe" :autoplay="autoplay" indicator-color="white">
      <van-swipe-item v-for="(image, index) in images" :key="index">
        <img :src="image" @click="handleClick(index)" />
      </van-swipe-item>
    </van-swipe>
    <div class="swipe-indicator" v-if="showIndicator">
      <span
        v-for="(image, index) in images"
        :key="index"
        :class="{ active: index === currentIndex }"
        @click="goTo(index)"
      />
    </div>
  </div>
</template>

<script>
/**
 * 轮播图组件
 */
export default {
  name: 'SwipeCard',

  props: {
    // 图片列表
    images: {
      type: Array,
      default: () => []
    },
    // 自动播放间隔
    autoplay: {
      type: Number,
      default: 3000
    },
    // 是否显示指示器
    showIndicator: {
      type: Boolean,
      default: true
    }
  },

  data() {
    return {
      currentIndex: 0
    }
  },

  methods: {
    handleClick(index) {
      this.$emit('click', index)
    },

    goTo(index) {
      this.currentIndex = index
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
.swipe-card {
  position: relative;
}

.swipe {
  height: 150px;
}

.swipe img {
  width: 100%;
  height: 100%;
}

.swipe-indicator {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 5px;
}

.swipe-indicator span {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.5);
  cursor: pointer;
}

.swipe-indicator span.active {
  background-color: #fff;
}
</style>
