<template>
  <div>
    <el-scrollbar height="710px">
      <el-row>
        <el-col :span="18">
          <el-input class="title" size="large" v-model="title" placeholder="请输入文章标题" clearable />
        </el-col>
        <el-col :span="6">
          <el-button class="submit" type="primary" round @click="submit">发布</el-button>
        </el-col>
      </el-row>
      <div style="border: 1px solid #ccc;" >
        <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            style="height: 600px; overflow-y: hidden;"
            v-model="valueHtml"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="handleCreated"
        />
      </div>
      <Footer></Footer>
    </el-scrollbar>
  </div>
</template>

<script>
import NavBar from "@/components/oj/common/NavBar.vue";
import Footer from "@/components/oj/common/Footer";
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
  name: "EditArticle",
  data() {
    return {
      title: '',
      content:""
    };
  },
  mounted() {
  },
  methods: {
    submit(){
      ElMessageBox.confirm(
          '确定发布吗?',
          'Warning',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            ElMessage({
              type: 'success',
              message: '发布成功',
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '取消发布',
            })
          })
    }
  },
  components: {
    NavBar,
    Footer,
    Editor,
    Toolbar
  },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 内容 HTML
    const valueHtml = ref('<p>hello</p>')

    // 模拟 ajax 异步获取内容
    onMounted(() => {
      setTimeout(() => {
        valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
      }, 1500)
    })

    const toolbarConfig = {}
    const editorConfig = { placeholder: '请输入内容...' }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    return {
      editorRef,
      valueHtml,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated
    };
  }
}
</script>

<style scoped>
/*.title{*/
/*  margin-top: 60px;*/
/*}*/
.submit{
  margin-top: 5px;
}
.body{
  background-color: #FFFFFF;
}
</style>