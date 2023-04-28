<template>
  <div>
    <NavBar :active="`/blog`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="18" class="container">
          <el-row>
            <el-col :span="24" class="title">
              <el-row>
                <el-col :span="8" class="list">
                  全部文章
                </el-col>
                <el-col :span="8" class="search">
                  <el-input
                      size="medium"
                      placeholder="请输入关键字搜索"
                      v-model="search">
                    <i @click="goToSearch" class="el-input__icon el-icon-search"></i>
                  </el-input>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="24" class="blogList">
              <div class="blog" v-for="(item) in articles">
                <el-row>
                  <router-link :to="{ name: 'article', params: { id: item.id } }">
                      <el-col :span="24" class="tit">{{ item.title }}</el-col>
                  </router-link>
                  <el-col :span="24" class="con">{{ item.content }}</el-col>
                  <el-col :span="24" class="msg">作者：{{ item.authorName }} &nbsp;&nbsp;发布时间：{{ item.time }}&nbsp;&nbsp;阅读量：{{ item.readNum }}</el-col>
                </el-row>
              </div>
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
        <el-col :span="6" class="add">
          <router-link :to="`/article/edit`">

            <el-button type="primary" class="btn">
              <el-icon>
                <Edit/>
              </el-icon>&nbsp;发&nbsp;布&nbsp;文&nbsp;章
            </el-button>

          </router-link>
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
  name: "Blog",
  data() {
    return {
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      articles: []
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
    goToSearch() {
      alert("搜索")
    },
    getPage() {
      axios.get("/api/article/list?limit=" + this.pageSize + "&page=" + this.currentPage, {}).then(response => {
        if (response.data.code === 0) {
          this.articles = response.data.data
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
  }
}
</script>

<style scoped>
.content {
  padding: 20px 40px;
}

.blog {
  width: 100%;
  height: 150px;
  background-color: #FFF;
  margin-top: 10px;
  border-radius: 5px;
  padding: 10px;
}

.tit {
  float: left;
  text-align: left;
  width: 100%;
  height: 40px;
  font-size: 18px;
  font-weight: bold;
}

.con {
  text-align: left;
  height: 70px;
  font-size: 15px;
}

.msg {
  text-align: left;
}

.blog:hover {
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
  padding: 20px 5%;
}

.add {
  padding-top: 20px;
}

.btn {
  float: left;
  width: 200px;
  height: 50px;
  font-size: 18px;
}

.container .title {
  background-color: #ffffff;
  height: 60px;
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

.search {
  float: right;
  margin-right: 10px;
}
</style>
