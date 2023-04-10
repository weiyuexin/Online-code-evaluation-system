<template>
    <prism-editor
        class="my-editor height-300"
        v-model="code"
        aria-disabled
        :highlight="highlighter"
        line-numbers
        :readonly="false"
        :tabSize="4"
    ></prism-editor>

</template>

<script>
import {PrismEditor} from "vue-prism-editor";
import "vue-prism-editor/dist/prismeditor.min.css";
import {highlight, languages} from "prismjs/components/prism-core";
import "prismjs/components/prism-clike";
import "prismjs/components/prism-javascript";
import "prismjs/themes/prism-tomorrow.css";

export default {
  name: "CodeBlock",
  data() {
    return {
      code: 'package main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\npackage main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\npackage main\n\nimport "fmt"\n\nfunc main() {\n	fmt.Println("hello golang")\n	var a, b int\n	//fmt.Scanf("%d %d", &a, &b)\n	fmt.Println("a+b=", a+b)\n}\n'
    };
  },
  components: {
    PrismEditor
  },
  methods: {
    highlighter(code) {
      return highlight(code, languages.js);
    }
  }
}
</script>

<style scoped>
.my-editor {
  background: #2d2d2d;
  color: #ccc;
  font-family: Fira code, Fira Mono, Consolas, Menlo, Courier, monospace;
  font-size: 14px;
  line-height: 1.5;
  padding: 5px;
  width: 700px;
}

.prism-editor__textarea:focus {
  outline: none;
}

/* 非必须 */
.height-300 {
  height: 400px;
}
</style>

