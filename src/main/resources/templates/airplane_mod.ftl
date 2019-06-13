<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>编辑飞机 #${biz_modifier.id?c}</h1>
    <hr>
    <form class="form-horizontal" action="/api/airplane/modify/${biz_modifier.id?c}" method="POST">
      <input type="text" class="invisible" name="id" value="${biz_modifier.id?c}">
      <div class="form-group">
        <label class="col-sm-2 control-label">飞机名</label>
        <div class="col-sm-10">
          <input type="text" maxlength="29" class="form-control" name="name" placeholder="J20-01"
                 value="${biz_modifier.name}" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">座位行数</label>
        <div class="col-sm-10">
          <input type="number" min="1" max="99" class="form-control" name="rows"
                 value="${biz_modifier.rows}" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">座位列数</label>
        <div class="col-sm-10">
          <input type="number" min="1" max="10" class="form-control" name="cols"
                 value="${biz_modifier.cols}" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">头等舱的行数</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" name="firstClassRows"
                 value="${biz_modifier.firstClassRows}" required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">状态</label>
        <div class="col-sm-4">
          <select name="type" class="form-control">
            <option value="0" <#if biz_modifier.type==0!>selected</#if>>已启用</option>
            <option value="1" <#if biz_modifier.type==1!>selected</#if>>未启用</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-success">保存机场</button>
          <a class="btn btn-default" href="/admin/airplane/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>