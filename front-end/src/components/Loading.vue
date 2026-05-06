<template>
  <div class="loading-overlay" v-if="visible">
    <van-loading type="spinner" :size="size" :color="color">
      <span v-if="text" class="loading-text">{{ text }}</span>
    </van-loading>
  </div>
</template>

<script>
/**
 * 加载组件
 */
export default {
  name: 'Loading',

  props: {
    // 是否显示
    visible: {
      type: Boolean,
      default: false
    },
    // 加载文案
    text: {
      type: String,
      default: ''
    },
    // 尺寸
    size: {
      type: String,
      default: '30px'
    },
    // 颜色
    color: {
      type: String,
      default: '#1989fa'
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
.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
}

.loading-text {
  color: #fff;
  font-size: 14px;
  margin-top: 10px;
}
</style>
