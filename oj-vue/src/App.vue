<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #EFF3F5;
  margin: 0px;
  padding: 0px;
  width: 100%;
}
body,html{
  margin: 0;
  padding: 0;
}
a{
  text-decoration: none;
}
.content{
  margin-top: 60px;
}

</style>
