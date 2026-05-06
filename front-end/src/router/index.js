import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/home/Home.vue'),
    meta: { title: '首页' }
  },
  {
    path: '/category',
    name: 'Category',
    component: () => import('@/views/category/Category.vue'),
    meta: { title: '分类' }
  },
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('@/views/cart/Cart.vue'),
    meta: { title: '购物车' }
  },
  {
    path: '/user',
    name: 'User',
    component: () => import('@/views/user/User.vue'),
    meta: { title: '我的' }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/user/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/user/Register.vue'),
    meta: { title: '注册' }
  },
  {
    path: '/product/:id',
    name: 'ProductDetail',
    component: () => import('@/views/product/ProductDetail.vue'),
    meta: { title: '商品详情' }
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import('@/views/product/Search.vue'),
    meta: { title: '搜索' }
  },
  {
    path: '/order',
    name: 'OrderList',
    component: () => import('@/views/order/OrderList.vue'),
    meta: { title: '我的订单' }
  },
  {
    path: '/order/:id',
    name: 'OrderDetail',
    component: () => import('@/views/order/OrderDetail.vue'),
    meta: { title: '订单详情' }
  },
  {
    path: '/checkout',
    name: 'Checkout',
    component: () => import('@/views/order/Checkout.vue'),
    meta: { title: '确认订单' }
  },
  {
    path: '/pay/:id',
    name: 'Pay',
    component: () => import('@/views/order/Pay.vue'),
    meta: { title: '订单支付' }
  },
  {
    path: '/address',
    name: 'AddressList',
    component: () => import('@/views/user/AddressList.vue'),
    meta: { title: '收货地址' }
  },
  {
    path: '/collect',
    name: 'Collect',
    component: () => import('@/views/user/Collect.vue'),
    meta: { title: '我的收藏' }
  },
  {
    path: '/coupon',
    name: 'Coupon',
    component: () => import('@/views/user/Coupon.vue'),
    meta: { title: '我的优惠券' }
  },
  {
    path: '/comment/list/:id?',
    name: 'CommentList',
    component: () => import('@/views/order/CommentList.vue'),
    meta: { title: '我的评价' }
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

// 解决重复路由跳转报错问题
const originalPush = VueRouter.prototype.push
const originalReplace = VueRouter.prototype.replace

VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) {
    return originalPush.call(this, location, onResolve, onReject)
  }
  return originalPush.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') {
      throw err
    }
  })
}

VueRouter.prototype.replace = function replace(location, onResolve, onReject) {
  if (onResolve || onReject) {
    return originalReplace.call(this, location, onResolve, onReject)
  }
  return originalReplace.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') {
      throw err
    }
  })
}

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  document.title = to.meta.title || '应用'
  next()
})

export default router
