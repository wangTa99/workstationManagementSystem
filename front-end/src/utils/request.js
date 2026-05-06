/**
 * 请求封装工具
 * 基于axios封装统一的请求处理
 */
import axios from 'axios'
import { Toast } from 'vant'

// 创建axios实例
const service = axios.create({
  // 基础URL
  baseURL: process.env.VUE_APP_API_BASE_URL || '/api',
  // 请求超时时间
  timeout: 30000,
  // 请求头
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 添加token到请求头
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }

    // 添加时间戳防止缓存
    if (config.method === 'get') {
      config.params = {
        ...config.params,
        _t: Date.now()
      }
    }

    // 显示加载状态
    if (config.showLoading !== false) {
      Toast.loading({
        message: '加载中...',
        forbidClick: true,
        loadingType: 'spinner',
        duration: 0
      })
    }

    return config
  },
  error => {
    Toast.clear()
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    Toast.clear()

    const res = response.data

    // 根据响应码处理
    if (res.code === 200) {
      return res
    } else if (res.code === 401) {
      // 登录过期，清除token并跳转到登录页
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
      Toast.fail('登录已过期，请重新登录')
      window.location.href = '/#/login'
      return Promise.reject(new Error(res.message || '未授权'))
    } else if (res.code === 403) {
      Toast.fail('无权限访问')
      return Promise.reject(new Error(res.message || '无权限'))
    } else if (res.code === 404) {
      Toast.fail('请求的资源不存在')
      return Promise.reject(new Error(res.message || '未找到'))
    } else if (res.code === 500) {
      Toast.fail('服务器错误')
      return Promise.reject(new Error(res.message || '服务器错误'))
    } else {
      Toast.fail(res.message || '请求失败')
      return Promise.reject(new Error(res.message || '请求失败'))
    }
  },
  error => {
    Toast.clear()

    if (error.response) {
      // 服务器返回错误状态码
      switch (error.response.status) {
        case 400:
          Toast.fail('请求参数错误')
          break
        case 401:
          localStorage.removeItem('token')
          localStorage.removeItem('userInfo')
          Toast.fail('登录已过期，请重新登录')
          window.location.href = '/#/login'
          break
        case 403:
          Toast.fail('无权限访问')
          break
        case 404:
          Toast.fail('请求的资源不存在')
          break
        case 500:
          Toast.fail('服务器错误')
          break
        default:
          Toast.fail('请求失败')
      }
    } else if (error.code === 'ECONNABORTED') {
      Toast.fail('请求超时，请稍后重试')
    } else {
      Toast.fail('网络错误，请检查网络连接')
    }

    return Promise.reject(error)
  }
)

/**
 * 封装GET请求
 * @param {string} url 请求URL
 * @param {object} params 请求参数
 * @param {object} config 其他配置
 * @returns {Promise}
 */
export function get(url, params = {}, config = {}) {
  return service.get(url, {
    params,
    ...config
  })
}

/**
 * 封装POST请求
 * @param {string} url 请求URL
 * @param {object} data 请求数据
 * @param {object} config 其他配置
 * @returns {Promise}
 */
export function post(url, data = {}, config = {}) {
  return service.post(url, data, config)
}

/**
 * 封装PUT请求
 * @param {string} url 请求URL
 * @param {object} data 请求数据
 * @param {object} config 其他配置
 * @returns {Promise}
 */
export function put(url, data = {}, config = {}) {
  return service.put(url, data, config)
}

/**
 * 封装DELETE请求
 * @param {string} url 请求URL
 * @param {object} params 请求参数
 * @param {object} config 其他配置
 * @returns {Promise}
 */
export function del(url, params = {}, config = {}) {
  return service.delete(url, {
    params,
    ...config
  })
}

/**
 * 封装文件上传请求
 * @param {string} url 请求URL
 * @param {FormData} formData 表单数据
 * @param {function} onUploadProgress 上传进度回调
 * @returns {Promise}
 */
export function upload(url, formData, onUploadProgress) {
  return service.post(url, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    onUploadProgress
  })
}

export default {
  get,
  post,
  put,
  del,
  upload
}
