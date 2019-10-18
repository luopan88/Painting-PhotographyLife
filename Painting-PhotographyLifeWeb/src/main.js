import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import Vuex from 'vuex'
import './assets/css/main.css'

Vue.use(Vuex)
Vue.use(ElementUI)

const store = new Vuex.Store({
	state: {
		test: "test"
	}
})

new Vue({
  el: '#app',
  render: h => h(App),
  store
})
