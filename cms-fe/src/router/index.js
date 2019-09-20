import Vue from 'vue'
import Router from 'vue-router'
import courseindex from '@/pages/courseindex'
import courselist from '@/pages/courselist'
import coursedetail from '@/pages/coursedetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'courseindex',
      component: courseindex
    },
    {
      path: '/courselist',
      name: 'courselist',
      component: courselist
    },
    {
      path: '/coursedetail',
      name: 'coursedetail',
      component: coursedetail
    }
  ]
})
