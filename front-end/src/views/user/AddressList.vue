<template>
  <div class="address-list-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="收货地址" fixed placeholder left-arrow @click-left="goBack">
      <template #right>
        <span @click="goToAdd">新增</span>
      </template>
    </van-nav-bar>

    <!-- 地址列表 -->
    <div class="address-list">
      <div
        class="address-item"
        v-for="address in addresses"
        :key="address.id"
        @click="handleSelect(address)"
      >
        <div class="address-content">
          <div class="consignee">
            <span>{{ address.consigneeName }}</span>
            <span>{{ address.consigneePhone }}</span>
            <van-tag v-if="address.isDefault" type="danger" size="small">默认</van-tag>
          </div>
          <div class="address-detail">
            {{ address.province }}{{ address.city }}{{ address.district }}{{ address.detailAddress }}
          </div>
        </div>
        <div class="address-actions">
          <van-icon name="edit" @click.stop="goToEdit(address)" />
          <van-icon name="delete" @click.stop="handleDelete(address)" />
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <EmptyState
      v-if="!loading && addresses.length === 0"
      description="暂无收货地址"
      :showButton="true"
      buttonText="添加地址"
      @click="goToAdd"
    />

    <!-- 加载状态 -->
    <van-loading v-if="loading" class="loading" />
  </div>
</template>

<script>
/**
 * 收货地址列表页面组件
 */
export default {
  name: 'AddressList',

  components: {
    EmptyState
  },

  data() {
    return {
      // 地址列表
      addresses: [],
      // 加载状态
      loading: false,
      // 选择模式
      mode: 'list'
    }
  },

  created() {
    // 获取选择模式
    this.mode = this.$route.query.mode || 'list'
    // 获取地址列表
    this.getAddressList()
  },

  methods: {
    /**
     * 获取地址列表
     */
    getAddressList() {
      this.loading = true
      this.$api.getAddressList().then(res => {
        if (res.code === 200) {
          this.addresses = res.data
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 处理选择地址
     */
    handleSelect(address) {
      if (this.mode === 'select') {
        // 选择模式，返回选中的地址
        this.$router.back()
      }
    },

    /**
     * 跳转到新增地址页面
     */
    goToAdd() {
      this.$router.push('/address/add')
    },

    /**
     * 跳转到编辑地址页面
     */
    goToEdit(address) {
      this.$router.push(`/address/edit/${address.id}`)
    },

    /**
     * 删除地址
     */
    handleDelete(address) {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要删除该地址吗？'
      }).then(() => {
        this.$api.deleteAddress(address.id).then(res => {
          if (res.code === 200) {
            this.$toast.success('删除成功')
            this.getAddressList()
          }
        })
      })
    },

    /**
     * 返回上一页
     */
    goBack() {
      this.$router.go(-1)
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
.address-list-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.address-list {
  padding-top: 46px;
}

.address-item {
  display: flex;
  padding: 15px;
  background-color: #fff;
  margin-bottom: 10px;
}

.address-content {
  flex: 1;
}

.consignee {
  display: flex;
  align-items: center;
  gap: 10px;
  font-weight: bold;
  font-size: 16px;
}

.address-detail {
  margin-top: 8px;
  color: #666;
  font-size: 14px;
  line-height: 1.5;
}

.address-actions {
  display: flex;
  align-items: center;
  gap: 15px;
  color: #999;
}

.loading {
  text-align: center;
  padding: 20px;
}
</style>
