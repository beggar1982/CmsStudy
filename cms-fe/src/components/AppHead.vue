<template>
  <div class="topmenu">
    <div class="wrapper">
      <div>
        <a href="/index.html">
          <img src="../assets/logo.png" alt="" class="header-logo">
        </a>
      </div>

      <div>
        <ul class="header-nav">
          <li class="no1"><a href="/index.html">标签1</a></li>
          <li class="no2"><a href="/index.html">标签2</a></li>
        </ul>
      </div>

      <div class="loginInformation">
        <span id="login-register" class="login-register" style="display: inline;">
          <a id="registerLink" href="/register/" title="" target="_blank">注册</a>
          <a id="loginLink" href="javascript:register();" title="" target="_self">登录</a>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "AppHead"
  }
</script>

<style scoped>
  @import '../common/public.css';

  .topmenu {
    width: 100%;
    height: 80px;
    background-color: #fff;
    position: fixed;
    top: 0;
    left: 0;
    border-bottom: 1px solid #f6f6f6;
    overflow: hidden;
  }

  .wrapper {
    width: 100%;
    background-color: #ffffff;
    clear: both;
  }

  .header-logo {
    margin-top: 20px;
    float: left;
    display: block;
    margin-right: 35px;
  }

  .header-nav {
    width: 284px;
    height: 32px;
    margin-top: 26px;
    float: left;
    display: inline-block;
  }

  .header-nav li {
    margin: 0 8px 0 9px;
    display: inline;
    float: left;
    height: 32px;
    line-height: 32px;
    text-align: center;
    font-size: 14px;
  }

  .loginInformation {
  }

  .loginInformation .login-register {
    display: none;
  }

  .loginInformation .login-register a {
    margin-right: 45px;
    font-size: 14px;
    color: #ff874a;

    margin-top: 32px;
    float: right;
    display: block;
  }
</style>
