<template>
  <div>
    <NavBar :active="`/status`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="24" class="container">
          <el-row>
            <el-col :span="24" class="title">
              <el-row>
                <el-col :span="8" class="list">
                  测评状态
                </el-col>
                <el-col :span="8" class="search">
                  <el-input
                      size="medium"
                      placeholder="请输入题目ID以搜索"
                      v-model="search">
                    <i slot="suffix" @click="goToSearch" class="el-input__icon el-icon-search"></i>
                  </el-input>
                </el-col>
                <el-col :span="8" class="search">
                  <el-input
                      size="medium"
                      placeholder="请输入用户名以搜索"
                      v-model="search">
                    <i slot="suffix" @click="goToSearch" class="el-input__icon el-icon-search"></i>
                  </el-input>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="24">
              <el-table class="status"
                        :data="problems"
                        stripe
                        style="width: 100%">
                <el-table-column
                    prop="id"
                    label="ID"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="title"
                    label="题目"
                    width="300">
                  <template slot-scope="scope">
                    <el-link :href="scope.row.url" type="primary" target="_blank">{{ scope.row.title }}</el-link>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="difficulty"
                    label="难度">
                  <template slot-scope="scope">
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
                    label="题解">
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
                  background
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[10, 20, 30, 50, 100]"
                  :page-size="10"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="400">
              </el-pagination>
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
export default {
  name: "Status",
  data() {
    return {
      search: '',
      currentPage: 1,
      problems: [{
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      }, {
        id: '23',
        title: '寻找两个正序数组的中位数',
        difficulty: {
          type: 'danger', label: '困难'
        },
        solve: 888,
        passRate: '88%',
        url: 'https://www.baidu.com'
      },]
    };
  },
  components:{
    NavBar,
    Footer
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    goToSearch() {
      alert("搜索")
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

.status {
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
