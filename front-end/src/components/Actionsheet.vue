<template>
  <div class="actionsheet-card">
    <van-action-sheet
      v-model="visible"
      :actions="actions"
      :cancel-text="cancelText"
      :description="description"
      :round="round"
      @select="handleSelect"
      @cancel="handleCancel"
    />
  </div>
</template>

<script>
/**
 * 动作面板组件
 */
export default {
  name: 'ActionsheetCard',

  props: {
    // 是否显示
    value: {
      type: Boolean,
      default: false
    },
    // 操作选项
    actions: {
      type: Array,
      default: () => []
    },
    // 取消按钮文本
    cancelText: {
      type: String,
      default: '取消'
    },
    // 描述
    description: {
      type: String,
      default: ''
    },
    // 是否圆角
    round: {
      type: Boolean,
      default: true
    }
  },

  computed: {
    visible: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  },

  methods: {
    handleSelect(action) {
      this.$emit('select', action)
    },

    handleCancel() {
      this.$emit('cancel')
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
</style>
