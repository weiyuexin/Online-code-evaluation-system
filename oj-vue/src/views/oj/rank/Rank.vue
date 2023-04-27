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
                      <el-icon @click="goToSearch" class="el-input__icon">
                        <Search/>
                      </el-icon>
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
                    prop="username"
                    label="用户名"
                    width="200">
                  <template v-slot="scope">
                    <el-link :href="scope.row.url" type="primary" target="_blank">{{
                        scope.row.username
                      }}
                    </el-link>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="introduction"
                    label="个人简介">
                </el-table-column>
                <el-table-column
                    prop="submitNum"
                    label="提交次数">
                </el-table-column>
                <el-table-column
                    prop="solvedNum"
                    label="通过题数">
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
      </el-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
import {ElMessage} from 'element-plus'
import axios from "axios";

export default {

  // eslint-disable-next-line vue/multi-word-component-names
  name: "Rank",
  data() {
    return {
      keyword: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      rank: []
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
    handleSizeChange(val) {
      this.pageSize = val;
      this.getPage()
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getPage()
    },
    getPage() {
      axios.get("/api/user/rank?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
        if (response.data.code === 0) {
          this.rank = response.data.data
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
      if (this.keyword === "") {
        ElMessage({
          message: '请输入关键字',
          type: 'warning',
        })
      } else {
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
