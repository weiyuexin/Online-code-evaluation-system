<template>
  <div>
    <el-container class="container">
      <el-header class="head"><i class="el-icon-data-analysis"></i>&nbsp;热门题目</el-header>
      <el-main class="main">
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
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Statistic",
  data() {
    return {
      problems: []
    };
  },
  methods: {
    getTopProblems() {
      axios.get("/api/problem/hot").then(response => {
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
    }
  },
  mounted() {
    this.getTopProblems()
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
