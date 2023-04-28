<template>
  <div>
    <el-scrollbar height="710px">
      <el-row>
        <el-col :span="18">
          <el-input class="title" size="large" v-model="title" placeholder="请输入文章标题" clearable/>
        </el-col>
        <el-col :span="6">
          <el-button class="submit" type="primary" round @click="submit">发布</el-button>
        </el-col>
      </el-row>
      <div style="border: 1px solid #ccc;">
        <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            :defaultConfig="editorConfig"
            :mode="mode"
            v-model="valueHtml"
            style="height: 500px; overflow-y: hidden"
            @onCreated="handleCreated"
            @onChange="handleChange"
            @onDestroyed="handleDestroyed"
            @onFocus="handleFocus"
            @onBlur="handleBlur"
            @customAlert="customAlert"
            @customPaste="customPaste"
        />
      </div>
      <Footer></Footer>
    </el-scrollbar>
  </div>
</template>

<script>
import Footer from "@/components/oj/common/Footer";
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import {onBeforeUnmount, ref, shallowRef} from 'vue'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import axios from "axios";
import qs from "qs";
import Cookies from "js-cookie";

export default {
  name: "EditArticle",
  data() {
    return {
      title: '',
      content: "",
      user: {}
    };
  },
  mounted() {
    const user = Cookies.get('user')
    this.user = JSON.parse(user)
  },
  methods: {
    submit() {
      this.content = this.valueHtml
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
            let postData = qs.stringify({
              title: this.title,
              content: this.content,
              authorId: this.user.id
            })
            if (JSON.stringify(this.user) === '{}') {
              ElMessage({
                type: 'info',
                message: '请先登录',
              })
              //跳转到首页
              window.setTimeout(function () {
                location.href = "/";
              }, 2000);
              return
            }
            axios.post("/api/article/", postData)
                .then(response => {
                  console.log(response.data)
                  if (response.data.code === 200) {
                    ElMessage({
                      message: '发布成功',
                      type: 'success',
                    })
                  } else {
                    ElMessage({
                      message: response.data.msg,
                      type: 'error',
                    })
                  }
                })
                .catch(error => {
                  ElMessage({
                    message: '发布失败',
                    type: 'warning',
                  })
                  console.log(error);
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
    Footer,
    Editor,
    Toolbar
  },
  setup() {
    // 编辑器实例，必须用 shallowRef，重要！
    const editorRef = shallowRef();

    // 内容 HTML
    const valueHtml = ref('');

    // 模拟 ajax 异步获取内容
    // onMounted(() => {
    //   setTimeout(() => {
    //     valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>';
    //   }, 1500);
    // });

    const toolbarConfig = {};
    const editorConfig = {placeholder: '请输入内容...',MENU_CONF:{}};
    editorConfig.MENU_CONF['uploadImage'] = {
      server: '/api/cos/upload',
    }
    // 组件销毁时，也及时销毁编辑器，重要！
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;

      editor.destroy();
    });

    // 编辑器回调函数
    const handleCreated = (editor) => {
      console.log('created', editor);
      editorRef.value = editor; // 记录 editor 实例，重要！
    };
    const handleChange = (editor) => {
      console.log('change:', editor.getHtml());
    };
    const handleDestroyed = (editor) => {
      console.log('destroyed', editor);
    };
    const handleFocus = (editor) => {
      console.log('focus', editor);
    };
    const handleBlur = (editor) => {
      console.log('blur', editor);
    };
    const customAlert = (info, type) => {
      alert(`【自定义提示】${type} - ${info}`);
    };
    const customPaste = (editor, event, callback) => {
      console.log('ClipboardEvent 粘贴事件对象', event);

      // 自定义插入内容
      // editor.insertText('xxx');

      // 返回值（注意，vue 事件的返回值，不能用 return）
      callback(true); // 返回 false ，阻止默认粘贴行为
      // callback(true) // 返回 true ，继续默认的粘贴行为
    };

    const insertText = () => {
      const editor = editorRef.value;
      if (editor == null) return;

      editor.insertText('hello world');
    };

    const printHtml = () => {
      const editor = editorRef.value;
      if (editor == null) return;
      console.log(editor.getHtml());
    };

    const disable = () => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.disable()
    };

    return {
      editorRef,
      mode: 'default',
      valueHtml,
      toolbarConfig,
      editorConfig,
      handleCreated,
      handleChange,
      handleDestroyed,
      handleFocus,
      handleBlur,
      customAlert,
      customPaste,
      insertText,
      printHtml,
      disable
    };
  },
}
</script>

<style scoped>
/*.title{*/
/*  margin-top: 60px;*/
/*}*/
.submit {
  margin-top: 5px;
}

.body {
  background-color: #FFFFFF;
}
</style>