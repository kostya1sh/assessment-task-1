// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';

Vue.config.productionTip = false;

Vue.config.errorHandler = (err, vm, info) => {
  console.log(`Error: ${err.toString()}\nInfo: ${info}`);
};

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  data () {
    return {
      errMessage: ''
    };
  },
  methods: {
    showErrorView: function (err) {
      document.querySelector('.errorView').style.display = 'inline-block';
      this.errMessage = err.toString();
    },
    hideErrorView: function () {
      document.querySelector('.errorView').style.display = 'none';
    }
  }
});
