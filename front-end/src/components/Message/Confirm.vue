<template>
  <transition name="confirm-fade">
    <div v-if="visible" class="global-confirm-mask" @click="handleClickMask">
      <div class="global-confirm-wrapper">
        <div class="global-confirm">
          <div class="confirm-header">
            <span class="confirm-icon">
              <svg viewBox="0 0 24 24" width="24" height="24">
                <path fill="#ff9800" d="M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z"/>
              </svg>
            </span>
            <span class="confirm-title">{{ title }}</span>
          </div>
          <div class="confirm-content">
            {{ content }}
          </div>
          <div class="confirm-footer">
            <button class="btn btn-cancel" @click="handleCancel">{{ cancelText }}</button>
            <button class="btn btn-confirm" @click="handleConfirm">{{ confirmText }}</button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'GlobalConfirm',
  data() {
    return {
      visible: false,
      title: '提示',
      content: '',
      confirmText: '确定',
      cancelText: '取消',
      onConfirm: null,
      onCancel: null,
      closeOnClickMask: false
    }
  },
  methods: {
    handleConfirm() {
      this.visible = false
      this.onConfirm && this.onConfirm()
    },
    handleCancel() {
      this.visible = false
      this.onCancel && this.onCancel()
    },
    handleClickMask() {
      if (this.closeOnClickMask) {
        this.handleCancel()
      }
    }
  }
}
</script>

<style scoped>
.global-confirm-mask {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10000;
}

.global-confirm-wrapper {
  padding: 20px;
}

.global-confirm {
  background: #fff;
  border-radius: 8px;
  width: 400px;
  max-width: 90vw;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
}

.confirm-header {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 20px 20px 12px;
}

.confirm-icon {
  display: flex;
  align-items: center;
}

.confirm-title {
  font-size: 16px;
  font-weight: 600;
  color: #333;
}

.confirm-content {
  padding: 0 20px 20px;
  font-size: 14px;
  color: #666;
  line-height: 1.6;
}

.confirm-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 0 20px 20px;
}

.btn {
  padding: 8px 20px;
  font-size: 14px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s;
  border: none;
}

.btn-cancel {
  background: #fff;
  color: #666;
  border: 1px solid #dcdfe6;
}

.btn-cancel:hover {
  color: #409eff;
  border-color: #c6e2ff;
  background: #ecf5ff;
}

.btn-confirm {
  background: #409eff;
  color: #fff;
}

.btn-confirm:hover {
  background: #66b1ff;
}

/* 过渡动画 */
.confirm-fade-enter {
  opacity: 0;
}

.confirm-fade-enter .global-confirm {
  transform: scale(0.9);
}

.confirm-fade-enter-to {
  opacity: 1;
}

.confirm-fade-enter-to .global-confirm {
  transform: scale(1);
}

.confirm-fade-leave {
  opacity: 1;
}

.confirm-fade-leave .global-confirm {
  transform: scale(1);
}

.confirm-fade-leave-to {
  opacity: 0;
}

.confirm-fade-leave-to .global-confirm {
  transform: scale(0.9);
}

.confirm-fade-enter-active,
.confirm-fade-leave-active {
  transition: all 0.3s ease;
}

.confirm-fade-enter-active .global-confirm,
.confirm-fade-leave-active .global-confirm {
  transition: transform 0.3s ease;
}
</style>
