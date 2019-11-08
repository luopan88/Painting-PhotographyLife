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
        :rules="formRules"
        hide-required-asterisk
        status-icon
      >
        <el-form-item label="账号:" prop="username">
          <el-input
            type="email"
            v-model="form.username"
            placeholder="请输入登录邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input
            v-model="form.password"
            show-password
            placeholder="请输入登录密码"
          ></el-input>
        </el-form-item>

        <el-form-item label="验证码:" prop="imgcode">
          <ImageVerify
            v-model="form.imgcode"
            :basesrc="verifyimageurl"
            :rand="r"
          ></ImageVerify>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            :disabled="ldisabled"
            @click="login"
            class="but"
            >登录</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-row :gutter="24" type="flex">
            <el-col :span="12">
              <el-button class="but" @click="islogin = false"
                >创建账号</el-button
              >
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
        :rules="regformRules"
        size="mini"
        label-width="60px"
        label-position="top"
        class="form"
        v-else
        key="rform"
        hide-required-asterisk
        status-icon
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
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input
            v-model="regform.password"
            show-password
            placeholder="请输入登录密码"
            autocomplete="new-password"
          ></el-input>
        </el-form-item>

        <el-form-item label="邮箱验证码:" prop="emailcode">
          <el-row :gutter="24" type="flex" class="row">
            <el-col :span="12">
              <el-input
                v-model="regform.emailcode"
                placeholder="请输入邮箱验证码"
              ></el-input>
            </el-col>
            <el-col :span="10">
              <EmailCodeGet :email="regform.username"></EmailCodeGet>
            </el-col>
          </el-row>
        </el-form-item>

        <el-form-item>
          <el-row :gutter="24" type="flex">
            <el-col :span="12">
              <el-button type="primary" @click="regist" class="but"
                >创建</el-button
              >
            </el-col>
            <el-col :span="12">
              <!-- <el-link type="primary" @click="islogin=true">已有帐号?</el-link> -->
              <el-button class="but" @click="islogin = true"
                >已有帐号</el-button
              >
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </transition>
  </div>
</template>

<script>
import ImageVerify from "./ImageVerify.vue";
import EmailCodeGet from "./EmailCodeGet.vue";
import util from "../assets/js/util.js";

export default {
  components: {
    ImageVerify,
    EmailCodeGet
  },
  data() {
    var userIsExist = (rule, value, callback) => {
      this.$http
        .get(this.$store.state.api.userIsExist + this.regform.username)
        .then(res => {
          let mes = res.data;
          if (mes.status === 1) {
            callback(new Error("用户已存在"));
          } else {
            callback();
          }
        })
        .catch(() => {
          callback(new Error("从服务器验证用户名失败时出错"));
        });
    };
    return {
      r: "",
      verifyimageurl: this.$store.state.api.verifyPic,
      form: {
        username: "",
        password: "",
        imgcode: ""
      },
      formRules: {
        username: [
          {
            required: true,
            type: "email",
            message: "请输入登陆邮箱",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "请输入登陆密码", trigger: "blur" }
        ],
        imgcode: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      },
      regform: {
        name: "",
        username: "",
        password: "",
        emailcode: ""
      },
      regformRules: {
        name: [{ required: true, message: "请输入昵称", trigger: "blur" }],
        username: [
          {
            required: true,
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: "blur"
          },
          { validator: userIsExist, trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入登陆密码", trigger: "blur" }
        ],
        emailcode: [
          { required: true, message: "请输入验证码", trigger: "blur" }
        ]
      },
      islogin: true,
      ldisabled: false,
      rdisabled: false
    };
  },
  created: function() {
    this.login = util.validFun(this, "form", this.login);
    this.regist = util.validFun(this, "regform", this.regist);
  },
  methods: {
    login() {
      this.ldisabled = true;
      let that = this;
      util.http(that, this.$store.state.api.login, this.form, () => {
        that.r = new Date().getTime();
        that.ldisabled = false;
        that.form.imgcode = "";
      });
    },
    regist() {
      this.rdisabled = true;
      let that = this;
      util.http(this, this.$store.state.api.regist, this.regform, () => {
        that.rdisabled = false;
        that.regform.emailcode = "";
      });
    }
  }
};
</script>

<style scoped>
.but {
  display: block;
  width: 100%;
  margin: 0 auto;
}
.form {
  /* background-color: #EBEEF5; */
  background-color: rgba(235, 238, 245, 0.8);
  max-width: 300px;
  min-width: 200px;
  padding: 24px;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  margin: auto;
}
</style>
