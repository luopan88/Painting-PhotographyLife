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

<style>
#app {
  background-color: transparent;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  max-width: 1020px;
  font-family: "Microsoft YaHei", "Microsoft Sans Serif", "Microsoft SanSerf",
    "\5FAE\8F6F\96C5\9ED1";
}
</style>
