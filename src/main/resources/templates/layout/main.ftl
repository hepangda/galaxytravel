<#macro content>
  <!DOCTYPE html>
  <html>
  <head>
    <title>灰猪旅行</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
  </head>
  <body>
  <div class="top-bar">
    <div class="container text-muted">
      <div class="pull-right">
        手机客户端 客服中心 商家中心 |
        <a href="http://ttms.xuejietech.cn/">喵眼电影</a>
      </div>
        <#if currentUser??>
            <#if currentUser.type == 0>
              Hi，点击搜索框开始订票吧！
            <#elseif currentUser.type == 1>
              管理员 ${currentUser.realname} 你好，快乐工作，认真生活哦~
            </#if>
        <#else>
          Hi，最近想去哪里玩？请 <a href="/login">登录</a> <a href="/register">免费注册</a>
        </#if>
    </div>
  </div>
  <div class="site-header">
    <div class="container">
      <div class="pull-right">
          <#if currentUser??>
            <div class="btn-group"
                 style="vertical-align: top; margin-top: 5px; margin-right: 20px;">
              <button type="button" class="btn btn-default dropdown-toggle" style="border:none;"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span>${currentUser.realname}</span> <span class="caret"></span>
              </button>
              <ul class="dropdown-menu">
                <li><a href="/logout">退出</a></li>
              </ul>
            </div>
          <#else>

            <a class="btn btn-default giant-order-btn" href="/order">立即出发</a>
          </#if>
      </div>
      <img src="/images/flippy-logo.png">
        <#if currentUser?? && currentUser.type == 1>
          <div class="mgrhello">
            <h4>管理后台</h4>
          </div>
        <#else>
          <div class="searchbox">
            <form method="get" action="/user/flight/list">
              <input type="text" name="to" placeholder="泰国/巴厘岛/京都/首尔">
              <input type="submit" class="submitbtn" value="搜索">
            </form>
          </div>
        </#if>

    </div>
  </div>
  <div class="container"><#nested /></div>
  <div class="site-footer">
    <div class="container">&copy; 2019 灰猪旅行</div>
  </div>
  <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
  </html>
</#macro>