<template>
  <div class="split">
    <el-scrollbar height="710px">
      <NavBar :active="`/problems`"></NavBar>
      <div class="my-split">
        <Split v-model="split1">
          <template #left>
            <el-scrollbar height="640px">
              <div class="demo-split-pane problemDetail">
                <el-row>
                  <el-col :span="24" class="title">{{ problem.title }}</el-col>
                  <el-col :span="24" class="t">题目描述</el-col>
                  <el-col :span="24" class="v">{{ problem.description }}</el-col>
                  <el-col :span="24" class="t">输入描述</el-col>
                  <el-col :span="24" class="v">{{ problem.describeInput }}</el-col>
                  <el-col :span="24" class="t">输出描述</el-col>
                  <el-col :span="24" class="v">{{ problem.describeOutput }}</el-col>
                  <el-col :span="24" class="t">输入样例</el-col>
                  <el-col :span="8" class="v c">{{ problem.input }}</el-col>
                  <el-col :span="24" class="t">输出样例</el-col>
                  <el-col :span="8" class="v c">{{ problem.output }}</el-col>
                </el-row>

              </div>
            </el-scrollbar>
          </template>
          <template #right>
            <div class="demo-split-pane no-padding">
              <Split v-model="split2" mode="vertical">
                <template #top>
                  <div class="demo-split-pane">
                    <el-container>
                      <el-header class="code-header">
                        <el-row>
                          <el-col :span="9" class="my-language">
                            语言 :
                            <el-select v-model="language" class="m-2" size="large">
                              <el-option
                                  v-for="item in languages"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value"
                              />
                            </el-select>
                            <el-button class="flush-code" @click="RefreshCode">
                              <el-icon>
                                <RefreshRight/>
                              </el-icon>
                            </el-button>
                          </el-col>
                          <el-col :span="8"></el-col>
                          <el-col :span="7" class="my-language">
                            主题 :
                            <el-select v-model="theme" class="m-2" size="large">
                              <el-option
                                  v-for="item in themes"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value"
                              />
                            </el-select>
                          </el-col>
                        </el-row>
                      </el-header>
                      <el-main class="code-main">
                        <prism-editor
                            class="my-editor height-300"
                            v-model="code"
                            aria-disabled
                            :highlight="highlighter"
                            line-numbers
                            :readonly="false"
                            :tabSize="4"
                        ></prism-editor>
                      </el-main>
                    </el-container>
                  </div>
                </template>
                <template #bottom>
                  <div class="demo-split-pane">
                    <el-row>
                      <el-col :span="20" class="status">{{ status }}</el-col>
                      <el-col :span="4" class="my-btn">
                        <el-button type="success" @click="submit" class="submit">提&nbsp;&nbsp;&nbsp;&nbsp;交
                        </el-button>
                      </el-col>
                    </el-row>
                  </div>
                </template>
              </Split>
            </div>
          </template>
        </Split>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import {ElMessage, ElMessageBox} from 'element-plus'

import {PrismEditor} from "vue-prism-editor";
import "vue-prism-editor/dist/prismeditor.min.css";
import {highlight, languages} from "prismjs/components/prism-core";
import "prismjs/components/prism-clike";
import "prismjs/components/prism-javascript";
import "prismjs/themes/prism-tomorrow.css";
import axios from "axios";
import qs from "qs";
import Cookies from "js-cookie";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Problem",
  data() {
    return {
      split1: 0.5,
      split2: 0.8,
      activeIndex: '1',
      language: "C",
      theme: "暗夜",
      code: '',
      problemId: 0,
      languages: [
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
          value: 'Python3',
          label: 'Python3',
        },
        {
          value: 'Golang',
          label: 'Golang',
        },
        {
          value: 'JavaScript',
          label: 'JavaScript',
        }
      ],
      themes: [
        {
          value: '暗夜',
          label: '暗夜',
        },
        {
          value: '日光灯',
          label: '日光灯',
        }
      ],
      problem: {},
      user: {},
      status: ""
    };
  },
  components: {
    NavBar,
    PrismEditor
  },
  mounted() {
    this.problemId = this.$route.params.id
    this.getProblem()
  },
  methods: {
    highlighter(code) {
      return highlight(code, languages.js);
    },
    getUser() {
      // console.log(JSON.parse(Cookies.get('user'))!==null)
      //const user = JSON.parse(Cookies.get('user'))
      const user = Cookies.get('user')
      this.user = JSON.parse(user)
    },
    getProblem() {
      axios.get("/api/problem/" + this.problemId)
          .then(response => {
            this.problem = response.data.data
            console.log(this.problem)
          })
          .catch(error => {
            ElMessage({
              message: '获取题目信息失败',
              type: 'warning',
            })
            console.log(error);
          })
    },
    RefreshCode() {
      ElMessageBox.confirm(
          '确定要重置代码吗?',
          'Warning',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            this.code = ""
            ElMessage({
              type: 'success',
              message: '重置完成',
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '取消重置',
            })
          })
    },
    submit() {
      this.getUser()
      if (JSON.stringify(this.user) === '{}') {
        ElMessage({
          type: 'info',
          message: '请先登录',
        })
        return
      }
      if (this.code === "") {
        ElMessage({
          type: 'error',
          message: '代码不能为空',
        })
        return
      }
      let postData = qs.stringify({
        code: this.code,
        problemId: this.problemId,
        userId: this.user.id
      })
      switch (this.language) {
        case "C":
          console.log(this.user)
          axios.post("/api/code/c", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              });
          break
        case "C++":
          console.log(this.user)
          axios.post("/api/code/cpp", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              })
              break
        case "Java":
          console.log(this.user)
          axios.post("/api/code/java", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              })
          break
        case "Python3":
          console.log(this.user)
          axios.post("/api/code/python", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              })
          break
        case "Golang":
          console.log(this.user)
          axios.post("/api/code/go", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              })
          break
        case "JavaScript":
          console.log(this.user)
          axios.post("/api/code/js", postData)
              .then(response => {
                this.status = response.data.data.status
                if (response.data.code === 200) {
                  ElMessage({
                    message: '通过',
                    type: 'success',
                  })
                } else {
                  ElMessage({
                    message: response.data.msg,
                    type: 'warning',
                  })
                }
              })
              .catch(error => {
                ElMessage({
                  message: '提交失败',
                  type: 'warning',
                })
                console.log(error);
              })
              break
      }
    }
  }
}
</script>

<style scoped>
.split {
  height: 100%;
  /*height: 650px;*/
  background-color: #FFFFFF;
}

.my-split {
  margin-top: 60px;
  height: 650px;
  border: 1px solid #dcdee2;
}

.problemDetail {
  padding: 10px 20px !important;

}

.status {
  float: left;
  text-align: left;
  font-size: 20px;
  padding-left: 20px;
  padding-top: 10px;
}

.title {
  float: left;
  text-align: left;
  font-size: 25px;
}

.t {
  float: left;
  text-align: left;
  line-height: 50px;
  color: #3091f2;
  font-size: 18px;
}

.v {
  padding-left: 20px;
  text-align: left;
  float: left;
}

.c {
  background-color: #f1f1f1;
  padding: 10px;
  border: 1px dashed #e9eaec;
}

.demo-split-pane {
  padding: 1px;
}

.demo-split-pane.no-padding {
  height: 650px;
  padding: 0;
}

.code-header {
  background-color: #FFFFFF;
}

.my-language {
  float: left;
  padding-top: 10px;
}

.m-2 {
  width: 130px;
}

.flush-code {
  width: 40px;
  height: 40px;
  margin-left: 20px;
}

.el-main {
  width: 100%;
}

.code-main {
  padding: 0;
  height: 100%;
}

.my-editor {
  background: #2d2d2d;
  color: #ccc;
  font-family: Fira code, Fira Mono, Consolas, Menlo, Courier, monospace;
  font-size: 14px;
  line-height: 1.5;
  width: 100%;
  height: 100%;
}

.prism-editor__textarea:focus {
  outline: none;
}

/* 非必须 */
.height-300 {
  height: 460px;
}

.my-btn {
  padding-top: 80px;
}

.my-btn .submit {
  width: 80px;
}
</style>
