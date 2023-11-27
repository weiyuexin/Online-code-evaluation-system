<template>
  <div class="nav">
    <router-link :to="`/`" ><img src="@/assets/logo.png" class="logoImg"/></router-link>
    <el-menu :default-active="activeIndex" router active-text-color="#409EFF" class="oj-navbar" mode="horizontal"
             @select="handleSelect">
      <el-menu-item index="/">
        <el-icon>
          <HomeFilled/>
        </el-icon>
        首页
      </el-menu-item>
      <el-menu-item index="/problems">
        <el-icon>
          <Grid/>
        </el-icon>
        题目
      </el-menu-item>
      <el-menu-item index="/contest">
        <el-icon>
          <TrophyBase/>
        </el-icon>
        竞赛
      </el-menu-item>
      <el-menu-item index="/status">
        <el-icon>
          <TrendCharts/>
        </el-icon>
        测评
      </el-menu-item>
      <el-menu-item index="/rank">
        <el-icon>
          <Histogram/>
        </el-icon>
        排行
      </el-menu-item>
      <el-menu-item index="/blog">
        <el-icon>
          <ChatDotSquare/>
        </el-icon>
        博客
      </el-menu-item>
      <el-menu-item index="/about">
        <el-icon>
          <InfoFilled/>
        </el-icon>
        关于
      </el-menu-item>
      <el-menu-item index="/register" class="register" v-if="JSON.stringify(user) === '{}'">
        <el-button round>注册</el-button>
      </el-menu-item>
      <el-menu-item index="/login" class="login" v-if="JSON.stringify(user) === '{}'">
        <el-button type="primary" round>登录</el-button>
      </el-menu-item>
      <div class="account" v-if="JSON.stringify(user) !== '{}'">
        <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar
          :src="user.photo"
      />
      &nbsp;&nbsp;{{user.username}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
    </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item><router-link to="/user"><el-icon><Avatar /></el-icon>个人中心</router-link></el-dropdown-item>
              <el-dropdown-item @click="logout"><el-icon><CircleClose /></el-icon>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-menu>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
import {ElMessage} from "element-plus";


export default {
  name: "NavBar",
  data() {
    return {
      activeIndex: "",
      user: {}
    };
  },
  props: { //接收父组件传递的参数
    active: String
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    // 判断是否登录
    check() {
      // console.log(JSON.parse(Cookies.get('user'))!==null)
      //const user = JSON.parse(Cookies.get('user'))
      // const user = Cookies.get('user')
      // this.user = JSON.parse(user)
    },
    logout(){
      Cookies.remove('user')
      this.$router.go(0);
      window.location.reload();
      ElMessage({
        message: '退出登录成功',
        type: 'success',
      })
    }
  },
  created() {
    this.activeIndex = this.active
  },
  mounted() {
    this.check()
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
  font-size: 18px;
}
.el-menu-item:hover{
  background-color: white!important;
}

.nav{
  position: fixed;
  top: 0;
  z-index: 1000;
  width: 100%;
  height: 60px;
  background-color: white;
  border-bottom: solid 1px #EFF3F5;
  padding-left: 5%;
}
.oj-navbar {
  width: 85%;
  z-index: 999;
  float: left;
  margin-left: 3%;
}

.logo {
  width: 130px !important;
}
.logoImg{
  height: 60px;
  width: 5%;
  float: left;
  background-color: white;
}

.register {
  height: 59px !important;
  float: left !important;
  margin-left: 200px !important;
}

.login {
  height: 59px !important;
  float: left !important;
}


.account {
  padding-top: 15px;
  height: 59px !important;
  float: right !important;
  margin-left: 400px;
}

.logo img {
  width: 60px !important;
}

.el-menu.el-menu--horizontal {
  border-bottom: solid 0px;
}

.el-menu--horizontal > .el-menu-item {
  width: 100px;
  font-size: 15px;
  text-align: center;
}

.el-menu--horizontal > .el-menu-item img {
  width: 80px;
  height: 58px;
}

</style>
