<template>
  <div class="empty-state">
    <van-empty :description="description" :image="image">
      <slot>
        <van-button v-if="showButton" round type="primary" @click="handleClick">
          {{ buttonText }}
        </van-button>
      </slot>
    </van-empty>
  </div>
</template>

<script>
/**
 * 空状态组件
 */
export default {
  name: 'EmptyState',

  props: {
    // 描述文字
    description: {
      type: String,
      default: '暂无数据'
    },
    // 图片
    image: {
      type: String,
      default: 'default'
    },
    // 是否显示按钮
    showButton: {
      type: Boolean,
      default: false
    },
    // 按钮文字
    buttonText: {
      type: String,
      default: '返回首页'
    }
  },

  methods: {
    /**
     * 处理按钮点击
     */
    handleClick() {
      this.$emit('click')
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
.empty-state {
  padding: 50px 0;
}
</style>
