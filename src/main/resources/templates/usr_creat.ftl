<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>添加用户</h1>
    <hr>
    <form class="form-horizontal" action="/api/user/create" method="POST">
      <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="19" class="form-control" name="username" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
          <input type="password" maxlength="29" class="form-control" name="pwd" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">真实姓名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="29" class="form-control" name="realname" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">手机号码</label>
        <div class="col-sm-10">
          <input type="text" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="14"
                 class="form-control" name="phone" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">类型</label>
        <div class="col-sm-4">
          <select name="type" class="form-control">
            <option value="0">普通用户</option>
            <option value="1">管理员</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">保存</button>
          <a class="btn btn-default" href="/admin/user/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>