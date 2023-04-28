<template>
  <div>
    <NavBar :active="`/blog`"></NavBar>
    <div class="main">
      <el-row class="article">
        <el-col :span="24" class="title">{{ article.title }}</el-col>
        <el-col :span="24" class="msg">作者：{{ article.authorName }} &nbsp;&nbsp;
          发布时间：{{ article.time }}&nbsp;&nbsp;
          阅读量：{{ article.readNum }}
        </el-col>
        <el-col :span="24" class="content" v-html="article.content">
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
  name: "Article",
  data() {
    return {
      articleId: 0,
      article: {}
    };
  },
  components: {
    NavBar,
    Footer
  },
  mounted() {
    this.articleId = this.$route.params.id
    this.getArticle()
  },
  methods: {
    getArticle() {
      console.log(this.articleId)
      axios.get("/api/article/" + this.articleId)
          .then(response => {
            this.article = response.data.data
            console.log(this.article)
          })
          .catch(error => {
            ElMessage({
              message: '获取文章信息失败',
              type: 'warning',
            })
            console.log(error);
          })
    },
  }
}
</script>

<style scoped>
.main{
  background-color: #EFF3F5;
  margin: 0 auto;
  text-align: center;
}
.article {
  margin-top: 60px;
  width: 80%;
  background-color: #FFFFFF;
  margin-left: 140px;
  padding: 20px;
}
.title{
  float: left;
  text-align: left;
  font-weight: bold;
  font-size: 25px;
}
.msg{
  text-align: left;
  float: left;
  padding-top: 10px;
  font-size: 16px;
}
.content{
  text-align: left;
}
</style>
