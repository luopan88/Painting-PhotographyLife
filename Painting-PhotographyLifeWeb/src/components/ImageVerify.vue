<template>
  <el-row :gutter="20" type="flex" class="row">
    <el-col :span="12">
      <el-input
        placeholder="请输入验证码"
        @focus="verifyimgisshow"
        :value="value"
        @input="emitvalue"
      ></el-input>
    </el-col>
    <el-col :span="10">
      <transition name="el-fade-in">
        <el-tooltip
          content="点击图片刷新"
          placement="top"
          v-show="verifyimgshow"
        >
          <el-image
            style="width: 75px; height: 29px"
            :src="imgurl"
            fit="fill"
            @click="reflushVerifyPicDebounce"
            placeholder="加载中..."
          ></el-image>
        </el-tooltip>
      </transition>
    </el-col>
  </el-row>
</template>

<script>
import { debounce } from "lodash";
export default {
  props: ["value", "basesrc"],
  data() {
    return {
      t: "",
      verifyimgshow: false
    };
  },
  computed: {
    imgurl() {
      if (this.t === "") return "";
      return this.basesrc + "?t=" + this.t;
    }
  },
  created: function() {
    this.reflushVerifyPicDebounce = debounce(this.reflushVerifyPic, 200);
  },
  methods: {
    reflushVerifyPic() {
      this.t = new Date().getTime();
    },
    verifyimgisshow() {
      if (!this.verifyimgshow) {
        this.reflushVerifyPic();
        this.verifyimgshow = true;
      }
    },
    emitvalue(value){
      this.$emit('input', value)
    }
  }
};
</script>

<style scoped>
.row {
  height: 29px;
}
.but {
  display: block;
  width: 100%;
  margin: 0 auto;
}
</style>
