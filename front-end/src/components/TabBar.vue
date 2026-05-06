<template>
  <div class="tabbar-card">
    <van-tabbar
      v-model="active"
      :fixed="fixed"
      :placeholder="placeholder"
      :route="route"
      :safe-area-inset-bottom="safeAreaInsetBottom"
      @change="handleChange"
    >
      <van-tabbar-item
        v-for="(item, index) in tabs"
        :key="index"
        :to="item.to"
        :icon="item.icon"
        :dot="item.dot"
        :badge="item.badge"
        :info="item.info"
      >
        {{ item.text }}
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
/**
 * 底部标签栏组件
 */
export default {
  name: 'TabBarCard',

  props: {
    // 当前选中标签
    value: {
      type: [String, Number],
      default: 0
    },
    // 标签数据
    tabs: {
      type: Array,
      default: () => []
    },
    // 是否固定底部
    fixed: {
      type: Boolean,
      default: true
    },
    // 是否在底部生成占位元素
    placeholder: {
      type: Boolean,
      default: true
    },
    // 是否开启路由模式
    route: {
      type: Boolean,
      default: false
    },
    // 是否开启安全区适配
    safeAreaInsetBottom: {
      type: Boolean,
      default: true
    }
  },

  computed: {
    active: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  },

  methods: {
    handleChange(index) {
      this.$emit('change', index)
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
.tabbar-card {
  background-color: #fff;
}
</style>
