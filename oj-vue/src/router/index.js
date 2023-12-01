import {createRouter, createWebHistory} from 'vue-router'
import Main from '../views/oj/Main'
import ProblemList from "@/views/oj/problem/ProblemList.vue";
import ContestList from "@/views/oj/contest/ContestList";
import Status from "@/views/oj/status/Status";
import Rank from "@/views/oj/rank/Rank";
import Blog from "@/views/oj/blog/Blog";
import About from "@/views/oj/about/About";
import Register from "@/views/oj/user/Register.vue";
import Login from "@/views/oj/user/Login.vue";
import UserPanel from "@/views/oj/user/UserPanel.vue";
import Problem from "@/views/oj/problem/Problem.vue";
import EditArticle from "@/views/oj/article/EditArticle.vue";
import Article from "@/views/oj/article/Article.vue";
import Contest from "@/views/oj/contest/Contest.vue";

const routerHistory = createWebHistory()


const routes = [
    {
        path: "/logo",
        redirect: "/"
    },
    {
        name: "main",
        path: '/',
        component: Main,
        meta: {
            title: "Home - Online Judge",
        }
    },
    {
        path: "/problems",
        component: ProblemList,
        meta: {
            title: 'Problems - Online Judge',
        }
    },
    {
        path: "/contest",
        component: ContestList,
        meta: {
            title: "Contest - Online Judge"
        }
    },
    {
        path: "/status",
        component: Status,
        meta: {
            title: "Status - Online Judge"
        }
    },
    {
        path: "/rank",
        component: Rank,
        meta: {
            title: "Rank - Online Judge"
        }
    },
    {
        path: "/blog",
        component: Blog,
        meta: {
            title: "Blog - Online Judge"
        }
    },
    {
        path: "/about",
        component: About,
        meta: {
            title: "About - Online Judge"
        }
    },
    {
        path: "/login",
        component: Login,
        meta: {
            title: "Login - Online Judge"
        }
    },
    {
        path: "/register",
        component: Register,
        meta: {
            title: "Register - Online Judge"
        }
    },
    {
        path: "/user",
        component: UserPanel,
        meta: {
            title: "我的 - Online Judge"
        }
    },
    {
        path: "/problem/:id",
        name: "problem",
        component: Problem,
        meta: {
            title: "题目详情 - Online Judge"
        }
    },
    {
        path: "/article/edit",
        component: EditArticle,
        meta: {
            title: "编辑文章 - Online Judge"
        }
    },
    {
        path: "/article/:id",
        name: "article",
        component: Article,
        meta: {
            title: "文章详情页 - Online Judge"
        }
    },
    {
        path: "/contest/:id",
        name: "contest",
        component: Contest,
        meta: {
            title: "竞赛详情页 - Online Judge"
        }
    },
    {
        path: '/404',
        name: 'not-found',
        component: () => import('../views/oj/404/404.vue'),
        hidden: true
    },
    // 404 page must be placed at the end !!!
    { path: '/:catchAll(.*)', redirect: '/404', hidden: true }
]
const router = createRouter({
    history: routerHistory,
    routes
})

export default router
