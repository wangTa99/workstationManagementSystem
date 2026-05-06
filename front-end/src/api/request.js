import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:18007/api',
    timeout: 10000
})

// 请求拦截器
request.interceptors.request.use(
    config => {
        // 从 localStorage 读取 token 并添加到请求头
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['token'] = token
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
request.interceptors.response.use(
    response => {
        return response.data
    },
    error => {
        return Promise.reject(error)
    }
)

// 用于下载的请求（保留原始响应，包含headers）
export const downloadRequest = axios.create({
    baseURL: 'http://localhost:18007/api',
    timeout: 60000,
    responseType: 'blob'
})

// 请求拦截器 - 添加token
downloadRequest.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['token'] = token
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

export default request
