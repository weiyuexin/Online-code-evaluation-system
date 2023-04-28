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
              width="100">
          </el-table-column>
          <el-table-column
              prop="username"
              label="用户名"
              width="130">
            <template v-slot="scope">
              <el-link :href="scope.row.url" type="primary" target="_blank">{{
                  scope.row.username
                }}
              </el-link>
            </template>
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
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Rank",
  data() {
    return {
      rank: []
    };
  },
  methods:{
    getIndexRank(){
      axios.get("/api/user/top" ).then(response => {
        if (response.data.code === 200) {
          this.rank = response.data.data
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
    this.getIndexRank()
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
