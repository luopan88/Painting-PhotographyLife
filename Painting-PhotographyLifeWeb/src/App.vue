<template>
  <div id="app">
    <transition name="el-fade-in" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style></style>
