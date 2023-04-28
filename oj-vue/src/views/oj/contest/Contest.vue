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
                  {{contest.name}}
                </el-col>
                <el-col :span="4" class="list">
                  剩余时间：
                  <CountDown :target="targetTime" @on-end="handleEnd" v-font="20" />
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="24">
              <el-table class="rank"
                        :data="problems"
                        stripe
                        style="width: 100%">
                <el-table-column
                    type="index"
                    label="编号"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="username"
                    label="题目名称"
                    width="400">
                  <template v-slot="scope">
                    <router-link :to="{ name: 'problem', params: { id: scope.row.problemId } }"><el-link :href="scope.row.url" type="primary" target="_blank">{{
                        scope.row.problemName
                      }}
                    </el-link></router-link>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="submitNum"
                    label="提交总次数">
                </el-table-column>
                <el-table-column
                    prop="solvedNum"
                    label="通过总次数">
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
import Footer from "@/components/oj/common/Footer.vue";
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Contest",
  data() {
    return {
      keyword: '',
      contest: {},
      contestId:0,
      problems:[],
      targetTime: new Date().getTime() -3000,
    };
  },
  components: {
    NavBar,
    Footer
  },
  mounted() {
    this.contestId = this.$route.params.id
    this.getContest()
  },
  methods: {
    handleEnd () {
      this.$Message.info('比赛结束');
    },
    getContest(){
      axios.get("/api/contest/"+this.contestId).then(response => {
        if (response.data.code === 200) {
          this.contest = response.data.data
          const currentTime= new Date().getTime()
          const startTime = Date.parse(new Date(this.contest.startTime))
          console.log(currentTime)
          console.log(startTime)
          if (startTime>currentTime){
            ElMessage({
              message: '当前竞赛还未开始',
              type: 'warning',
            })
            return
          }
          console.log(this.contest.endTime)
          this.targetTime = Date.parse(new Date(this.contest.endTime));
          this.getAllProblems()
        }
      }).catch(error => {
        ElMessage({
          message: '后端接口错误',
          type: 'warning',
        })
        console.log(error);
      })
    },
    getAllProblems() {
      axios.get("/api/contest/problem/list/"+this.contestId).then(response => {
        if (response.data.code === 200) {
          this.problems = response.data.data
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