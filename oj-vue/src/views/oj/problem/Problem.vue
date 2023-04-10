<template>
  <div class="split">
    <el-scrollbar height="710px">
      <NavBar :active="`/problems`"></NavBar>
      <div class="my-split">
        <Split v-model="split1">
          <template #left>
            <div class="demo-split-pane">
              Right Pane
            </div>
          </template>
          <template #right>
            <div class="demo-split-pane no-padding">
              <Split v-model="split2" mode="vertical">
                <template #top>
                  <div class="demo-split-pane">
                    Top Pane
                  </div>
                </template>
                <template #bottom>
                  <div class="demo-split-pane">
                    Bottom Pane
                  </div>
                </template>
              </Split>
            </div>
          </template>
        </Split>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Problem",
  data() {
    return {
      split1: 0.5,
      split2: 0.7
    };
  },
  components: {
    NavBar
  }
}
</script>

<style scoped>
.split {
  height: 100%;
  /*height: 650px;*/
  /*border: 1px solid #dcdee2;*/
}

.my-split {
  margin-top: 60px;
  height: 650px;
  border: 1px solid #dcdee2;
}


.demo-split-pane {
  padding: 10px;
}

.demo-split-pane.no-padding {
  height: 650px;
  padding: 0;
}
</style>
