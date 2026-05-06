/**
 * 格式化工具
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */

/**
 * 格式化金额
 * @param {number} price - 价格
 * @param {string} symbol - 货币符号
 * @returns {string}
 */
function formatPrice(price, symbol = '¥') {
  if (!price && price !== 0) return ''
  return `${symbol}${(price / 100).toFixed(2)}`
}

/**
 * 格式化日期
 * @param {Date|string|number} date - 日期
 * @param {string} format - 格式
 * @returns {string}
 */
function formatDate(date, format = 'YYYY-MM-DD HH:mm:ss') {
  if (!date) return ''

  const d = new Date(date)
  const year = d.getFullYear()
  const month = String(d.getMonth() + 1).padStart(2, '0')
  const day = String(d.getDate()).padStart(2, '0')
  const hour = String(d.getHours()).padStart(2, '0')
  const minute = String(d.getMinutes()).padStart(2, '0')
  const second = String(d.getSeconds()).padStart(2, '0')

  return format
    .replace('YYYY', year)
    .replace('MM', month)
    .replace('DD', day)
    .replace('HH', hour)
    .replace('mm', minute)
    .replace('ss', second)
}

/**
 * 格式化相对时间
 * @param {Date|string|number} date - 日期
 * @returns {string}
 */
function formatRelativeTime(date) {
  if (!date) return ''

  const now = Date.now()
  const timestamp = new Date(date).getTime()
  const diff = now - timestamp

  const minute = 60 * 1000
  const hour = 60 * minute
  const day = 24 * hour

  if (diff < minute) {
    return '刚刚'
  } else if (diff < hour) {
    return Math.floor(diff / minute) + '分钟前'
  } else if (diff < day) {
    return Math.floor(diff / hour) + '小时前'
  } else if (diff < 7 * day) {
    return Math.floor(diff / day) + '天前'
  } else {
    return formatDate(date, 'YYYY-MM-DD')
  }
}

/**
 * 格式化手机号
 * @param {string} phone - 手机号
 * @returns {string}
 */
function formatPhone(phone) {
  if (!phone) return ''
  return phone.replace(/(\d{3})(\d{4})(\d{4})/, '$1****$3')
}

/**
 * 隐藏手机号中间4位
 * @param {string} phone - 手机号
 * @returns {string}
 */
function hidePhone(phone) {
  return formatPhone(phone)
}

/**
 * 格式化订单状态
 * @param {string} status - 状态
 * @returns {string}
 */
function formatOrderStatus(status) {
  const statusMap = {
    pending: '待支付',
    paid: '已支付',
    shipped: '待收货',
    completed: '已完成',
    cancelled: '已取消',
    refunding: '退款中',
    refunded: '已退款'
  }
  return statusMap[status] || status
}

/**
 * 截断文本
 * @param {string} text - 文本
 * @param {number} length - 长度
 * @param {string} suffix - 后缀
 * @returns {string}
 */
function truncate(text, length = 50, suffix = '...') {
  if (!text) return ''
  if (text.length <= length) return text
  return text.substring(0, length) + suffix
}

module.exports = {
  formatPrice,
  formatDate,
  formatRelativeTime,
  formatPhone,
  hidePhone,
  formatOrderStatus,
  truncate
}
