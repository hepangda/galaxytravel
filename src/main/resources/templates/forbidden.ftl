<#import "layout/main.ftl" as layout>
<@layout.content>
  <div class="col-md-offset-3 col-md-6 login-box">
    <p class="banner"><strong>Oops</strong></p>
    <hr>
    <form class="form-horizontal">
      <div class="form-grouop">
        <h3 style="text-align: center">您没有权限访问这个页面!</h3>
      </div>
      <div class="form-grouop">
      </div>
      <div class="form-grouop">
        <h5 style="text-align: center">
          <#if currentUser??>
            <a href="/">返回主页</a>
          <#else>
            <a href="/login">请先登录</a>
          </#if>
        </h5>
      </div>
    </form>
  </div>
</@layout.content>