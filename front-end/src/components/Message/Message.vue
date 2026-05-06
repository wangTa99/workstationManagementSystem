<template>
  <transition name="message-fade" @after-leave="handleAfterLeave">
    <div
      v-if="visible"
      :class="['global-message', type]"
      :style="customStyle"
    >
      <span class="message-icon">
        <svg v-if="type === 'success'" viewBox="0 0 24 24" width="18" height="18">
          <path fill="currentColor" d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"/>
        </svg>
        <svg v-else-if="type === 'error'" viewBox="0 0 24 24" width="18" height="18">
          <path fill="currentColor" d="M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z"/>
        </svg>
        <svg v-else-if="type === 'warning'" viewBox="0 0 24 24" width="18" height="18">
          <path fill="currentColor" d="M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z"/>
        </svg>
        <svg v-else viewBox="0 0 24 24" width="18" height="18">
          <path fill="currentColor" d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z"/>
        </svg>
      </span>
      <span class="message-content">{{ message }}</span>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'GlobalMessage',
  data() {
    return {
      visible: false,
      message: '',
      type: 'info',
      duration: 3000,
      onClose: null,
      offset: 20
    }
  },
  computed: {
    customStyle() {
      return {
        top: `${this.offset}px`
      }
    }
  },
  methods: {
    handleAfterLeave() {
      this.$destroy(true)
      this.$el.remove()
    }
  },
  mounted() {
    this.visible = true
    if (this.duration > 0) {
      setTimeout(() => {
        this.visible = false
        this.onClose && this.onClose()
      }, this.duration)
    }
  }
}
</script>

<style scoped>
.global-message {
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  padding: 12px 20px;
  border-radius: 6px;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 14px;
  z-index: 9999;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  min-width: 280px;
  max-width: 500px;
}

.global-message.success {
  background: #f0f9eb;
  color: #67c23a;
  border: 1px solid #e1f3d8;
}

.global-message.error {
  background: #fef0f0;
  color: #f56c6c;
  border: 1px solid #fde2e2;
}

.global-message.warning {
  background: #fdf6ec;
  color: #e6a23c;
  border: 1px solid #faecd8;
}

.global-message.info {
  background: #edf2fc;
  color: #409eff;
  border: 1px solid #d9ecff;
}

.message-icon {
  display: flex;
  align-items: center;
}

/* 过渡动画 */
.message-fade-enter {
  opacity: 0;
  transform: translateX(-50%) translateY(-20px);
}

.message-fade-enter-to {
  opacity: 1;
  transform: translateX(-50%) translateY(0);
}

.message-fade-leave {
  opacity: 1;
  transform: translateX(-50%) translateY(0);
}

.message-fade-leave-to {
  opacity: 0;
  transform: translateX(-50%) translateY(-20px);
}

.message-fade-enter-active,
.message-fade-leave-active {
  transition: all 0.3s ease;
}
</style>
