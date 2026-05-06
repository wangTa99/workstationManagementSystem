/**
 * 工具函数集合
 */

/**
 * 格式化金额
 * @param {number} amount 金额
 * @param {number} decimals 小数位数
 * @returns {string}
 */
export function formatAmount(amount, decimals = 2) {
  if (amount === null || amount === undefined) {
    return '0.00'
  }
  return parseFloat(amount).toFixed(decimals)
}

/**
 * 格式化日期
 * @param {Date|string|number} date 日期
 * @param {string} format 格式
 * @returns {string}
 */
export function formatDate(date, format = 'YYYY-MM-DD') {
  if (!date) return ''

  const d = new Date(date)

  const year = d.getFullYear()
  const month = String(d.getMonth() + 1).padStart(2, '0')
  const day = String(d.getDate()).padStart(2, '0')
  const hours = String(d.getHours()).padStart(2, '0')
  const minutes = String(d.getMinutes()).padStart(2, '0')
  const seconds = String(d.getSeconds()).padStart(2, '0')

  return format
    .replace('YYYY', year)
    .replace('MM', month)
    .replace('DD', day)
    .replace('HH', hours)
    .replace('mm', minutes)
    .replace('ss', seconds)
}

/**
 * 格式化相对时间
 * @param {Date|string|number} date 日期
 * @returns {string}
 */
export function formatRelativeTime(date) {
  if (!date) return ''

  const now = Date.now()
  const timestamp = new Date(date).getTime()
  const diff = now - timestamp

  const minute = 60 * 1000
  const hour = 60 * minute
  const day = 24 * hour
  const week = 7 * day
  const month = 30 * day
  const year = 365 * day

  if (diff < minute) {
    return '刚刚'
  } else if (diff < hour) {
    return `${Math.floor(diff / minute)}分钟前`
  } else if (diff < day) {
    return `${Math.floor(diff / hour)}小时前`
  } else if (diff < week) {
    return `${Math.floor(diff / day)}天前`
  } else if (diff < month) {
    return `${Math.floor(diff / week)}周前`
  } else if (diff < year) {
    return `${Math.floor(diff / month)}月前`
  } else {
    return `${Math.floor(diff / year)}年前`
  }
}

/**
 * 手机号脱敏
 * @param {string} phone 手机号
 * @returns {string}
 */
export function maskPhone(phone) {
  if (!phone) return ''
  return phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')
}

/**
 * 邮箱脱敏
 * @param {string} email 邮箱
 * @returns {string}
 */
export function maskEmail(email) {
  if (!email) return ''
  const [username, domain] = email.split('@')
  if (!domain) return email
  const maskedUsername = username.substring(0, 2) + '***'
  return maskedUsername + '@' + domain
}

/**
 * 生成随机字符串
 * @param {number} length 长度
 * @returns {string}
 */
export function randomString(length = 32) {
  const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
  let result = ''
  for (let i = 0; i < length; i++) {
    result += chars.charAt(Math.floor(Math.random() * chars.length))
  }
  return result
}

/**
 * 判断是否为空
 * @param {any} value 值
 * @returns {boolean}
 */
export function isEmpty(value) {
  if (value === null || value === undefined) return true
  if (typeof value === 'string') return value.trim().length === 0
  if (Array.isArray(value)) return value.length === 0
  if (typeof value === 'object') return Object.keys(value).length === 0
  return false
}

/**
 * 判断是否为空或零
 * @param {any} value 值
 * @returns {boolean}
 */
export function isNullOrZero(value) {
  if (value === null || value === undefined) return true
  if (value === 0) return true
  return false
}

/**
 * 防抖函数
 * @param {function} func 函数
 * @param {number} wait 等待时间
 * @returns {function}
 */
export function debounce(func, wait = 300) {
  let timeout = null
  return function (...args) {
    if (timeout) clearTimeout(timeout)
    timeout = setTimeout(() => {
      func.apply(this, args)
    }, wait)
  }
}

/**
 * 节流函数
 * @param {function} func 函数
 * @param {number} wait 等待时间
 * @returns {function}
 */
export function throttle(func, wait = 300) {
  let timeout = null
  let previous = 0
  return function (...args) {
    const now = Date.now()
    const remaining = wait - (now - previous)
    if (remaining <= 0 || remaining > wait) {
      if (timeout) {
        clearTimeout(timeout)
        timeout = null
      }
      previous = now
      func.apply(this, args)
    } else if (!timeout) {
      timeout = setTimeout(() => {
        previous = Date.now()
        timeout = null
        func.apply(this, args)
      }, remaining)
    }
  }
}

/**
 * 深拷贝
 * @param {any} obj 对象
 * @returns {any}
 */
export function deepClone(obj) {
  if (obj === null || typeof obj !== 'object') return obj

  if (obj instanceof Date) {
    return new Date(obj.getTime())
  }

  if (obj instanceof Array) {
    return obj.map(item => deepClone(item))
  }

  if (obj instanceof Object) {
    const clonedObj = {}
    for (const key in obj) {
      if (obj.hasOwnProperty(key)) {
        clonedObj[key] = deepClone(obj[key])
      }
    }
    return clonedObj
  }
}

/**
 * 存储localStorage
 * @param {string} key 键
 * @param {any} value 值
 */
export function setStorage(key, value) {
  try {
    localStorage.setItem(key, JSON.stringify(value))
  } catch (e) {
    console.error('存储失败:', e)
  }
}

/**
 * 获取localStorage
 * @param {string} key 键
 * @param {any} defaultValue 默认值
 * @returns {any}
 */
export function getStorage(key, defaultValue = null) {
  try {
    const value = localStorage.getItem(key)
    return value ? JSON.parse(value) : defaultValue
  } catch (e) {
    console.error('读取失败:', e)
    return defaultValue
  }
}

/**
 * 移除localStorage
 * @param {string} key 键
 */
export function removeStorage(key) {
  try {
    localStorage.removeItem(key)
  } catch (e) {
    console.error('移除失败:', e)
  }
}

/**
 * 清除所有localStorage
 */
export function clearStorage() {
  try {
    localStorage.clear()
  } catch (e) {
    console.error('清除失败:', e)
  }
}

export default {
  formatAmount,
  formatDate,
  formatRelativeTime,
  maskPhone,
  maskEmail,
  randomString,
  isEmpty,
  isNullOrZero,
  debounce,
  throttle,
  deepClone,
  setStorage,
  getStorage,
  removeStorage,
  clearStorage
}
