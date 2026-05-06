<template>
  <div class="datetime-picker-card">
    <van-datetime-picker
      v-model="currentDate"
      :type="type"
      :title="title"
      :min-date="minDate"
      :max-date="maxDate"
      :min-hour="minHour"
      :max-hour="maxHour"
      :min-minute="minMinute"
      :max-minute="maxMinute"
      :formatter="formatter"
      :filter="filter"
      :confirm-text="confirmText"
      :cancel-text="cancelText"
      :visible-item-count="visibleItemCount"
      :item-height="itemHeight"
      @confirm="handleConfirm"
      @cancel="handleCancel"
      @change="handleChange"
    />
  </div>
</template>

<script>
/**
 * 日期时间选择器组件
 */
export default {
  name: 'DatetimePickerCard',

  props: {
    // 当前选中值
    value: {
      type: [Date, String, Number],
      default: () => new Date()
    },
    // 选择器类型
    type: {
      type: String,
      default: 'datetime'
    },
    // 标题
    title: {
      type: String,
      default: '选择时间'
    },
    // 最小日期
    minDate: {
      type: Date,
      default: () => new Date(new Date().getFullYear() - 10, 0, 1)
    },
    // 最大日期
    maxDate: {
      type: Date,
      default: () => new Date(new Date().getFullYear() + 10, 11, 31)
    },
    // 最小小时
    minHour: {
      type: Number,
      default: 0
    },
    // 最大小时
    maxHour: {
      type: Number,
      default: 23
    },
    // 最小分钟
    minMinute: {
      type: Number,
      default: 0
    },
    // 最大分钟
    maxMinute: {
      type: Number,
      default: 59
    },
    // 列格式化函数
    formatter: {
      type: Function,
      default: null
    },
    // 列过滤函数
    filter: {
      type: Function,
      default: null
    },
    // 确认按钮文字
    confirmText: {
      type: String,
      default: '确认'
    },
    // 取消按钮文字
    cancelText: {
      type: String,
      default: '取消'
    },
    // 可见的选项个数
    visibleItemCount: {
      type: Number,
      default: 5
    },
    // 选项高度
    itemHeight: {
      type: Number,
      default: 44
    }
  },

  computed: {
    currentDate: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  },

  methods: {
    handleConfirm(value) {
      this.$emit('confirm', value)
    },

    handleCancel() {
      this.$emit('cancel')
    },

    handleChange(picker) {
      this.$emit('change', picker)
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
.datetime-picker-card {
  background-color: #fff;
}
</style>
