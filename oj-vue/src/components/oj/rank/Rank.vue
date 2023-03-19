<template>
  <div>
    <el-container class="container">
      <el-header class="head"><i class="el-icon-s-data"></i>&nbsp;榜单</el-header>
      <el-main class="main">
        <el-table
            :data="rank"
            stripe
            style="width: 100%">
          <el-table-column
              prop="index"
              label="排名"
              width="180">
          </el-table-column>
          <el-table-column
              prop="username"
              label="用户名"
              width="180">
            <template slot-scope="scope">
              <el-link :href="scope.row.url" type="primary" target="_blank">{{ scope.row.username }}</el-link>
            </template>
          </el-table-column>
          <el-table-column
              prop="passed"
              label="通过数">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Rank",
  data() {
    return {
      rank: [{
        index: 1,
        username: '王小虎',
        url: 'https://element.eleme.cn/#/zh-CN/component/table',
        passed: 45
      }, {
        index: 2,
        username: '王小虎',
        passed: 45
      }, {
        index: 3,
        username: '王小虎',
        passed: 45
      }, {
        index: 4,
        username: '王小虎',
        passed: 45
      }, {
        index: 5,
        username: '王小虎',
        passed: 45
      }]
    };
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 500px;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.container .head {
  text-align: left;
  line-height: 60px;
  font-size: 23px;
  color: #409eff;
  border-bottom: solid 1px #ebeef5;
}
</style>
