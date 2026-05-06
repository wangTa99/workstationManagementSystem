<template>
  <div class="invoice-page">
    <!-- 导航栏 -->
    <NavBar title="发票管理" :left-arrow="true" @click-left="handleBack" />

    <!-- 发票列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="loadData"
    >
      <van-cell-group>
        <van-cell
          v-for="(item, index) in invoices"
          :key="index"
          :title="item.title"
          :label="item.content"
          :value="item.statusText"
          :value-class="item.status === 'pending' ? 'status-pending' : 'status-confirmed'"
          is-link
          @click="handleInvoiceClick(item)"
        />
      </van-cell-group>
    </van-list>

    <!-- 申请发票按钮 -->
    <div class="apply-btn">
      <van-button type="primary" block round @click="handleApply">
        申请开票
      </van-button>
    </div>

    <!-- 申请弹窗 -->
    <van-popup v-model="showApply" position="bottom" round>
      <div class="apply-content">
        <h3>申请发票</h3>
        <van-form @submit="handleSubmit">
          <van-field
            v-model="form.title"
            label="发票抬头"
            placeholder="请输入发票抬头"
            required
          />
          <van-field
            v-model="form.taxNo"
            label="税号"
            placeholder="请输入税号"
          />
          <van-field
            v-model="form.address"
            label="地址"
            placeholder="请输入地址"
          />
          <van-field
            v-model="form.phone"
            label="电话"
            placeholder="请输入电话"
          />
          <van-field
            v-model="form.email"
            label="邮箱"
            placeholder="请输入接收邮箱"
          />
          <div class="form-buttons">
            <van-button round block @click="showApply = false">取消</van-button>
            <van-button type="primary" round block native-type="submit">提交</van-button>
          </div>
        </van-form>
      </div>
    </van-popup>
  </div>
</template>

<script>
/**
 * 发票页面
 */
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'InvoicePage',

  components: {
    NavBar
  },

  data() {
    return {
      // 发票列表
      invoices: [],
      // 加载状态
      loading: false,
      // 是否加载完成
      finished: false,
      // 显示申请弹窗
      showApply: false,
      // 申请表单
      form: {
        title: '',
        taxNo: '',
        address: '',
        phone: '',
        email: ''
      }
    }
  },

  mounted() {
    this.loadData()
  },

  methods: {
    // 返回
    handleBack() {
      this.$router.go(-1)
    },

    // 加载数据
    loadData() {
      this.loading = true
      // 模拟数据
      setTimeout(() => {
        this.invoices = [
          {
            id: 1,
            title: '上海某某科技有限公司',
            content: '税号：91310115MA1K4XXXXX',
            status: 'confirmed',
            statusText: '已开票'
          },
          {
            id: 2,
            title: '个人',
            content: '内容：商品明细',
            status: 'pending',
            statusText: '待开票'
          }
        ]
        this.loading = false
        this.finished = true
      }, 500)
    },

    // 点击发票
    handleInvoiceClick(item) {
      // 跳转到发票详情
    },

    // 申请开票
    handleApply() {
      this.showApply = true
    },

    // 提交申请
    handleSubmit() {
      if (!this.form.title) {
        this.$toast('请输入发票抬头')
        return
      }
      this.$toast('提交成功')
      this.showApply = false
      this.loadData()
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
.invoice-page {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 70px;
}

.status-pending {
  color: #ff976a !important;
}

.status-confirmed {
  color: #07c160 !important;
}

.apply-btn {
  position: fixed;
  bottom: 20px;
  left: 16px;
  right: 16px;
}

.apply-content {
  padding: 20px;
}

.apply-content h3 {
  text-align: center;
  margin-bottom: 20px;
}

.form-buttons {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}
</style>
