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
                      <router-link :to="{ name: 'problem', params: { id: scope.row.id } }"><el-link :href="scope.row.url" type="primary" target="_blank">{{
                          scope.row.title
                        }}
                      </el-link></router-link>
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
                        {{ scope.row.difficulty }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column
                      prop="submitNum"
                      label="提交次数">
                  </el-table-column>
                  <el-table-column
                      prop="solvedNum"
                      label="通过次数">
                  </el-table-column>
                  <el-table-column
                      prop="passRate"
                      label="通过率">
                    <template v-slot="scope">
                      <span v-if="scope.row.submitNum===0">0</span>
                      <span v-if="scope.row.submitNum>0">
                      {{ ((scope.row.solvedNum / scope.row.submitNum) * 100).toFixed(2) }}%
                    </span>
                    </template>
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
                    class="pagination">
                </el-pagination>
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
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  name: "ProblemList",
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
    this.getPage()
  },
  methods: {
    created() {
      document.title = '这是标题';
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getPage()
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getPage()
    },
    goToSearch() {
      alert("搜索")
    },
    getPage(){
      axios.get("/api/problem/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
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
