<#import "layout/main.ftl" as layout>
<@layout.content>
    <#include "layout/sidebar.ftl">
  <div class="col-md-9">
    <h1>编辑机场 #${biz_modifier.id?c}</h1>
    <hr>
    <form class="form-horizontal" action="/api/airport/modify/${biz_modifier.id?c}" method="POST">
      <input type="text" class="invisible" name="id" value="${biz_modifier.id?c}">
      <div class="form-group">
        <label class="col-sm-2 control-label">机场名</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="portName" value="${biz_modifier.portName}"
                 required>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">城市名</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="cityName" value="${biz_modifier.cityName}"
                 required>
        </div>
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
          <a class="btn btn-default" href="/admin/airport/list">返回</a>
        </div>
      </div>
    </form>
  </div>
</@layout.content>