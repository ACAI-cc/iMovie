import { getToken, setToken, removeToken } from '@/utils/token'
import login from '@/api/login'

const state ={
  token:getToken()
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  }
}

const actions = {
  // user login
  async login({ commit }, userInfo) {
      let result = await login(userInfo);

      if (result.code == 200) {
        commit('SET_TOKEN', result.data.token)
        setToken(result.data.token)
      }else{
        return Promise.reject(new Error('faile'))
      }

  },

}

const getters = {

}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}

