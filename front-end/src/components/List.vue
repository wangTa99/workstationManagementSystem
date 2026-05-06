<template>
  <div class="list-card">
    <van-list
      v-model="loading"
      :finished="finished"
      :finished-text="finishedText"
      @load="onLoad"
    >
      <slot>
        <div
          v-for="(item, index) in list"
          :key="index"
          class="list-item"
          @click="handleClick(item, index)"
        >
          <slot name="item" :item="item" :index="index">
            {{ item }}
          </slot>
        </div>
      </slot>
    </van-list>

    <EmptyState
      v-if="showEmpty && list.length === 0 && !loading"
      :description="emptyDescription"
      :showButton="showEmptyButton"
      @click="handleEmptyClick"
    />
  </div>
</template>

<script>
/**
 * 列表组件
 */
export default {
  name: 'ListCard',

  components: {
    EmptyState
  },

  props: {
    // 列表数据
    list: {
      type: Array,
      default: () => []
    },
    // 是否加载中
    loading: {
      type: Boolean,
      default: false
    },
    // 是否加载完成
    finished: {
      type: Boolean,
      default: false
    },
    // 加载完成文本
    finishedText: {
      type: String,
      default: '没有更多了'
    },
    // 是否显示空状态
    showEmpty: {
      type: Boolean,
      default: true
    },
    // 空状态描述
    emptyDescription: {
      type: String,
      default: '暂无数据'
    },
    // 是否显示空状态按钮
    showEmptyButton: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    onLoad() {
      this.$emit('load')
    },

    handleClick(item, index) {
      this.$emit('item-click', item, index)
    },

    handleEmptyClick() {
      this.$emit('empty-click')
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
.list-card {
  min-height: 200px;
}

.list-item {
  padding: 15px;
  border-bottom: 1px solid #f5f5f5;
}
</style>
