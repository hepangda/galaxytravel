<#import "layout/main.ftl" as layout>
<@layout.content>
    <div class="col-md-offset-3 col-md-6 login-box">
        <p class="banner"><strong>登录</strong></p>
        <div>请您在使用前先登录您的账号</div>
        <hr>
        <form class="form-horizontal" method="POST" action="/api/login">
            <div class="form-group">
                <label for="username" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-8">
                    <input type="tel" class="form-control" name="username" id="username" placeholder="手机号" value="${phoneNo!}">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="pwd" id="password" placeholder="密码">
                </div>
            </div>
            <#if global_biz_message??>
                <div class="col-sm-offset-3 col-sm-8">
                    <p class="text-danger">${global_biz_message}</p>
                </div>
            </#if>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <a class="pull-right" href="/">返回主页</a>
                    <input type="hidden" name="referer" value="${referer!}">
                    <button type="submit" class="btn btn-success">登录</button>
                    <a href="/register" class="btn btn-default">注册</a>
                </div>
            </div>
        </form>
    </div>
</@layout.content>