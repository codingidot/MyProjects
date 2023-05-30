import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
export default createStore({
  plugins: [
    createPersistedState()
  ],
  state: {
    id: null
  },
  getters: {
    // 회원정보 불러오기
    getID (state) {
      return state.id
    }
  },
  mutations: {
    // 로그인 성공시 state에 아이디 담아줌
    loginSuccess (state, id) {
      state.id = id
    }
  },
  actions: {
  },
  modules: {
  }
})
