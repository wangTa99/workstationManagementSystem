<template>
  <div class="search-bar">
    <van-search
      v-model="keyword"
      :placeholder="placeholder"
      show-action
      @search="handleSearch"
      @cancel="handleCancel"
      @input="handleInput"
    />
  </div>
</template>

<script>
/**
 * 搜索栏组件
 */
export default {
  name: 'SearchBar',

  props: {
    // 占位文本
    placeholder: {
      type: String,
      default: '搜索商品'
    },
    // 默认关键词
    value: {
      type: String,
      default: ''
    }
  },

  data() {
    return {
      keyword: this.value
    }
  },

  watch: {
    value(val) {
      this.keyword = val
    }
  },

  methods: {
    /**
     * 处理搜索
     */
    handleSearch() {
      this.$emit('search', this.keyword)
    },

    /**
     * 处理取消
     */
    handleCancel() {
      this.keyword = ''
      this.$emit('cancel')
    },

    /**
     * 处理输入
     */
    handleInput() {
      this.$emit('input', this.keyword)
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
.search-bar {
  background-color: #fff;
}
</style>
