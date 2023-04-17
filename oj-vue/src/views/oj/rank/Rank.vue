<template>
  <div>
    <NavBar :active="`/rank`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="24" class="container">
          <el-row>
            <el-col :span="24" class="title">
              <el-row>
                <el-col :span="4" class="list">
                  用户排行
                </el-col>
                <el-col :span="4" class="search">
                  <el-input
                      size="large"
                      placeholder="请输入用户名以搜索"
                      v-model="keyword">
                    <template #suffix>
                      <el-icon @click="goToSearch" class="el-input__icon"><Search /></el-icon>
                    </template>
                  </el-input>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="24">
              <el-table class="rank"
                        :data="rank"
                        stripe
                        style="width: 100%">
                <el-table-column
                    type="index"
                    label="Rank"
                    width="100">
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
                    prop="introduction"
                    label="个人简介">
                </el-table-column>
                <el-table-column
                    prop="submit_num"
                    label="提交次数">
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
            </el-col>
            <el-col :span="24">
              <el-pagination
                  class="pagination"
                  background layout="jumper,prev, pager, next"
                  :page-size="10"
                  :total="100" />
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
import { ElMessage } from 'element-plus'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Rank",
  data() {
    return {
      keyword: '',
      currentPage: 1,
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
        },]
    };
  },
  components: {
    NavBar,
    Footer
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    goToSearch() {
      if (this.keyword===""){
        ElMessage({
          message: '请输入关键字',
          type: 'warning',
        })
      }else {
        //进行搜索
      }
    }
  }
}
</script>

<style scoped>
.content {
  padding: 20px 40px;
}

.el-link.el-link--primary {
  color: #000 !important;
}

.el-link.el-link--primary:hover {
  color: #66b1ff !important;
}

.rank {
  -moz-border-radius-bottomleft: 6px;
  -moz-border-radius-bottomright: 6px;
  -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}

.pagination {
  float: right;
  margin-top: 30px;
}

.container {
  padding-top: 20px;
}

.container .title {
  background-color: #ffffff;
  height: 100px;
}

.container .title .list {
  font-size: 25px;
  text-align: left;
  padding-top: 10px;
  padding-left: 10px;
}

.container .title .search {
  width: 150px;
  padding-top: 10px;
  padding-left: 10px;
}

.el-icon-search {
  cursor: pointer;
}
</style>
