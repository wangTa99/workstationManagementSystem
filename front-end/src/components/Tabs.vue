<template>
  <div class="tabs-card">
    <van-tabs
      v-model="activeTab"
      :type="type"
      :color="color"
      :sticky="sticky"
      :animated="animated"
      @change="handleChange"
    >
      <van-tab
        v-for="(tab, index) in tabs"
        :key="index"
        :title="tab.title"
        :name="tab.name"
        :disabled="tab.disabled"
      >
        <slot :name="tab.name || index">
          <div class="tab-content">{{ tab.content }}</div>
        </slot>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
/**
 * 标签页组件
 */
export default {
  name: 'TabsCard',

  props: {
    // 标签页数据
    tabs: {
      type: Array,
      default: () => []
    },
    // 当前选中的标签
    value: {
      type: [String, Number],
      default: 0
    },
    // 类型
    type: {
      type: String,
      default: 'line'
    },
    // 颜色
    color: {
      type: String,
      default: '#1989fa'
    },
    // 是否粘性定位
    sticky: {
      type: Boolean,
      default: false
    },
    // 是否开启切换动画
    animated: {
      type: Boolean,
      default: false
    }
  },

  computed: {
    activeTab: {
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
.tabs-card {
  background-color: #fff;
}

.tab-content {
  padding: 15px;
}
</style>
