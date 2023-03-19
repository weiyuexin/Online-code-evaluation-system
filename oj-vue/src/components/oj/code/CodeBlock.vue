<template>
  <prism-editor
      class="my-editor height-300"
      v-model="code"
      aria-disabled
      :highlight="highlighter"
      line-numbers
      :readonly="true"
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
      code: '#include <stdio.h>\n' +
          ' \n' +
          'void arr_out(int a[8])//输出函数\n' +
          '{\n' +
          '    int i = 0;\n' +
          '    for(i = 0;i < 8;i++)\n' +
          '    {\n' +
          '        printf("%d ",a[i]);\n' +
          '    }\n' +
          '    printf("\\n");\n' +
          '}\n' +
          ' \n' +
          'void arr_sort(int *p,int n)\n' +
          '{\n' +
          '    int i,j;\n' +
          '    int min = 0;\n' +
          '    for(i = 0;i < n - 1;i++)//排序次数\n' +
          '    {\n' +
          '        min = i;\n' +
          '        for(j = i + 1;j < n;j++)\n' +
          '        {\n' +
          '            if(p[j] < p[min])\n' +
          '            {\n' +
          '                min = j;//记录交换的元素下标值\n' +
          '            }\n' +
          '        }\n' +
          '        if(i != min)\n' +
          '        {\n' +
          '            int temp = p[i];\n' +
          '            p[i] = p[min];\n' +
          '            p[min] = temp;\n' +
          '        }  \n' +
          '    }\n' +
          '}\n' +
          ' \n' +
          'int main()\n' +
          '{\n' +
          '    int a[8] = {0};\n' +
          '    int i = 0;\n' +
          '    for(i = 0;i < 8;i++)\n' +
          '    {\n' +
          '        scanf("%d",&a[i]);\n' +
          '    }\n' +
          ' \n' +
          '    arr_sort(a,8);//排序函数\n' +
          '    arr_out(a);//输出函数\n' +
          ' \n' +
          '    return 0;\n' +
          '}'
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
}

.prism-editor__textarea:focus {
  outline: none;
}

/* 非必须 */
.height-300 {
  height: 400px;
}
</style>

