import Vue from "vue";
import Vuex from "vuex";
import ApiUrl from "../assets/js/apiurl.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    api: ApiUrl
  },
  mutations: {},
  actions: {},
  modules: {}
});
