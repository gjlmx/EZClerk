import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import DateCalculator from '@/components/DateCalculator';
import DocumentCreate from '@/components/DocumentCreate';
import CaseInfo from '@/components/CaseInfo';

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    // {
    //   path:"/",
    //   name:'DateCalculator',
    //   component: DateCalculator
    //
    // },
    // {
    //   path:"/",
    //   name:'CaseInfo',
    //   component: CaseInfo
    //
    // },
    {
      path:"/",
      name:'DocumentCreate',
      component: DocumentCreate

    }
  ]
})
