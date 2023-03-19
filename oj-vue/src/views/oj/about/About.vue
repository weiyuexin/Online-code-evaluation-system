<template>
  <div>
    <NavBar :active="`/about`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="12" class="div1">
          <div class="div">
            <el-row>
              <el-col :span="24" class="div-title">
                编译环境
              </el-col>
              <el-col :span="24" class="div-title">
                <CodeBlock></CodeBlock>
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
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
import CodeBlock from "@/components/oj/code/CodeBlock";
export default {
  name: "About",
  data() {
    return {};
  },
  components: {
    NavBar,
    Footer,
    CodeBlock
  }
}
</script>

<style scoped>
.content {
  padding-left: 40px;
  padding-right: 40px;
  padding-top: 30px;
}

.div1 {
  height: 500px;
  float: left;
  padding-right: 10px;
}

.div2 {
  height: 500px;
  float: right;
  padding-left: 10px;
}
.div{
  background-color: #FFFFFF;
  height: 100%;
  -moz-border-radius-bottomleft: 6px;
  -moz-border-radius-bottomright: 6px;
  -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  padding: 10px 20px;
  text-align: left;
  line-height: 50px;
  color: #409eff;
  font-size: 22px;
}
.div-title{
  border-bottom: 1px solid #ebeef5;
  width: 100%;
  height: 50px;
}
</style>
