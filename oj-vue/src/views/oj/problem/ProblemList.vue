<template>
  <div>
    <el-scrollbar height="710px">
      <NavBar :active="`/problems`"></NavBar>
      <div class="content">
        <el-row>
          <el-col :span="18" class="container">
            <el-row>
              <el-col :span="24" class="title">
                <el-row>
                  <el-col :span="4" class="list">
                    题目列表
                  </el-col>
                  <el-col :span="5" class="search">
                    <el-input
                        size="large"
                        placeholder="请输入关键字搜索"
                        v-model="search">
                      <template #suffix>
                        <el-icon @click="goToSearch" class="el-input__icon">
                          <Search/>
                        </el-icon>
                      </template>
                    </el-input>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="24">
                <el-table class="problems"
                          :data="problems"
                          stripe
                          style="width: 100%">
                  <el-table-column
                      label="ID"
                      type="index"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      prop="title"
                      label="题目"
                      width="300">
                    <template v-slot="scope">
                      <el-link :href="scope.row.url" type="primary" target="_blank">{{
                          scope.row.title
                        }}
                      </el-link>
                    </template>
                  </el-table-column>
                  <el-table-column
                      prop="difficulty"
                      label="难度">
                    <template v-slot="scope">
                      <el-tag
                          :key="scope.row.difficulty.label"
                          :type="scope.row.difficulty.type"
                          effect="dark">
                        {{ scope.row.difficulty.label }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column
                      prop="solve"
                      label="提交次数">
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
                    :total="100"/>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6">

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
  name: "ProblemList",
  data() {
    return {
      search: '',
      currentPage: 1,
      problems: [{
        id: '23',
        title: 'A+B problem',
        difficulty: {
          type: 'success', label: '简单'
        },
        solve: 213,
        passRate: '78%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '二进制转十进制',
        difficulty: {
          type: 'warning', label: '普及'
        },
        solve:76,
        passRate: '57%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '单词的长度',
        difficulty: {
          type: 'success', label: '简单'
        },
        solve: 212,
        passRate: '64%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '迷宫的最短路径',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 34,
        passRate: '12%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 55,
        passRate: '23%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '平面分割问题',
        difficulty: {
          type: '', label: '提高'
        },
        solve: 125,
        passRate: '34%',
        url: 'https://www.baidu.com'
      },]
    };
  },
  components: {
    NavBar,
    Footer
  },
  methods: {
    created() {
      document.title = '这是标题';
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    goToSearch() {
      alert("搜索")
    }
  },
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

.problems {
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
  width: 250px;
  padding-top: 10px;
  padding-left: 10px;
}

.el-icon-search {
  cursor: pointer;
}
</style>
