import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 用户信息
    userInfo: null,
    // 购物车数量
    cartCount: 0,
    // 登录状态
    isLoggedIn: false
  },

  mutations: {
    /**
     * 设置用户信息
     */
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo
      state.isLoggedIn = !!userInfo
    },

    /**
     * 设置购物车数量
     */
    setCartCount(state, count) {
      state.cartCount = count
    },

    /**
     * 清除用户信息
     */
    clearUserInfo(state) {
      state.userInfo = null
      state.isLoggedIn = false
      state.cartCount = 0
    }
  },

  actions: {
    /**
     * 登录
     */
    login({ commit }, userInfo) {
      commit('setUserInfo', userInfo)
      localStorage.setItem('userInfo', JSON.stringify(userInfo))
    },

    /**
     * 退出登录
     */
    logout({ commit }) {
      commit('clearUserInfo')
      localStorage.removeItem('userInfo')
      localStorage.removeItem('token')
    },

    /**
     * 初始化用户信息
     */
    initUserInfo({ commit }) {
      const userInfoStr = localStorage.getItem('userInfo')
      if (userInfoStr) {
        try {
          const userInfo = JSON.parse(userInfoStr)
          commit('setUserInfo', userInfo)
        } catch (e) {
          commit('clearUserInfo')
        }
      }
    },

    /**
     * 更新购物车数量
     */
    updateCartCount({ commit }, count) {
      commit('setCartCount', count)
    }
  },

  getters: {
    /**
     * 获取用户信息
     */
    getUserInfo: state => state.userInfo,

    /**
     * 获取登录状态
     */
    getIsLoggedIn: state => state.isLoggedIn,

    /**
     * 获取购物车数量
     */
    getCartCount: state => state.cartCount
  }
})
