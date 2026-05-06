<template>
  <div class="register-container">
    <!-- 顶部导航栏 -->
    <van-nav-bar title="注册" fixed placeholder left-arrow @click-left="goBack" />

    <!-- 注册表单 -->
    <div class="register-form">
      <van-form @submit="handleRegister">
        <van-field
          v-model="registerForm.username"
          name="username"
          label="用户名"
          placeholder="请输入用户名"
          :rules="[
            { required: true, message: '请输入用户名' },
            { pattern: /^[a-zA-Z0-9_]{4,16}$/, message: '用户名4-16位字母、数字或下划线' }
          ]"
        />
        <van-field
          v-model="registerForm.password"
          type="password"
          name="password"
          label="密码"
          placeholder="请输入密码"
          :rules="[
            { required: true, message: '请输入密码' },
            { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$/, message: '密码6-20位，包含字母和数字' }
          ]"
        />
        <van-field
          v-model="registerForm.confirmPassword"
          type="password"
          name="confirmPassword"
          label="确认密码"
          placeholder="请再次输入密码"
          :rules="[
            { required: true, message: '请确认密码' },
            { validator: validateConfirmPassword, message: '两次密码输入不一致' }
          ]"
        />
        <van-field
          v-model="registerForm.phone"
          name="phone"
          label="手机号"
          placeholder="请输入手机号"
          :rules="[
            { required: true, message: '请输入手机号' },
            { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确' }
          ]"
        />
        <van-field
          v-model="registerForm.captcha"
          name="captcha"
          center
          clearable
          label="验证码"
          placeholder="请输入验证码"
          :rules="[{ required: true, message: '请输入验证码' }]"
        >
          <template #button>
            <van-button
              size="small"
              type="primary"
              :disabled="countdown > 0"
              @click="sendCaptcha"
            >
              {{ countdown > 0 ? `${countdown}s` : '发送验证码' }}
            </van-button>
          </template>
        </van-field>

        <div class="agreement-row">
          <van-checkbox v-model="agreed" shape="square" icon-size="14">
            我已阅读并同意
          </van-checkbox>
          <span class="agreement-link" @click="showAgreement">《用户协议》</span>
          <span>和</span>
          <span class="agreement-link" @click="showPrivacy">《隐私政策》</span>
        </div>

        <div class="submit-row">
          <van-button type="primary" block native-type="submit" :loading="loading">
            注册
          </van-button>
        </div>
      </van-form>

      <div class="login-row">
        <span>已有账号？</span>
        <span class="login-btn" @click="goToLogin">立即登录</span>
      </div>
    </div>
  </div>
</template>

<script>
/**
 * 注册页面组件
 * 提供用户注册功能，包括用户名、密码、手机号验证
 */
export default {
  name: 'Register',

  data() {
    return {
      // 注册表单
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        phone: '',
        captcha: ''
      },
      // 是否同意协议
      agreed: false,
      // 加载状态
      loading: false,
      // 倒计时
      countdown: 0
    }
  },

  methods: {
    /**
     * 验证确认密码
     */
    validateConfirmPassword(value) {
      return value === this.registerForm.password
    },

    /**
     * 发送验证码
     */
    sendCaptcha() {
      // 验证手机号格式
      if (!/^1[3-9]\d{9}$/.test(this.registerForm.phone)) {
        this.$toast('请输入正确的手机号')
        return
      }

      // 发送验证码
      this.$api.sendRegisterCaptcha(this.registerForm.phone).then(res => {
        if (res.code === 200) {
          this.$toast.success('验证码已发送')
          this.countdown = 60
          const timer = setInterval(() => {
            this.countdown--
            if (this.countdown <= 0) {
              clearInterval(timer)
            }
          }, 1000)
        } else {
          this.$toast.fail(res.message)
        }
      })
    },

    /**
     * 处理注册
     */
    handleRegister() {
      // 验证是否同意协议
      if (!this.agreed) {
        this.$toast('请阅读并同意用户协议和隐私政策')
        return
      }

      this.loading = true
      this.$api.register(this.registerForm).then(res => {
        if (res.code === 200) {
          this.$toast.success('注册成功')
          this.$router.replace('/login')
        } else {
          this.$toast.fail(res.message)
        }
      }).finally(() => {
        this.loading = false
      })
    },

    /**
     * 显示用户协议
     */
    showAgreement() {
      this.$router.push('/agreement')
    },

    /**
     * 显示隐私政策
     */
    showPrivacy() {
      this.$router.push('/privacy')
    },

    /**
     * 跳转到登录页面
     */
    goToLogin() {
      this.$router.push('/login')
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
.register-container {
  min-height: 100vh;
  background-color: #fff;
}

.register-form {
  padding: 40px 20px;
}

.agreement-row {
  display: flex;
  align-items: center;
  padding: 15px 0;
  font-size: 12px;
}

.agreement-link {
  color: #f00;
  margin: 0 2px;
}

.submit-row {
  margin-top: 20px;
}

.login-row {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  font-size: 14px;
}

.login-btn {
  color: #f00;
  margin-left: 5px;
}
</style>
