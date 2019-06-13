<#import "layout/main.ftl" as layout>
<@layout.content>
    <div class="col-md-offset-3 col-md-6 login-box">
        <p class="banner"><strong>注册</strong></p>
        <hr>
        <form class="form-horizontal" action="/api/register" method="POST">
            <div class="form-group">
                <label for="username" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="username" id="username" placeholder="用户名" value="${phoneNo!}">
                </div>
            </div>
            <div class="form-group">
                <label for="pwd" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="pwd" id="pwd" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <label for="realname" class="col-sm-3 control-label">真实姓名</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="realname" id="realname" placeholder="真实姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-sm-3 control-label">手机号</label>
                <div class="col-sm-8">
                    <input type="tel" class="form-control" name="phone" id="phone" placeholder="手机号">
                </div>
            </div>
            <#if message??>
                <div class="col-sm-offset-3 col-sm-8">
                    <p class="text-danger">${message}</p>
                </div>
            </#if>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <a class="pull-right" href="/login">已有账号？登录</a>
                    <input type="hidden" name="type" value="0">
                    <button type="submit" class="btn btn-primary">注册</button>
                </div>
            </div>
        </form>
    </div>
</@layout.content>