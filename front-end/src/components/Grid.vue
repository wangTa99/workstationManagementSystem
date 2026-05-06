<template>
  <div class="grid-card">
    <van-grid :column-num="columnNum" :border="border" :gutter="gutter">
      <van-grid-item
        v-for="(item, index) in items"
        :key="index"
        @click="handleClick(index)"
      >
        <van-icon :name="item.icon" size="24" :color="item.color" />
        <span class="grid-text">{{ item.text }}</span>
      </van-grid-item>
    </van-grid>
  </div>
</template>

<script>
/**
 * 宫格组件
 */
export default {
  name: 'GridCard',

  props: {
    // 列数
    columnNum: {
      type: Number,
      default: 4
    },
    // 是否显示边框
    border: {
      type: Boolean,
      default: true
    },
    // 格子间距
    gutter: {
      type: Number,
      default: 0
    },
    // 宫格数据
    items: {
      type: Array,
      default: () => []
    }
  },

  methods: {
    handleClick(index) {
      this.$emit('click', index)
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
.grid-card {
  background-color: #fff;
}

.grid-text {
  margin-top: 5px;
  font-size: 12px;
  color: #333;
}
</style>
