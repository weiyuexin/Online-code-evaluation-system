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
                    <i slot="suffix" @click="goToSearch" class="el-input__icon el-icon-search"></i>
                  </el-input>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="24" class="blogList">
              <div class="blog" v-for="(item,index) in blogs">
                <el-row>
                  <el-col :span="24">{{item.title}}</el-col>
                  <el-col :span="24">{{item.content}}</el-col>
                  <el-col :span="24"></el-col>
                </el-row>
              </div>
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
        <el-col :span="6">

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
  name: "Blog",
  data() {
    return {
      blogs: [{
        title: '[S1018] 二叉树 题解',
        content: '观察题目，我们可以考虑一个贪心策略：对于每一个节点，',
        author: 'wyx',
        comments: 7,
        stars: 9,
        view: 89,
        type: '题解',
        time: '2023-2-29'
      }, {
        title: '[S1018] 二叉树 题解',
        content: '观察题目，我们可以考虑一个贪心策略：对于每一个节点，',
        author: 'wyx',
        comments: 7,
        stars: 9,
        view: 89,
        type: '题解',
        time: '2023-2-29'
      }, {
        title: '[S1018] 二叉树 题解',
        content: '观察题目，我们可以考虑一个贪心策略：对于每一个节点，',
        author: 'wyx',
        comments: 7,
        stars: 9,
        view: 89,
        type: '题解',
        time: '2023-2-29'
      }]
    };
  },
  components: {
    NavBar,
    Footer
  },
  methods: {
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

.blog {
//-moz-border-radius-bottomleft: 6px; //-moz-border-radius-bottomright: 6px; //-webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%); //box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%); width: 100%; height: 150px;
  background-color: #FFF;
  margin-top: 10px;
  border-radius: 5px;
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
.search{
  float: right;
  margin-right: 10px;
}
</style>
