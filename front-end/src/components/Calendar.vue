<template>
  <div class="calendar-card">
    <van-calendar
      v-model="visible"
      :type="type"
      :title="title"
      :color="color"
      :min-date="minDate"
      :max-date="maxDate"
      :default-date="defaultDate"
      :formatter="formatter"
      :confirm-text="confirmText"
      :confirm-disabled-text="confirmDisabledText"
      :readonly="readonly"
      :round="round"
      :poppable="poppable"
      :lazy-render="lazyRender"
      @confirm="handleConfirm"
      @select="handleSelect"
      @open="handleOpen"
      @close="handleClose"
    />
  </div>
</template>

<script>
/**
 * 日历组件
 */
export default {
  name: 'CalendarCard',

  props: {
    // 是否显示
    value: {
      type: Boolean,
      default: false
    },
    // 类型
    type: {
      type: String,
      default: 'single'
    },
    // 标题
    title: {
      type: String,
      default: '选择日期'
    },
    // 主题颜色
    color: {
      type: String,
      default: '#1989fa'
    },
    // 最小日期
    minDate: {
      type: Date,
      default: () => new Date()
    },
    // 最大日期
    maxDate: {
      type: Date,
      default: () => {
        const date = new Date()
        date.setFullYear(date.getFullYear() + 1)
        return date
      }
    },
    // 默认选中的日期
    defaultDate: {
      type: [Date, Array],
      default: () => new Date()
    },
    // 日期格式化函数
    formatter: {
      type: Function,
      default: null
    },
    // 确认按钮文字
    confirmText: {
      type: String,
      default: '确定'
    },
    // 确认按钮禁用时的文字
    confirmDisabledText: {
      type: String,
      default: '请选择日期'
    },
    // 是否只读
    readonly: {
      type: Boolean,
      default: false
    },
    // 是否圆角
    round: {
      type: Boolean,
      default: true
    },
    // 是否弹出展示
    poppable: {
      type: Boolean,
      default: true
    },
    // 是否延迟渲染
    lazyRender: {
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
    handleConfirm(date) {
      this.$emit('confirm', date)
    },

    handleSelect(date) {
      this.$emit('select', date)
    },

    handleOpen() {
      this.$emit('open')
    },

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
.calendar-card {
  background-color: #fff;
}
</style>
