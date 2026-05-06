<template>
  <div class="setting-page">
    <!-- 导航栏 -->
    <NavBar title="设置" :left-arrow="true" @click-left="handleBack" />

    <!-- 设置列表 -->
    <van-cell-group>
      <van-cell title="清理缓存" is-link @click="handleClearCache" />
      <van-cell title="检查更新" is-link :value="version" @click="handleCheckUpdate" />
      <van-cell title="关于我们" is-link @click="handleAbout" />
    </van-cell-group>

    <van-cell-group class="setting-group">
      <van-cell title="消息通知" is-link @click="handleNotification" />
      <van-cell title="隐私政策" is-link @click="handlePrivacy" />
      <van-cell title="用户协议" is-link @click="handleAgreement" />
    </van-cell-group>

    <van-cell-group class="setting-group">
      <van-cell title="账户安全" is-link @click="handleSecurity" />
      <van-cell title="支付设置" is-link @click="handlePaySetting" />
      <van-cell title="收货地址" is-link @click="handleAddress" />
    </van-cell-group>

    <!-- 退出登录 -->
    <div class="logout-btn">
      <van-button type="danger" block round @click="handleLogout">
        退出登录
      </van-button>
    </div>
  </div>
</template>

<script>
/**
 * 设置页面
 */
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'SettingPage',

  components: {
    NavBar
  },

  data() {
    return {
      // 当前版本
      version: 'v1.0.0'
    }
  },

  methods: {
    // 返回
    handleBack() {
      this.$router.go(-1)
    },

    // 清理缓存
    handleClearCache() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要清理缓存吗？'
      }).then(() => {
        this.$toast('清理成功')
      })
    },

    // 检查更新
    handleCheckUpdate() {
      this.$toast('已是最新版本')
    },

    // 关于我们
    handleAbout() {
      this.$router.push('/about')
    },

    // 消息通知
    handleNotification() {
      this.$router.push('/notification-setting')
    },

    // 隐私政策
    handlePrivacy() {
      this.$router.push('/privacy')
    },

    // 用户协议
    handleAgreement() {
      this.$router.push('/agreement')
    },

    // 账户安全
    handleSecurity() {
      this.$router.push('/security')
    },

    // 支付设置
    handlePaySetting() {
      this.$router.push('/pay-setting')
    },

    // 收货地址
    handleAddress() {
      this.$router.push('/address-list')
    },

    // 退出登录
    handleLogout() {
      this.$dialog.confirm({
        title: '提示',
        message: '确定要退出登录吗？'
      }).then(() => {
        this.$store.commit('user/LOGOUT')
        this.$toast('已退出登录')
        this.$router.replace('/login')
      })
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
.setting-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.setting-group {
  margin-top: 12px;
}

.logout-btn {
  margin: 40px 16px;
}
</style>
