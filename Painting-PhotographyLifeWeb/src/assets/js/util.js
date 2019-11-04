import qs from "qs";
import { debounce } from "lodash";

export default {
  http,
  validFun
};

function http(that, url, data, fin, success, fail) {
  that.$http
    .post(url, qs.stringify(data))
    .then(function(response) {
      let res = response.data;

      if (res.data instanceof Array && res.status === -1) {
        res.data.forEach(item => {
          that.$message({
            showClose: true,
            message: item,
            type: "error"
          });
        });
      } else {
        that.$message({
          showClose: true,
          message: res.info,
          type: res.status === -1 ? "error" : "success"
        });
      }

      if (res.status === 1) {
        if (success) {
          success(res);
        }
      } else {
        if (fail) {
          fail(res);
        }
      }
    })
    .catch(function(error) {
      that.$message({
        showClose: true,
        message: "请求出错" + error.message,
        type: "error"
      });
    })
    .finally(fin);
}
function validFun(that, ref, success, fail, fun) {
  return debounce(() => {
    if (fun) {
      fun();
    }
    that.$refs[ref].validate((v, o) => {
      if (v) {
        success();
      } else {
        if (fail) {
          fail(o);
        }
        return false;
      }
    });
  }, 200);
}
