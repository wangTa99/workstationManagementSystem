/**
 * 全局过滤器
 */

/**
 * 金额格式化过滤器
 * @param {number} value 金额
 * @param {string} symbol 货币符号
 * @returns {string}
 */
export function currency(value, symbol = '¥') {
  if (value === null || value === undefined || isNaN(value)) {
    return `${symbol}0.00`
  }
  return `${symbol}${parseFloat(value).toFixed(2)}`
}

/**
 * 日期格式化过滤器
 * @param {Date|string|number} value 日期
 * @param {string} format 格式
 * @returns {string}
 */
export function dateFormat(value, format = 'YYYY-MM-DD') {
  if (!value) return ''

  const d = new Date(value)

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
 * 日期时间格式化过滤器
 * @param {Date|string|number} value 日期
 * @returns {string}
 */
export function dateTimeFormat(value) {
  return dateFormat(value, 'YYYY-MM-DD HH:mm:ss')
}

/**
 * 手机号脱敏过滤器
 * @param {string} phone 手机号
 * @returns {string}
 */
export function maskPhone(phone) {
  if (!phone) return ''
  return phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')
}

/**
 * 邮箱脱敏过滤器
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
 * 相对时间过滤器
 * @param {Date|string|number} value 日期
 * @returns {string}
 */
export function relativeTime(value) {
  if (!value) return ''

  const now = Date.now()
  const timestamp = new Date(value).getTime()
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
 * 文件大小格式化过滤器
 * @param {number} bytes 字节数
 * @returns {string}
 */
export function fileSize(bytes) {
  if (bytes === 0) return '0 B'

  const k = 1024
  const sizes = ['B', 'KB', 'MB', 'GB', 'TB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))

  return `${(bytes / Math.pow(k, i)).toFixed(2)} ${sizes[i]}`
}

/**
 * 订单状态过滤器
 * @param {number} status 状态码
 * @returns {string}
 */
export function orderStatus(status) {
  const statusMap = {
    0: '待付款',
    1: '待发货',
    2: '待收货',
    3: '已完成',
    4: '已取消',
    5: '已退款'
  }
  return statusMap[status] || '未知状态'
}

/**
 * 支付状态过滤器
 * @param {number} status 状态码
 * @returns {string}
 */
export function payStatus(status) {
  const statusMap = {
    0: '未支付',
    1: '已支付',
    2: '已退款'
  }
  return statusMap[status] || '未知状态'
}

/**
 * 商品状态过滤器
 * @param {number} status 状态码
 * @returns {string}
 */
export function productStatus(status) {
  const statusMap = {
    0: '下架',
    1: '上架',
    2: '售罄'
  }
  return statusMap[status] || '未知状态'
}

/**
 * 性别过滤器
 * @param {number} gender 性别码
 * @returns {string}
 */
export function gender(gender) {
  const genderMap = {
    0: '未知',
    1: '男',
    2: '女'
  }
  return genderMap[gender] || '未知'
}

/**
 * 百分数过滤器
 * @param {number} value 数值
 * @param {number} decimals 小数位数
 * @returns {string}
 */
export function percentage(value, decimals = 2) {
  if (value === null || value === undefined || isNaN(value)) {
    return '0%'
  }
  return `${parseFloat(value).toFixed(decimals)}%`
}

/**
 * 文字省略过滤器
 * @param {string} value 文字
 * @param {number} length 保留长度
 * @param {string} suffix 后缀
 * @returns {string}
 */
export function truncate(value, length = 50, suffix = '...') {
  if (!value) return ''
  if (value.length <= length) return value
  return value.substring(0, length) + suffix
}

// 导出所有过滤器
export default {
  currency,
  dateFormat,
  dateTimeFormat,
  maskPhone,
  maskEmail,
  relativeTime,
  fileSize,
  orderStatus,
  payStatus,
  productStatus,
  gender,
  percentage,
  truncate
}
