import { createRouter, createWebHistory } from 'vue-router'
import Main from '../views/oj/Main'
import ProblemList from "@/views/oj/problem/ProblemList.vue";
import ContestList from "@/views/oj/contest/ContestList";
import Status from "@/views/oj/status/Status";
import Rank from "@/views/oj/rank/Rank";
import Blog from "@/views/oj/blog/Blog";
import About from "@/views/oj/about/About";

const routerHistory = createWebHistory()



const routes=[
    {
        path: "/logo",
        redirect: "/"
    },
    {
        name: "main",
        path: '/',
        component: Main,
        meta:{
            title: "Home - Online Judge - Henu"
        }
    },
    {
        path: "/problems",
        component: ProblemList,
        meta: {
            title: 'Problems - Online Judge - Henu',
        }
    },
    {
        path: "/contest",
        component: ContestList,
        meta:{
            title: "Contest - Online Judge - Henu"
        }
    },
    {
        path: "/status",
        component: Status,
        meta:{
            title: "Status - Online Judge - Henu"
        }
    },
    {
        path: "/rank",
        component: Rank,
        meta:{
            title: "Rank - Online Judge - Henu"
        }
    },
    {
        path: "/blog",
        component: Blog,
        meta:{
            title: "Blog - Online Judge - Henu"
        }
    },
    {
        path: "/about",
        component: About,
        meta:{
            title: "About - Online Judge - Henu"
        }
    },
]
const router = createRouter({
    history: routerHistory,
    routes
})

export default router
