<template>
  <div>
    <NavBar :active="`/contest`"></NavBar>
    <div class="content">

    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
export default {
  name: "ContestList",
  data() {
    return {};
  },
  components:{
    NavBar,
    Footer
  }
}
</script>

<style scoped>

</style>
