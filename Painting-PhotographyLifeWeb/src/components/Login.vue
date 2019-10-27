<template>
  <el-form
    ref="form"
    :model="form"
    size="mini"
    label-width="60px"
    label-position="top"
    class="form"
  >
    <el-form-item label="账号:" prop="username">
      <el-input
        type="email"
        v-model="form.username"
        placeholder="请输入登录邮箱"
      ></el-input>
    </el-form-item>
    <el-form-item label="密码:">
      <el-input
        v-model="form.password"
        show-password
        placeholder="请输入登录密码"
      ></el-input>
    </el-form-item>

    <el-form-item label="验证码:">
      <el-row :gutter="20" type="flex" class="row">
        <el-col :span="12">
          <el-input
            v-model="form.imgcode"
            placeholder="请输入验证码"
            @focus="verifyimgisshow"
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
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="login" class="but">登录</el-button>
    </el-form-item>
    <el-form-item>
      <el-button class="but">创建</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { debounce } from "lodash";
// var timestamp = new Date().getTime();
export default {
  data() {
    return {
      // imgurl: "https://static.oschina.net/uploads/img/201712/16113708_B8Hu.png"
      t: "",
      form: {
        username: "",
        password: "",
        imgcode: ""
      },
      verifyimgshow: false
    };
  },
  computed: {
    imgurl() {
      if (this.t === "") return "";
      return this.$store.state.api.verifyPic + "?t=" + this.t;
    }
  },
  created: function() {
    this.reflushVerifyPicDebounce = debounce(this.reflushVerifyPic, 200);
  },
  methods: {
    reflushVerifyPic() {
      this.t = new Date().getTime();
    },
    login() {
      let that = this;
      console.log(this.form);
      this.$http
        .post(this.$store.state.api.login, this.form)
        .then(function(response) {
          let res = response.data;
          that.$message(res.info);
        })
        .catch(function(error) {
          alert(error);
        });
    },
    verifyimgisshow() {
      if (!this.verifyimgshow) {
        this.reflushVerifyPic();
        this.verifyimgshow = true;
      }
    }
  }
};
</script>

<style>
  .row{
    height: 29px;
  }
.but {
  display: block;
  width: 100%;
  margin: 0 auto;
}
.form {
  /* background-color: #EBEEF5; */
  background-color: rgba(235, 238, 245, 0.7);
  max-width: 300px;
  min-width: 200px;
  padding: 24px;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  margin: auto;
}
</style>
