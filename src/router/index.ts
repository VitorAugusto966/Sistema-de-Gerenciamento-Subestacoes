import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import SubEstacaoListView from '@/views/subEstacao/SubEstacaoListView.vue'
import SubEstacaoFormView from '@/views/subEstacao/SubEstacaoFormView.vue'
import SubEstacaoUpdView from '@/views/subEstacao/subEstacaoUpdView.vue'
import SubEstacaoMapView from '@/views/subEstacao/SubEstacaoMapView.vue'
import SubEstacaoMapsView from '@/views/subEstacao/SubEstacaoMapsView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: LoginView,
      meta: {
        title: 'Login',
      },
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
      meta: {
        title: 'Home',
      },
    },
    {
      path: '/subEstacaoList',
      name: 'SubEstacaoList',
      component: SubEstacaoListView,
    },
    {
      path: '/subEstacaoForm',
      name: 'SubEstacaoForm',
      component: SubEstacaoFormView,
    },
    {
      path: '/subEstacaoUpd',
      name: 'SubEstacaoUpdate',
      component: SubEstacaoUpdView,
    },
    {
      path: '/subEstacaoMAp',
      name: 'SubEstacaoMap',
      component: SubEstacaoMapView,
    },
    {
      path: '/mapSub',
      name: 'MapSubEstacao',
      component: SubEstacaoMapsView,
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/',
    },
  ],
})

export default router
