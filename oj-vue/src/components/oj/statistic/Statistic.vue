<template>
  <div>
    <el-container class="container">
      <el-header class="head"><i class="el-icon-data-analysis"></i>&nbsp;最近一周提交统计</el-header>
      <el-main class="main">

      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Statistic",
  data() {
    return {};
  },
  methods:{

  }
}
</script>

<style scoped>
.container{
  width: 100%;
  height: 500px;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}
.container .head{
  text-align: left;
  line-height: 60px;
  font-size: 23px;
  color: #409eff;
  border-bottom: solid 1px #ebeef5;
}
</style>
