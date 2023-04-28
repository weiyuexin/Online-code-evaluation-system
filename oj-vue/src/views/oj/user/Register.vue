<template>
  <el-scrollbar height="710px">
    <div class="main">

      <el-row>
        <el-col :span="14" class="left">
          <el-image fit="cover" src="https://cs.henu.edu.cn/img/minglundalitang2.jpg"></el-image>
        </el-col>
        <el-col :span="10" class="right">
          <el-row class="my-row">
            <el-col :span="24" class="head">
              HENU-OJ
            </el-col>
            <el-col :span="24" class="form">
              <el-form
                  label-position="top"
                  label-width="100px"
                  :model="user"
                  style="max-width: 460px"
                  class="my-form"
              >
                <el-form-item>
                  <el-input size="large" placeholder="用户名" v-model="user.username"/>
                </el-form-item>
                <el-form-item>
                  <el-input size="large" placeholder="邮箱" v-model="user.email"/>
                </el-form-item>
                <el-form-item class="emailCode">
                  <el-input class="emailCodeInput" size="large" placeholder="邮箱验证码" v-model="user.emailCode"/>
                  <el-button class="emailCodeSend" @click="sendEmailCode" type="primary">发送</el-button>
                </el-form-item>
                <el-form-item>
                  <el-input size="large" placeholder="密码" v-model="user.password" show-password/>
                </el-form-item>
                <el-form-item>
                  <el-input size="large" placeholder="密码" v-model="user.password1" show-password/>
                </el-form-item>
                <el-button class="submit" @click="register" type="primary">
                  <el-icon>
                    <CircleCheck/>
                  </el-icon>
                  注册
                </el-button>
              </el-form>
            </el-col>
            <el-col :span="24" class="foot">
              <el-row>
                <el-col :span="6" class="toLogin">
                  <router-link :to="`/login`" :underline="false">返回登录</router-link>
                </el-col>
                <el-col :span="12">

                </el-col>
                <el-col :span="6" class="github">
                  <i class="fa fa-github"/>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

    </div>
  </el-scrollbar>
</template>

<script>
import {ElMessage} from 'element-plus'
import axios from "axios";
import qs from 'qs'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Register",
  data() {
    return {
      user: {
        username: "",
        email: "",
        emailCode: "",
        password: "",
        password1: "",
      }
    }
  },
  components: {},
  methods: {
    register() {
      if (this.user.username === "") {
        ElMessage({
          message: '用户名不能为空',
          type: 'warning',
        })
        return
      }
      if (this.user.email === "") {
        ElMessage({
          message: '邮箱不能为空',
          type: 'warning',
        })
        return
      }
      if (this.user.emailCode === "") {
        ElMessage({
          message: '邮箱验证码不能为空',
          type: 'warning',
        })
        return
      }
      if (this.user.password === "" || this.user.password1 === "") {
        ElMessage({
          message: '密码后确认密码不能为空',
          type: 'warning',
        })
        return
      }
      if (this.user.password !== this.user.password1) {
        ElMessage({
          message: '两次输入的密码不一致',
          type: 'warning',
        })
        return
      }
      let postData = qs.stringify({
        username: this.user.username,
        email: this.user.email,
        code: this.user.emailCode,
        password: this.user.password
      })
      axios.post("/api/user/register", postData)
          .then(response => {
            console.log(response.data)
            if (response.data.code === 200) {
              ElMessage({
                message: '注册成功',
                type: 'success',
              })
            } else {
              ElMessage({
                message: response.data.msg,
                type: 'error',
              })
            }
          })
          .catch(error => {
            ElMessage({
              message: '注册失败',
              type: 'warning',
            })
            console.log(error);
          })
    },
    sendEmailCode() {
      if (this.user.email === "") {
        ElMessage({
          message: '邮箱不能为空',
          type: 'warning',
        })
        return
      }
      // 发送邮件验证码
      axios.post("/api/email/send/" + this.user.email)
          .then(response => {
            ElMessage({
              message: '验证码发送成功，请前往邮箱查看',
              type: 'success',
            })
            console.log(response.data)
          })
          .catch(error => {
            ElMessage({
              message: '验证码发送失败，请查看邮箱输入是否正确',
              type: 'warning',
            })
            console.log(error);
          })
    },
  },
}
</script>

<style scoped>
.main {
  padding-top: 5%;
  width: 70%;
  margin: 0 auto;
  height: 650px;
}

.left {
  height: 500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
}

.left .el-image {
  width: 100%;
  height: 100%;
}

.right {
  height: 500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
}

.right .my-row {
  height: 100%;
}

.right .head {
  height: 13%;
  margin: 0 auto;
  text-align: center;
  line-height: 90px;
  font-size: 30px;
  font-weight: bold;
  color: #409eff;
}

.right .form {
  height: 75%;
  margin: 0 auto;
  padding: 20px 20px;
  text-align: center;
}

.right .form .my-form {
  height: 100%;
  width: 100%;
  margin: 0 auto;
  text-align: center;
}

.right .form .my-form .emailCodeInput {
  width: 75%;
}

.right .form .my-form .emailCodeSend {
  float: left;
  margin-left: 20px;
  width: 20%;
  height: 37px;
}

.right .form .my-form .submit {
  width: 100%;
  height: 37px;
}

.right .foot {
  height: 12%;
  padding: 0px 20px;
}

.right .foot .toLogin {
  text-align: left;
  padding-left: 3px;
}

.right .foot .github {
  font-size: 28px;
  padding-right: 3px;
  text-align: right;
}
</style>