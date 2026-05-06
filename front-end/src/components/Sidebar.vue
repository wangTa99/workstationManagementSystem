<template>
  <div class="sidebar-card">
    <van-sidebar v-model="activeKey" @change="handleChange">
      <van-sidebar-item
        v-for="(item, index) in items"
        :key="index"
        :title="item.title"
        :disabled="item.disabled"
        :badge="item.badge"
      />
    </van-sidebar>
  </div>
</template>

<script>
/**
 * 侧边栏组件
 */
export default {
  name: 'SidebarCard',

  props: {
    // 当前选中索引
    value: {
      type: [String, Number],
      default: 0
    },
    // 侧边栏数据
    items: {
      type: Array,
      default: () => []
    }
  },

  computed: {
    activeKey: {
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
.sidebar-card {
  background-color: #fff;
  height: 100%;
}
</style>
