<template>
<div>
  <NavBar></NavBar>
  <Footer></Footer>
</div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
export default {
  name: "UserPanel",
  data() {
    return {};
  },
  components: {
    NavBar,
    Footer
  },
}
</script>

<style scoped>

</style>
