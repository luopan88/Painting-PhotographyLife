import Vue from "vue";
import axios from "axios";

// axios.defaults.timeout = 5000;
axios.defaults.withCredentials = true;
Vue.prototype.$http = axios;
