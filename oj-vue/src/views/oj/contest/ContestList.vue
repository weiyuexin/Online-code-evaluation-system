<template>
  <div>
    <NavBar :active="`/contest`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="24" class="container">
          <el-row>
            <el-col :span="24" class="title">
              <el-row>
                <el-col :span="4" class="list">
                  竞赛列表
                </el-col>
                <el-col :span="4" class="search">
                  <el-input
                      size="large"
                      placeholder="请输入竞赛名以搜索"
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
                        :data="contests"
                        stripe
                        style="width: 100%">
                <el-table-column
                    prop="id"
                    label="编号"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="username"
                    label="竞赛名"
                    width="400">
                  <template v-slot="scope">
                    <router-link :to="{ name: 'contest', params: { id: scope.row.id } }"><el-link :href="scope.row.url" type="primary" target="_blank">{{
                        scope.row.name
                      }}
                    </el-link></router-link>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="startTime"
                    label="开始时间">
                </el-table-column>
                <el-table-column
                    prop="endTime"
                    label="结束时间">
                </el-table-column>
                <el-table-column
                    prop="num"
                    label="参赛人数">
                </el-table-column>
                <el-table-column
                    prop="status"
                    label="竞赛状态">
                </el-table-column>
                <el-table-column
                    label="操作">
                  <template v-slot="scope">
                    <el-button type="primary" v-if="scope.row.status==='未开始'" @click="apply(scope.row.id)">报名</el-button>
                    <el-button type="primary" disabled v-if="scope.row.status==='进行中'">报名</el-button>
                    <el-button type="primary" disabled v-if="scope.row.status==='已结束'">报名</el-button>

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
import Cookies from "js-cookie";
import qs from "qs";
export default {
  name: "ContestList",
  data() {
    return {
      keyword: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      contests: [],
      user: {},
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
      axios.get("/api/contest/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
        if (response.data.code === 0) {
          this.contests = response.data.data
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
    },
    getUser() {
      // console.log(JSON.parse(Cookies.get('user'))!==null)
      //const user = JSON.parse(Cookies.get('user'))
      const user = Cookies.get('user')
      this.user = JSON.parse(user)
    },
    apply(contestId){
      this.getUser()
      if (JSON.stringify(this.user) === '{}') {
        ElMessage({
          type: 'info',
          message: '请先登录',
        })
        return
      }
      let postData = qs.stringify({
        contestId: contestId,
        userId: this.user.id
      })
      axios.post("/api/contest/user/apply", postData)
          .then(response => {
            if (response.data.code === 200) {
              ElMessage({
                message: '报名成功',
                type: 'success',
              })
            } else {
              ElMessage({
                message: response.data.msg,
                type: 'warning',
              })
            }
          })
          .catch(error => {
            ElMessage({
              message: '报名失败',
              type: 'warning',
            })
            console.log(error);
          });
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
