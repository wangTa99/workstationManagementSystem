import Message from './Message.vue'
import Confirm from './Confirm.vue'

let seed = 1

// 获取唯一id
const getUid = () => {
  return 'msg_' + seed++
}

// 创建并显示Message
const showMessage = (Vue, options) => {
  const {
    message = '',
    type = 'info',
    duration = 3000,
    onClose,
    offset = 20
  } = options

  const container = document.createElement('div')
  document.body.appendChild(container)

  const MessageConstructor = Vue.extend(Message)
  const instance = new MessageConstructor({
    data: {
      visible: false,
      message,
      type,
      duration,
      onClose,
      offset
    }
  })

  instance.$mount(container)

  return instance
}

// 创建并显示Confirm
const showConfirm = (Vue, options) => {
  const {
    title = '提示',
    content = '',
    confirmText = '确定',
    cancelText = '取消',
    onConfirm,
    onCancel,
    closeOnClickMask = false
  } = options

  const container = document.createElement('div')
  document.body.appendChild(container)

  const ConfirmConstructor = Vue.extend(Confirm)
  const instance = new ConfirmConstructor({
    data: {
      visible: false,
      title,
      content,
      confirmText,
      cancelText,
      onConfirm,
      onCancel,
      closeOnClickMask
    }
  })

  instance.$mount(container)

  // 延迟显示，确保DOM已挂载
  setTimeout(() => {
    instance.visible = true
  }, 10)

  return instance
}

const MessagePlugin = {
  install(Vue) {
    // 创建全局Message方法
    const message = (options) => {
      return showMessage(Vue, options)
    }

    // 快捷方法
    message.success = (msg, duration) => {
      return showMessage(Vue, { message: msg, type: 'success', duration })
    }

    message.error = (msg, duration) => {
      return showMessage(Vue, { message: msg, type: 'error', duration })
    }

    message.warning = (msg, duration) => {
      return showMessage(Vue, { message: msg, type: 'warning', duration })
    }

    message.info = (msg, duration) => {
      return showMessage(Vue, { message: msg, type: 'info', duration })
    }

    // 创建全局Confirm方法
    const confirm = (options) => {
      return showConfirm(Vue, options)
    }

    // 挂载到Vue原型
    Vue.prototype.$message = message
    Vue.prototype.$confirm = confirm
  }
}

export default MessagePlugin
