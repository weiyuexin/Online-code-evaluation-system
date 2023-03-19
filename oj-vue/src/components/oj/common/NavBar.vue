<template>
  <div class="nav">
    <el-menu :default-active="activeIndex" router active-text-color="#409EFF" class="oj-navbar" mode="horizontal"
             @select="handleSelect">
      <el-menu-item index="/logo" class="logo"><img src="@/assets/logo_henu.png"/></el-menu-item>
      <el-menu-item index="/"><i class="el-icon-s-home"></i>首页</el-menu-item>
      <el-menu-item index="/problems"><i class="el-icon-s-grid"></i>题目</el-menu-item>
      <el-menu-item index="/contest"><i class="el-icon-trophy"></i>竞赛</el-menu-item>
      <el-menu-item index="/status"><i class="el-icon-s-marketing"></i>测评</el-menu-item>
      <el-menu-item index="/rank"><i class="el-icon-s-data"></i>排行</el-menu-item>
      <el-menu-item index="/blog"><i class="el-icon-s-opportunity"></i>博客</el-menu-item>
      <el-menu-item index="/about"><i class="el-icon-info"></i>关于</el-menu-item>
      <el-menu-item index="9" class="register">
        <el-button round>注册</el-button>
      </el-menu-item>
      <el-menu-item index="8" class="login">
        <el-button type="primary" round>登录</el-button>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {
      activeIndex: "",
    };
  },
  props: { //接收父组件传递的参数
    active: String
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  created() {
    this.activeIndex = this.active
  }
}
</script>

<style scoped>
.oj-navbar {
  position: fixed;
  width: 100%;
  top: 0;
  border-bottom: solid 1px #EFF3F5;
  z-index: 999;
  box-shadow: 0 1px 5px 0 rgb(0 0 0 / 10%);
}

.logo {
  width: 150px !important;
}

.register {
  float: right !important;
  margin-right: 100px !important;
}

.login {
  float: right !important;
}

.logo img {
  width: 60px !important;
}

.el-menu.el-menu--horizontal {
  border-bottom: solid 0px;
}

.el-menu--horizontal > .el-menu-item {
  width: 110px;
  font-size: 15px;
}

.el-menu--horizontal > .el-menu-item img {
  width: 80px;
  height: 58px;
}

</style>
