<template>
  <div id="app">
    <transition name="el-fade-in" mode="out-in">
      <TopMenu v-if="!index"></TopMenu>
    </transition>
    <transition name="el-fade-in" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import TopMenu from "./components/TopMenu.vue"
export default {
  name: "app",
  components:{
    TopMenu
  },
  data(){
    return {
      index: true
    }
  },
  watch:{
    $route: {
      handler: function(to){
        if (to.path !== "/" && to.path !== "/index") {
          this.index = false;
        } else if (to.path === "/" || to.path === "/index") {
          this.index = true;
        }
      },
      // 深度观察监听
      deep: true
    }
  }
};
</script>

<style>
</style>
