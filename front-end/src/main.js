import Vue from 'vue'
import App from './App.vue'
import router from './router'
import * as echarts from 'echarts'
import 'remixicon/fonts/remixicon.css'
import MessagePlugin from './components/Message'
import Api from './api'

Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$api = Api

// 注册全局消息提示插件
Vue.use(MessagePlugin)


new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
