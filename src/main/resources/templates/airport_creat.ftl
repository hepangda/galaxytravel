<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>创建机场</h1>
    <hr>
    <form class="form-horizontal" action="/api/airport/create" method="POST">
      <div class="form-group">
        <label class="col-sm-2 control-label">机场名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="19" class="form-control" name="portName" placeholder="上海虹桥" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">城市名</label>
        <div class="col-sm-4">
          <input type="text" maxlength="19" class="form-control" name="cityName" placeholder="上海" required>
        </div>
        <label class="col-sm-2 control-label">状态</label>
        <div class="col-sm-4">
          <select name="type" class="form-control">
            <option value="0">已启用</option>
            <option value="1">未启用</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">保存机场</button>
          <a class="btn btn-default" href="/admin/airport/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>