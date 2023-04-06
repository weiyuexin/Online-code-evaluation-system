<template>
    <div class="nav">
        <el-menu :default-active="activeIndex" router active-text-color="#409EFF" class="oj-navbar" mode="horizontal"
                 @select="handleSelect">
            <el-menu-item index="/logo" class="logo"><img src="@/assets/logo_henu.png"/></el-menu-item>
            <el-menu-item index="/"><el-icon><HomeFilled /></el-icon>首页</el-menu-item>
            <el-menu-item index="/problems"><el-icon><Grid /></el-icon>题目</el-menu-item>
            <el-menu-item index="/contest"><el-icon><TrophyBase /></el-icon>竞赛</el-menu-item>
            <el-menu-item index="/status"><el-icon><TrendCharts /></el-icon>测评</el-menu-item>
            <el-menu-item index="/rank"><el-icon><Histogram /></el-icon>排行</el-menu-item>
            <el-menu-item index="/blog"><el-icon><ChatDotSquare /></el-icon>博客</el-menu-item>
            <el-menu-item index="/about"><el-icon><InfoFilled /></el-icon>关于</el-menu-item>
            <el-menu-item index="/register" class="register">
                <el-button round>注册</el-button>
            </el-menu-item>
            <el-menu-item index="/login" class="login">
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
    width: 130px !important;
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

.logo img {
    width: 60px !important;
}

.el-menu.el-menu--horizontal {
    border-bottom: solid 0px;
}

.el-menu--horizontal > .el-menu-item {
    width: 110px;
    font-size: 15px;
    text-align: center;
}

.el-menu--horizontal > .el-menu-item img {
    width: 80px;
    height: 58px;
}

</style>
