<template>
  <div>
    <h1>Login</h1>
    <el-row>
      <el-button @click="getUser">发送GET请求</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {};
  },
  methods: {
    getUser() {
      axios.get("/api/user/1")
          .then(response => {
                console.log("请求成功", response.data)
              },
              erroe => {
                console.log("请求失败", erroe.message)
              })
    }
  }
}
</script>

<style scoped>

</style>
