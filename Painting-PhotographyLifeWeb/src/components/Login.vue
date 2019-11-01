<template>
  <div>
  <transition name="el-fade-in" mode="out-in">
    <el-form
      ref="form"
      :model="form"
      size="mini"
      label-width="60px"
      label-position="top"
      class="form"
      v-if="islogin"
      key="lform"
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
        <el-button type="primary" :disabled="ldisabled" @click="login" class="but">登录</el-button>
      </el-form-item>
      <el-form-item>
          <el-row :gutter="24" type="flex">
            <el-col :span="12">
        <el-button class="but" @click="islogin=false">创建账号</el-button>
            </el-col>
            <el-col :span="12">
        <el-link type="primary">找回密码</el-link>
            </el-col>
          </el-row>
      </el-form-item>
    </el-form>

    <el-form
      ref="regform"
      :model="regform"
      size="mini"
      label-width="60px"
      label-position="top"
      class="form"
      v-else
      key="rform"
    >
      <el-form-item label="昵称:" prop="name">
        <el-input
          type="email"
          v-model="regform.name"
          placeholder="请输入账户昵称"
        ></el-input>
      </el-form-item>
      <el-form-item label="邮箱:" prop="username">
        <el-input
          type="email"
          v-model="regform.username"
          placeholder="请输入邮箱地址"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码:">
        <el-input
          v-model="regform.password"
          show-password
          placeholder="请输入登录密码"
        ></el-input>
      </el-form-item>

      <el-form-item label="邮箱验证码:">
        <el-row :gutter="20" type="flex" class="row">
          <el-col :span="12">
            <el-input
              v-model="regform.imgcode"
              placeholder="邮箱请输入验证码"
              @focus="verifyimgisshow"
            ></el-input>
          </el-col>
          <el-col :span="10">
            
          </el-col>
        </el-row>
      </el-form-item>
      
      <el-form-item>
          <el-row :gutter="24" type="flex" class="row">
            <el-col :span="12">
        <el-button type="primary" @click="login" class="but">创建</el-button>
            </el-col>
            <el-col :span="12">
        <!-- <el-link type="primary" @click="islogin=true">已有帐号?</el-link> -->
        <el-button class="but" @click="islogin=true">已有帐号</el-button>
            </el-col>
          </el-row>
      </el-form-item>
    </el-form>
    </transition>
  </div>
</template>

<script>
import { debounce } from "lodash";
// var timestamp = new Date().getTime();
import ImageVerify from "./ImageVerify.vue"
export default {
  components:{
    ImageVerify
  },
  data() {
    return {
      // imgurl: "https://static.oschina.net/uploads/img/201712/16113708_B8Hu.png"
      t: "",
      form: {
        username: "",
        password: "",
        imgcode: ""
      },
      regform: {
        name: "",
        username: "",
        password: "",
        imgcode: ""
      },
      verifyimgshow: false,
      islogin: true,
      ldisabled: false
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
      this.ldisabled = true;
      let that = this;
      this.$http
        .post(this.$store.state.api.login, this.form)
        .then(function(response) {
          let res = response.data;
          that.$message({
            showClose: true,
            message: res.info,
            type: res.status === -1 ? "error" : "success"
          });
        })
        .catch(function(error) {
          // alert(error);
          that.$message({
            showClose: true,
            message: error.message,
            type: "error"
          });
        })
        .finally(function(){
          that.ldisabled = false;
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

<style scoped>
.row {
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
