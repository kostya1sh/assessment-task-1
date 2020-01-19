import Vue from 'vue';
import Router from 'vue-router';
import Fees from '@/components/Fees';
import Calculator from '@/components/Calculator';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Calculator
    },
    {
      path: '/fees',
      name: 'Fees',
      component: Fees
    },
    {
      path: '/calculator',
      name: 'Calculator',
      component: Calculator
    }
  ]
});

router.onError(err => {
  console.log('Router error catched: ' + err.toString());
});

export default router;
