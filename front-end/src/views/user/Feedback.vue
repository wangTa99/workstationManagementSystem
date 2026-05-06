<template>
  <div class="feedback-page">
    <!-- 导航栏 -->
    <NavBar title="意见反馈" :left-arrow="true" @click-left="handleBack" />

    <!-- 反馈类型 -->
    <van-cell-group>
      <van-cell title="反馈类型" is-link :value="feedbackTypeText" @click="showTypePicker = true" />
    </van-cell-group>

    <!-- 反馈内容 -->
    <van-cell-group class="feedback-content">
      <van-field
        v-model="content"
        type="textarea"
        placeholder="请输入您的意见或建议..."
        rows="6"
        maxlength="500"
        show-word-limit
      />
    </van-cell-group>

    <!-- 联系方式 -->
    <van-cell-group>
      <van-field
        v-model="contact"
        type="tel"
        placeholder="请输入手机号或邮箱（选填）"
        label="联系方式"
      />
    </van-cell-group>

    <!-- 上传图片 -->
    <van-cell-group class="upload-group">
      <van-cell title="上传图片（选填）">
        <template #right-icon>
          <van-uploader
            v-model="images"
            :max-count="3"
            :preview-size="80"
            accept="image/*"
            @oversize="handleOversize"
          />
        </template>
      </van-cell>
    </van-cell-group>

    <!-- 提交按钮 -->
    <div class="submit-btn">
      <van-button type="primary" block round :loading="submitting" @click="handleSubmit">
        提交反馈
      </van-button>
    </div>

    <!-- 类型选择器 -->
    <van-popup v-model="showTypePicker" position="bottom" round>
      <van-picker
        title="选择反馈类型"
        :columns="typeColumns"
        @confirm="handleTypeConfirm"
        @cancel="showTypePicker = false"
      />
    </van-popup>
  </div>
</template>

<script>
/**
 * 意见反馈页面
 */
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'FeedbackPage',

  components: {
    NavBar
  },

  data() {
    return {
      // 反馈类型
      feedbackType: '',
      // 反馈内容
      content: '',
      // 联系方式
      contact: '',
      // 上传的图片
      images: [],
      // 是否显示类型选择器
      showTypePicker: false,
      // 是否提交中
      submitting: false,
      // 反馈类型选项
      typeColumns: [
        { text: '功能建议', value: 'suggest' },
        { text: 'bug反馈', value: 'bug' },
        { text: '体验问题', value: 'experience' },
        { text: '其他', value: 'other' }
      ]
    }
  },

  computed: {
    // 反馈类型文本
    feedbackTypeText() {
      const item = this.typeColumns.find(col => col.value === this.feedbackType)
      return item ? item.text : '请选择'
    }
  },

  methods: {
    // 返回
    handleBack() {
      this.$router.go(-1)
    },

    // 类型选择确认
    handleTypeConfirm({ selectedOptions }) {
      this.feedbackType = selectedOptions[0].value
      this.showTypePicker = false
    },

    // 文件大小超限
    handleOversize() {
      this.$toast('图片大小不能超过5MB')
    },

    // 提交反馈
    async handleSubmit() {
      if (!this.feedbackType) {
        this.$toast('请选择反馈类型')
        return
      }
      if (!this.content || this.content.trim() === '') {
        this.$toast('请输入反馈内容')
        return
      }

      this.submitting = true
      try {
        // 模拟提交
        await new Promise(resolve => setTimeout(resolve, 1000))
        this.$toast('提交成功，感谢您的反馈')
        setTimeout(() => {
          this.$router.go(-1)
        }, 1500)
      } catch (error) {
        this.$toast('提交失败，请重试')
      } finally {
        this.submitting = false
      }
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
.feedback-page {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.feedback-content {
  margin-top: 12px;
}

.upload-group {
  margin-top: 12px;
}

.submit-btn {
  margin: 30px 16px;
}
</style>
