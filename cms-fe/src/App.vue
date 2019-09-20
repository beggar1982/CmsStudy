<template>
  <div id="app">
    <AppHead></AppHead>

    <div class="contentStyle">
      <router-view></router-view>
    </div>

    <AppFoot></AppFoot>
  </div>
</template>

<script>
  import AppHead from '@/components/AppHead';
  import AppFoot from '@/components/AppFoot';

  export default {
    name: 'App',
    components: {
      AppHead,
      AppFoot
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    width: 100%;
  }

  body {
    margin: 0;
    padding: 0;
  }

  .contentStyle {
    padding: 0;
    margin: 0;
    border: 0;
    width: 100%;
    height: auto;
    left: 0px;
    bottom: 0px;
    background-color: #fff;
  }

</style>
