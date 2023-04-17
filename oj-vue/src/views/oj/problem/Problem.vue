<template>
  <div class="split">
    <el-scrollbar height="710px">
      <NavBar :active="`/problems`"></NavBar>
      <div class="my-split">
        <Split v-model="split1">
          <template #left>
            <div class="demo-split-pane">
              <el-menu
                  :default-active="activeIndex"
                  class="el-menu-demo"
                  mode="horizontal"
                  @select="handleSelect"
              >
                <el-menu-item index="1">题目描述</el-menu-item>
                <el-menu-item index="2">评论</el-menu-item>
                <el-menu-item index="3">提交记录</el-menu-item>
              </el-menu>
            </div>
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
                      <el-col :span="20"></el-col>
                      <el-col :span="4" class="my-btn">
                        <el-button type="success" @click="submit" class="submit">提&nbsp;&nbsp;&nbsp;&nbsp;交</el-button>
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
      code: 'package main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\npackage main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\npackage main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\n',
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
      ]
    };
  },
  components: {
    NavBar,
    PrismEditor
  },
  methods: {
    highlighter(code) {
      return highlight(code, languages.js);
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
    submit(){
      ElMessage({
        type: 'success',
        message: '提交成功',
      })
    }
  }
}
</script>

<style scoped>
.split {
  height: 100%;
  /*height: 650px;*/
  /*border: 1px solid #dcdee2;*/
}

.my-split {
  margin-top: 60px;
  height: 650px;
  border: 1px solid #dcdee2;
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
.my-btn{
 padding-top: 80px;
}
.my-btn .submit{
  width: 80px;
}
</style>
