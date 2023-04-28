import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import ViewUIPlus from 'view-ui-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'font-awesome/css/font-awesome.min.css'
import 'view-ui-plus/dist/styles/viewuiplus.css'
import Cookies from 'js-cookie'
const app = createApp(App)
app.use(ElementPlus)
app.use(ViewUIPlus)
app.use(router)
app.config.productionTip = false
app.config.globalProperties.$cookies = Cookies
router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')
