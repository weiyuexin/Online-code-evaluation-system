<template>
  <div>
    <el-scrollbar height="1000px" class="scrollbar">
      <NavBar :active="`/about`"></NavBar>
      <div class="content">
        <el-row>
          <el-col :span="12" class="div1">
            <div class="div">
              <el-row>
                <el-col :span="24" class="div-title">
                  编译环境
                </el-col>

                <el-col :span="24">
                  <el-row>
                    <el-col :span="24">
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> C (GCC 4.8.5)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/gcc {src_path} -o {exe_path}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> C++ (G++ 4.8.5)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/g++ {src_path} -o {exe_path}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> Java (JDK 17.0.6)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/javac {src_path} -d {exe_dir} -encoding UTF8
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> Python3 (Python 3.6.8)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/python3 {src_path}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> Golang (Golang 1.20.2)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/go build -o {exe_path} {src_path}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="my-language">
                          <span>·</span> JavaScript (Node.js 16.19.1)
                        </el-col>
                        <el-col :span="24" class="compiler">
                          /usr/bin/node {src_path}
                        </el-col>
                      </el-row>
                    </el-col>
                  </el-row>
                </el-col>

              </el-row>
            </div>
          </el-col>
          <el-col :span="12" class="div2">
            <div class="div">
              <el-row>
                <el-col :span="24" class="div-title">
                  结果说明
                </el-col>
                <el-col :span="24" class="result">
                  <el-row class="result-list">
                    <el-col span="24">
                      <el-tag
                          type="danger"
                          effect="dark"
                          color="red">
                        Compile Error
                      </el-tag> &nbsp;:&nbsp;代码编译错误。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark">
                        Partial Accepted
                      </el-tag>&nbsp;:&nbsp;通过部分测试用例。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark"
                          color="#19BE6B">
                        Accepted
                      </el-tag>&nbsp;:&nbsp; 恭喜! 您的解题方法是正确的。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark"
                          color="red">
                        Wrong Answer
                      </el-tag>&nbsp;:&nbsp; 程序输出结果与判题程序的答案不符。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark"
                          color="red">
                        Runtime Error
                      </el-tag>&nbsp;:&nbsp; 程序异常终止。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark"
                          color="red">
                        Time Limit Exceeded
                      </el-tag>&nbsp;:&nbsp; 程序运行时间超出题目限制。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="success"
                          effect="dark"
                          color="red">
                        Memory Limit Exceeded
                      </el-tag>&nbsp;:&nbsp; 程序使用的内存超出题目限制。
                    </el-col>
                    <el-col span="24">
                      <el-tag
                          type="info"
                          effect="dark">
                        System Error
                      </el-tag>&nbsp;:&nbsp; 判题机出现错误。
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </div>
      <Footer></Footer>
    </el-scrollbar>
  </div>

</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";


export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "About",
  data() {
    return {}
  },
  components: {
    NavBar,
    Footer,
  }
}
</script>

<style scoped>
.scrollbar {
  background-color: #EFF3F5;
}

.content {
  padding-left: 40px;
  padding-right: 40px;
  padding-top: 30px;
}

.div1 {
  float: left;
  padding-right: 10px;
}

.div2 {
  float: right;
  padding-left: 10px;
}

.div {
  background-color: #FFFFFF;
  height: 100%;
  -moz-border-radius-bottomleft: 6px;
  -moz-border-radius-bottomright: 6px;
  -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  padding: 20px 20px;
  text-align: left;
  line-height: 50px;
  color: #409eff;
  font-size: 25px;
}

.div-title {
  border-bottom: 1px solid #ebeef5;
  width: 100%;
  height: 50px;
}

.my-language {
  font-size: 20px;
}

.my-language span {
  font-weight: bold;
}

.compiler {
  font-size: 18px;
  color: #000000;
  background-color: #F8F8F9;
  width: 400px;
  height: 50px;
  text-align: left;
  line-height: 50px;
  padding-left: 20px;
}

.result {
  width: 500px;
  height: 400px;
}

.result-list {
  width: 400px;
  font-size: 15px;
  color: #2d2d2d;
}
</style>
