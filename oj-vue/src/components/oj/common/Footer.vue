<template>
  <div class="footer">
    <el-row>
      <el-col :span="8">
        <div class="logo">
          <router-link :to="{name:`main`}"><img src="@/assets/logo_henu.png"></router-link>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="link">
          Copyright &nbsp;© &nbsp;2023 &nbsp;WeiYueXin
        </div>
      </el-col>
      <el-col :span="8">
        <div class="link"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      activeIndex: "",
    };
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
.footer {
  width: 100%;
  height: 150px;
  background-color: #fff;
  margin-top: 30px;
}

.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }

  width: 100%;
  height: 100%;
}

.el-col {
  border-radius: 0px;
  height: 100%;
}

.logo {
  height: 100%;
}

.logo img {
  position: relative;
  top: 13%;
  width: 22%;
  text-align: center;
}

.link {
  height: 100%;
  text-align: center;
  color: #000;
  font-size: 18px;
}

</style>
