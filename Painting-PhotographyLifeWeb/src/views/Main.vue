<template>
  <div class="main">
    <div class="block">
      <el-carousel height="265px"  type="card" indicator-position="none">
        <el-carousel-item v-for="item in 4" :key="item">
          <el-image
                class="radius shadow"
                style="width: 100%; height: 265px"
                :src="'/img/ef1b0509f201362abfc69e6a31e618323e07e73f.jpg'"
                fit="cover"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="blur">
    </div>
  </div>
</template>

<script>
import VueWaterfallEasy from 'vue-waterfall-easy';

export default {
  components:{
    VueWaterfallEasy
  }
}
</script>

<style scoped>
.main{
  overflow: hidden;
  min-width: 630px;
  margin: 20px 10%;
  position: relative;
  /* background-color: rgba(242,243,245,.8); */
  /* min-height: 100vh; */
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.blur {
  margin-top: 20px;
  position: relative;
  min-height: 100vh;
  border-radius: 5px;
  overflow: hidden;
}
.blur:before{
  background-image: url(../assets/img/d4fab7a666bc627cc2bcb4f00aa6d2a7958cee80.png@1366w.jpg);
}
.blur:after{
  background-color: rgba(242,243,245,.3);
}
.block{
  padding: 20px;
}
</style>
