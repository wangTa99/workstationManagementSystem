import request from './request'

const api = {
    // 示例：获取用户信息
    getUserInfo(params) {
        return request.get('/user/info', { params })
    },

    // 示例：登录
    login(data) {
        return request.post('/login', data)
    },

    // 示例：获取列表
    getList(params) {
        return request.get('/list', { params })
    },

    // 示例：文件上传、图片上传等
    // 接口返回数据格式如下：
    // {
    //     "code": 200,
    //     "data": {
    //         "url": "http://localhost:18007/uploads/2026/03/08/e47604e9d81d488abd55426cea1c1a28.png"
    //     },
    //     "message": "上传成功"
    // }
    upload(file) {
        const formData = new FormData()
        formData.append('file', file)
        return request.post('/file/upload', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}

export default api
