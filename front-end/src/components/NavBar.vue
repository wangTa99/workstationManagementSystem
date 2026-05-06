<template>
  <div class="navbar-card">
    <van-nav-bar
      :title="title"
      :left-text="leftText"
      :right-text="rightText"
      :left-arrow="leftArrow"
      :fixed="fixed"
      :placeholder="placeholder"
      :safe-area-inset-top="safeAreaInsetTop"
      @click-left="handleClickLeft"
      @click-right="handleClickRight"
    >
      <template #right>
        <slot name="right"></slot>
      </template>
      <template #left>
        <slot name="left"></slot>
      </template>
    </van-nav-bar>
  </div>
</template>

<script>
/**
 * 导航栏组件
 */
export default {
  name: 'NavBarCard',

  props: {
    // 标题
    title: {
      type: String,
      default: ''
    },
    // 左侧文本
    leftText: {
      type: String,
      default: ''
    },
    // 右侧文本
    rightText: {
      type: String,
      default: ''
    },
    // 是否显示左侧箭头
    leftArrow: {
      type: Boolean,
      default: true
    },
    // 是否固定顶部
    fixed: {
      type: Boolean,
      default: true
    },
    // 是否在顶部生成占位元素
    placeholder: {
      type: Boolean,
      default: true
    },
    // 是否开启安全区适配
    safeAreaInsetTop: {
      type: Boolean,
      default: true
    }
  },

  methods: {
    handleClickLeft() {
      this.$emit('click-left')
    },

    handleClickRight() {
      this.$emit('click-right')
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
.navbar-card {
  background-color: #fff;
}
</style>
