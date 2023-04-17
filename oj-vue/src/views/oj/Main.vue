<template>
  <div>
    <NavBar :active="`/`"></NavBar>
    <div class="content">
      <el-row>
        <el-col :span="14" class="left">
          <Statistic></Statistic>
        </el-col>
        <el-col :span="9" class="right">
          <Rank></Rank>
        </el-col>
      </el-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar";
import Footer from "@/components/oj/common/Footer";
import Statistic from "@/components/oj/statistic/Statistic";
import Rank from "@/components/oj/rank/Rank"

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Main',
  data() {
    return {}
  },
  components: {
    NavBar,
    Footer,
    Statistic,
    Rank
  }
}
</script>

<style scoped>
.content {
  margin-left: 40px;
  margin-right: 40px;
  padding-top: 30px;
}

.left {
  float: left;
}

.right {
  float: left;
  margin-left: 20px;
}
</style>
