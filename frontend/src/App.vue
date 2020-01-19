<template>
  <div id="app">
    <div class="header">
      <p class="link-container">
        <router-link class="header-link" to="/calculator">Calculator</router-link>
        <router-link class="header-link" to="/fees">Fees</router-link>
      </p>
    </div>
    <router-view/>
    <div class="errorView">
      <h1 class="error">{{ this.$root.errMessage }}</h1>
      <button type="submit" class="error-close" v-on:click="onErrorClose($event)">
        <img src="./assets/close-icon.png" width="20px" height="20px">
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    onErrorClose: function (event) {
      this.$root.hideErrorView();
    }
  }
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.header {
  display: inline-block;
  box-shadow: 0px 5px 5px #888888;
  background-color: black;
  position: fixed;
  top: 0;
  left: 0;
  right: 100%;
  height: 5%;
  width: 100%;
  z-index: 999;
  padding: 0.5%;
}
.errorView {
  display: none;
  background-color: #db524c;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 100%;
  height: 5%;
  width: 100%;
}
p.link-container {
  display: block;
}
.header-link {
  text-align: left;
  font-size: medium;
  color: white;
  margin-left: 2.5%;
}
h1.error {
  text-align: center;
  font-size: medium;
}
button.error-close {
  height: 100%;
  width: 5%;
  position: absolute;
  right: 0px;
  top: 0px;
  background-color: transparent;
  border: none;
  cursor: pointer;
  text-align: right;
}
</style>
