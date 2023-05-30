import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../components/Login.vue'
import HomeView from '../views/HomeView.vue'
import SignInView from '../components/SignIn.vue'
import FindView from '../components/Find.vue'
import VlistView from '../components/VideoList.vue'
import VideoDatail from '../components/VideoDetail.vue'
import RlistView from '../components/Rank.vue'
import MyPage from '../components/MyPage.vue'
import AdminView from '../components/Admin.vue'
import AdminVideoView from '../components/AdminVideo.vue'
import AdminMemberView from '../components/AdminMember.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/login/sign-in',
    name: 'sign-in',
    component: SignInView
  },
  {
    path: '/login/find',
    name: 'find',
    component: FindView
  },
  {
    path: '/videoList',
    name: 'Vlist',
    component: VlistView
  },
  {
    path: '/RankList',
    name: 'Rlist',
    component: RlistView
  },
  {
    path: '/videoDetail',
    name: 'Vdetail',
    component: VideoDatail
  },
  {
    path: '/MyPage',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/Admin',
    name: 'AdminView',
    component: AdminView
  },
  {
    path: '/AdminVideo',
    name: 'AdminVideoView',
    component: AdminVideoView
  },
  {
    path: '/AdminMember',
    name: 'AdminMemberView',
    component: AdminMemberView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
