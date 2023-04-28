<template>
  <div class="main">
    <NavBar></NavBar>
    <el-tabs
        v-model="activeName"
        @tab-click="handleClick"
        class="demo-tabs"
        tab-position="left"
        :stretch="true"
    >
      <el-tab-pane label="个人信息">
        <el-descriptions
            class="margin-top"
            :column="1"
            size="10px"
            border
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <user/>
                </el-icon>
                用户名
              </div>
            </template>
            {{ user.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                 <Male />
                </el-icon>
                性别
              </div>
            </template>
            {{user.sex}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <View />
                </el-icon>
                个人介绍
              </div>
            </template>
            {{user.introduction}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <Cellphone />
                </el-icon>
                电子邮箱
              </div>
            </template>
            {{user.email}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <Memo />
                </el-icon>
                提交次数
              </div>
            </template>
            {{user.submitNum}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                 <DataAnalysis />
                </el-icon>
                解题总数
              </div>
            </template>
            {{user.solvedNum}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <office-building/>
                </el-icon>
                学校
              </div>
            </template>
            {{user.school}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                 <Finished />
                </el-icon>
                注册时间
              </div>
            </template>
            {{user.registerTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <Refresh />
                </el-icon>
                上次登录时间
              </div>
            </template>
            {{user.accessTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <PieChart />
                </el-icon>
                默认使用的语言
              </div>
            </template>
            {{user.language}}
          </el-descriptions-item>
        </el-descriptions>
      </el-tab-pane>
      <el-tab-pane label="修改密码" class="changePass">
        <el-form
            label-position="top"
            label-width="100px"
            style="max-width: 460px"
        >
          <el-form-item label="请输入旧密码">
            <el-input  type="password"  show-password v-model="password.oldpass" />
          </el-form-item>
          <el-form-item label="请输入新密码">
            <el-input  type="password"  show-password v-model="password.newpass1" />
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input  type="password" v-model="password.newpass2" />
          </el-form-item>
          <el-button type="primary" @click="change">
            确认修改
          </el-button>
        </el-form>

      </el-tab-pane>
      <el-tab-pane label="个人资料设置" class="changePass">
        <el-form
            label-position="top"
            label-width="100px"
            style="max-width: 460px"
        >
          <el-form-item label="用户名">
            <el-input  type="text"  show-password v-model="password.oldpass" />
          </el-form-item>
          <el-form-item label="简介">
            <el-input  type="textarea"  show-password v-model="password.oldpass" />
          </el-form-item>
          <el-form-item label="学校">
            <el-input  type="text"  show-password v-model="password.oldpass" />
          </el-form-item>
          <el-form-item label="默认使用语言">
            <el-select v-model="language" class="m-2" placeholder="Select">
              <el-option
                  v-for="item in languages"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-button type="primary" @click="changeAccount">
            确认修改
          </el-button>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
import Cookies from "js-cookie";
import {ElMessage} from "element-plus";
import axios from "axios";
import qs from "qs";

export default {
  name: "UserPanel",
  data() {
    return {
      user: {},
      password:{
        oldpass:'',
        newpass1:"",
        newpass2:"",
      },
      language:"",
      languages:[
        {
          value: 'C',
          label: 'C',
        },
        {
          value: 'C++',
          label: 'C++',
        },
        {
          value: 'Java',
          label: 'Java',
        },
        {
          value: 'Python',
          label: 'Python',
        },
        {
          value: 'Golang',
          label: 'Golang',
        },
        {
          value: 'JavaScript',
          label: 'JavaScript',
        },
      ]
    };
  },
  components: {
    NavBar,
    Footer
  },
  methods: {
    getUser() {
      const user = Cookies.get('user')
      this.user = JSON.parse(user)
      console.log(user)
    },
    changeAccount(){
      ElMessage({
        message: '修改成功',
        type: 'success',
      })
    },
    change(){
      if (this.password.oldpass===""){
        ElMessage({
          message: '请输入原密码',
          type: 'error',
        })
        return
      }
      if (this.password.newpass1!==this.password.newpass2){
        ElMessage({
          message: '两次输入的密码不一样',
          type: 'error',
        })
        return
      }
      let postData = qs.stringify({
        id:this.user.id,
        password: this.password.oldpass,
        newPassword:this.password.newpass1
      })
      axios.put("/api/user/cwd", postData)
          .then(response => {
            console.log(response.data)
            if (response.data.code === 200) {
              ElMessage({
                message: '密码修改成功',
                type: 'success',
              })
              Cookies.set('user', JSON.stringify(response.data.data),'7d')
            } else {
              ElMessage({
                message: response.data.msg,
                type: 'error',
              })
            }
          })
          .catch(error => {
            ElMessage({
              message: '修改失败',
              type: 'warning',
            })
            console.log(error);
          })
    }
  },
  mounted() {
    this.getUser()
  }
}
</script>

<style scoped>
.main {
  background-color: #EFF3F5;
}

::v-deep .el-tabs__item {
  color: #7E7C77;
  height: 60px;
}

::v-deep .el-tabs__item.is-active {
  color: #15cbf3;
  background-color: transparent !important;
}

::v-deep .el-icon-arrow-left {
  color: #7E7C77;
}

::v-deep .el-icon-arrow-right {
  color: #7E7C77;
}

::v-deep .el-tabs__nav-wrap::after {
  height: 0;
}

::v-deep .el-tabs__active-bar {
  background-color: #15cbf3;
}
.iconStyle{
  margin-right: 10px;
}
.changePass{
  padding-top: 50px;
  padding-left: 50px;
}
.demo-tabs {
  margin-top: 60px;
  margin-left: 100px;
  margin-right: 100px;
  z-index: 999;
  height: 490px;
  background-color: #FFFFFF;
}

.el-descriptions {
  margin-top: 20px;
}

.cell-item {
  display: flex;
  align-items: center;
}

.margin-top {
  margin-top: 20px;
}

.main .el-tabs__item {
  padding: 0 !important;
}

.el-tabs__active-bar {
  background-color: transparent !important;
}

.demo-tabs > .el-tabs__content {
  padding: 32px;
  font-size: 32px;
  font-weight: 600;
}

.el-tabs--right .el-tabs__content,
.el-tabs--left .el-tabs__content {
  height: 100%;
}

.tab {
  background-color: red;
  width: 100%;
  height: 100%;
}
</style>
