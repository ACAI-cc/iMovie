import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui'
import store from './store';

Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  
  // 全局事件总线配置
  beforeCreate() {
    Vue.prototype.$bus = this;
  },

  router,
  store,
  render: h => h(App)
}).$mount('#app')
