<template>
  <div class="popup-card">
    <van-popup
      v-model="visible"
      :position="position"
      :round="round"
      :closeable="closeable"
      :close-icon="closeIcon"
      :overlay="overlay"
      :transition="transition"
      @close="handleClose"
    >
      <div class="popup-content">
        <slot></slot>
      </div>
    </van-popup>
  </div>
</template>

<script>
/**
 * 弹出层组件
 */
export default {
  name: 'PopupCard',

  props: {
    // 是否显示
    value: {
      type: Boolean,
      default: false
    },
    // 弹出位置
    position: {
      type: String,
      default: 'center'
    },
    // 是否圆角
    round: {
      type: Boolean,
      default: false
    },
    // 是否显示关闭图标
    closeable: {
      type: Boolean,
      default: false
    },
    // 关闭图标名称
    closeIcon: {
      type: String,
      default: 'cross'
    },
    // 是否显示遮罩层
    overlay: {
      type: Boolean,
      default: true
    },
    // 动画类型
    transition: {
      type: String,
      default: 'popup-slide'
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
    handleClose() {
      this.$emit('close')
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
.popup-content {
  padding: 20px;
  max-height: 80vh;
  overflow-y: auto;
}
</style>
