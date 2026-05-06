<template>
  <div class="login-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="登录" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 登录表单 -->
    <div class="login-form">
      <div class="logo">
        <van-image width="80" height="80" :src="logo" />
      </div>

      <van-form @submit="handleLogin">
        <van-field
          v-model="loginForm.username"
          name="username"
          label="用户名"
          placeholder="请输入用户名或手机号"
          :rules="[{ required: true, message: '请输入用户名' }]"
        />
        <van-field
          v-model="loginForm.password"
          type="password"
          name="password"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请输入密码' }]"
        />

        <div class="captcha-row" v-if="showCaptcha">
          <van-field
            v-model="loginForm.captcha"
            name="captcha"
            label="验证码"
            placeholder="请输入验证码"
            :rules="[{ required: true, message: '请输入验证码' }]"
          />
          <van-image
            width="100"
            height="40"
            :src="captchaUrl"
            @click="refreshCaptcha"
          />
        </div>

        <div class="forgot-row">
          <span @click="goToForgotPassword">忘记密码？</span>
        </div>

        <div class="submit-row">
          <van-button type="primary" block native-type="submit" :loading="loading">
            登录
          </van-button>
        </div>
      </van-form>

      <div class="register-row">
        <span>还没有账号？</span>
        <span class="register-btn" @click="goToRegister">立即注册</span>
      </div>

      <!-- 第三方登录 -->
      <div class="third-login" v-if="thirdLoginOptions.length > 0">
        <div class="divider">
          <span>其他登录方式</span>
        </div>
        <div class="third-icons">
          <div
            class="third-icon"
            v-for="option in thirdLoginOptions"
            :key="option.type"
            @click="handleThirdLogin(option.type)"
          >
            <van-icon :name="option.icon" size="30" :color="option.color" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/**
 * 登录页面组件
 * 提供用户名密码登录、验证码登录和第三方登录功能
 */
export default {
  name: 'Login',

  data() {
    return {
      // 登录表单
      loginForm: {
        username: '',
        password: '',
        captcha: ''
      },
      // 加载状态
      loading: false,
      // 是否显示验证码
      showCaptcha: false,
      // 验证码URL
      captchaUrl: '',
      // 验证码Key
      captchaKey: '',
      // Logo
      logo: 'https://img.yzcdn.cn/vant/cat.jpeg',
      // 第三方登录选项
      thirdLoginOptions: [
        { type: 'wechat', icon: 'wechat', color: '#07c160' },
        { type: 'qq', icon: 'qq', color: '#1296db' }
      ]
    }
  },

  methods: {
    /**
     * 处理登录
     */
    handleLogin() {
      this.loading = true
      this.$api.login(this.loginForm).then(res => {
        if (res.code === 200) {
          // 保存token
          localStorage.setItem('token', res.data.token)
          localStorage.setItem('userInfo', JSON.stringify(res.data))
          this.$toast.success('登录成功')
          // 跳转到首页或之前的页面
          const redirect = this.$route.query.redirect || '/'
          this.$router.replace(redirect)
        } else {
          this.$toast.fail(res.message)
          // 如果需要验证码
          if (res.code === 400 && res.data.needCaptcha) {
            this.showCaptcha = true
            this.refreshCaptcha()
          }
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 刷新验证码
     */
    refreshCaptcha() {
      this.captchaKey = new Date().getTime()
      this.captchaUrl = `/api/captcha?key=${this.captchaKey}`
    },

    /**
     * 处理第三方登录
     */
    handleThirdLogin(type) {
      // 调用第三方登录
      if (type === 'wechat') {
        // 微信登录逻辑
        this.$toast('微信登录开发中')
      } else if (type === 'qq') {
        // QQ登录逻辑
        this.$toast('QQ登录开发中')
      }
    },

    /**
     * 跳转到注册页面
     */
    goToRegister() {
      this.$router.push('/register')
    },

    /**
     * 跳转到忘记密码页面
     */
    goToForgotPassword() {
      this.$router.push('/forgot-password')
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
.login-container {
  min-height: 100vh;
  background-color: #fff;
}

.login-form {
  padding: 40px 20px;
}

.logo {
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
}

.captcha-row {
  display: flex;
  align-items: center;
}

.captcha-row .van-field {
  flex: 1;
}

.forgot-row {
  display: flex;
  justify-content: flex-end;
  padding: 15px 0;
}

.forgot-row span {
  color: #666;
  font-size: 14px;
}

.submit-row {
  margin-top: 20px;
}

.register-row {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  font-size: 14px;
}

.register-btn {
  color: #f00;
  margin-left: 5px;
}

.third-login {
  margin-top: 60px;
}

.divider {
  display: flex;
  align-items: center;
  color: #999;
  font-size: 12px;
}

.divider::before,
.divider::after {
  content: '';
  flex: 1;
  height: 1px;
  background-color: #ddd;
}

.divider span {
  padding: 0 20px;
}

.third-icons {
  display: flex;
  justify-content: center;
  gap: 40px;
  margin-top: 30px;
}

.third-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #f5f5f5;
}
</style>
