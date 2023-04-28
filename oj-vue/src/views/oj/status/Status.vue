<template>
  <div>
    <NavBar :active="`/status`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="24" class="container">
          <el-row>
            <el-col :span="24" class="title">
              <el-row>
                <el-col :span="3" class="list">
                  测评状态
                </el-col>
                <el-col :span="4" class="search">
                  <el-input
                      size="large"
                      placeholder="请输入题目ID以搜索"
                      v-model="search">
                    <template #suffix>
                      <el-icon @click="goToSearch" class="el-input__icon">
                        <Search/>
                      </el-icon>
                    </template>
                  </el-input>
                </el-col>
                <el-col :span="4" class="search">
                  <el-input
                      size="large"
                      placeholder="请输入用户名以搜索"
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
                    prop="createTime"
                    label="时间">
                </el-table-column>
                <el-table-column
                    prop="title"
                    label="题目"
                    width="300">
                  <template v-slot="scope">
                    <router-link :to="{ name: 'problem', params: { id: scope.row.problemId } }"><el-link :href="scope.row.url" type="primary" target="_blank">{{
                        scope.row.problemName
                      }}
                    </el-link></router-link>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="status"
                    label="状态">
                </el-table-column>
                <el-table-column
                    prop="language"
                    label="语言">
                </el-table-column>
                <el-table-column
                    prop="userName"
                    label="作者">
                </el-table-column>
              </el-table>
            </el-col>
            <el-col :span="24">
              <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-size="pageSize"
                  background="true"
                  layout="prev, pager, next"
                  :total="total"
                  class="fenye">
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
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Status",
  data() {
    return {
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      problems: []
    };
  },
  components: {
    NavBar,
    Footer
  },
  mounted() {
    axios.get("/api/evaluation/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
      if (response.data.code === 0) {
        this.problems = response.data.data
        this.total = response.data.count
      }
    }).catch(error => {
      ElMessage({
        message: '后端接口错误',
        type: 'warning',
      })
      console.log(error);
    })
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      axios.get("/api/evaluation/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
        if (response.data.code === 0) {
          this.problems = response.data.data
          this.total = response.data.count
        }
      }).catch(error => {
        ElMessage({
          message: '后端接口错误',
          type: 'warning',
        })
        console.log(error);
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      axios.get("/api/evaluation/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
        if (response.data.code === 0) {
          this.problems = response.data.data
          this.total = response.data.count
        }
      }).catch(error => {
        ElMessage({
          message: '后端接口错误',
          type: 'warning',
        })
        console.log(error);
      })
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
.fenye{
  float: right;
  padding-top: 20px;
}
.el-icon-search {
  cursor: pointer;
}
</style>
