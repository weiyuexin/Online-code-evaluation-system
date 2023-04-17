<template>
  <div>
    <el-container class="container">
      <el-header class="head"><i class="el-icon-s-data"></i>&nbsp;榜单</el-header>
      <el-main class="main">
        <el-table class="rank"
                  :data="rank"
                  stripe
                  style="width: 100%">
          <el-table-column
              type="index"
              label="Rank"
              width="80">
          </el-table-column>
          <el-table-column
              prop="title"
              label="用户名"
              width="200">
            <template v-slot="scope">
              <el-link :href="scope.row.url" type="primary" target="_blank">{{
                  scope.row.name
                }}
              </el-link>
            </template>
          </el-table-column>

          <el-table-column
              prop="solve_num"
              label="通过题数">
          </el-table-column>
          <el-table-column
              prop="passRate"
              label="通过率">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Rank",
  data() {
    return {
      rank: [{
        id: '23',
        name: 'weiyuexin',
        introduction: 'hello wyyy',
        submit_num: 899,
        solve_num: 555,
        passRate: '78%',
      }, {
        id: '2',
        name: 'wyx',
        introduction: '你好，我来自henu',
        submit_num: 455,
        solve_num: 102,
        passRate: '25%',
      }, {
        id: '8',
        name: 'www',
        introduction: 'hello,vue',
        submit_num: 100,
        solve_num: 12,
        passRate: '12%',
      }, {
        id: '17',
        name: '网易嗡嗡嗡',
        introduction: '阿三发射点回复',
        submit_num: 40,
        solve_num: 15,
        passRate: '35%',
      }, {
        id: '2',
        name: 'wyx',
        introduction: '你好，我来自henu',
        submit_num: 455,
        solve_num: 102,
        passRate: '25%',
      }, {
        id: '17',
        name: '网易嗡嗡嗡',
        introduction: '阿三发射点回复',
        submit_num: 40,
        solve_num: 15,
        passRate: '35%',
      },{
        id: '23',
        name: 'weiyuexin',
        introduction: 'hello wyyy',
        submit_num: 899,
        solve_num: 555,
        passRate: '78%',
      },{
        id: '23',
        name: 'weiyuexin',
        introduction: 'hello wyyy',
        submit_num: 899,
        solve_num: 555,
        passRate: '78%',
      },]
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
