<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>添加飞机</h1>
    <hr>
    <form class="form-horizontal" action="/api/airplane/create" method="POST">
      <div class="form-group">
        <label class="col-sm-2 control-label">飞机名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="29" class="form-control" name="name" placeholder="J20-01" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">座位行数</label>
        <div class="col-sm-10">
          <input type="number" min="1" max="99" class="form-control" name="rows" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">座位列数</label>
        <div class="col-sm-10">
          <input type="number" min="1" max="10" class="form-control" name="cols" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">头等舱的行数</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" name="firstClassRows" required>
        </div>
      </div>
      <div class="form-group">
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
          <button type="submit" class="btn btn-success">保存</button>
          <a class="btn btn-default" href="/admin/airplane/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>