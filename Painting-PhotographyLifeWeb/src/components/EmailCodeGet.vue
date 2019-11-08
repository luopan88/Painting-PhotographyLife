<template>
  <div>
    <el-button @click="dialogVisible = true" size="mini" type="primary" plain
      >获取验证码</el-button
    >
    <el-dialog
      :visible.sync="dialogVisible"
      :modal-append-to-body="false"
      :modal="false"
      center
      custom-class="imgdialog"
      destroy-on-close
      :close-on-click-modal="false"
      @closed="form.imgcode = ''"
    >
      <!-- <el-form size="mini" :rules="rules" :model="form" ref="form"> -->
      <el-form size="mini" :rules="rules" :model="form" ref="form">
        <el-form-item prop="email" hidden>
          <el-input type="email" :value="email"></el-input>
        </el-form-item>
        <el-form-item prop="imgcode">
          <ImageVerify
            v-model="form.imgcode"
            :basesrc="verifyimageurl"
            :rand="r"
          ></ImageVerify>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            class="but"
            @click="send"
            :disabled="ldisabled"
            >发送验证码</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import ImageVerify from "./ImageVerify.vue";
import util from "../assets/js/util.js";

export default {
  components: {
    ImageVerify
  },
  props: ["email"],
  data() {
    return {
      r: "",
      ldisabled: false,
      verifyimageurl: this.$store.state.api.verifyPic,
      dialogVisible: false,
      form: {
        email: "",
        imgcode: ""
      },
      rules: {
        email: [
          {
            required: true,
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: "blur"
          }
        ],
        imgcode: [
          { required: true, message: "请输入图片验证码", trigger: "blur" }
        ]
      }
    };
  },
  created: function() {
    let that = this;
    this.send = util.validFun(
      this,
      "form",
      this.send,
      o => {
        if (o.email) {
          that.$message({
            showClose: true,
            message: o.email[0].message,
            type: "error"
          });
        }
      },
      () => {
        that.form.email = that.email;
      }
    );
  },
  methods: {
    send() {
      this.ldisabled = true;
      let that = this;
      util.http(
        this,
        this.$store.state.api.emailVerify,
        this.form,
        () => {
          that.r = new Date().getTime();
          that.ldisabled = false;
        },
        () => {
          that.dialogVisible = false;
          that.form.imgcode = "";
        }
      );
    }
  }
};
</script>

<style>
.but {
  display: block;
  width: 100%;
  margin: 0 auto;
}
.imgdialog {
  background-color: rgba(235, 238, 245, 0.8) !important;
  max-width: 260px;
  min-width: 200px;
  border-radius: 4px !important;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04) !important;
}
</style>
